package com.egiwon.delieveryherosample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.common.base.BaseViewModel
import com.egiwon.common.wrapper.SchedulersExt.mainThreadSchedulers
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.ui.model.User
import com.egiwon.delieveryherosample.ui.model.mapToDomainUser
import com.egiwon.delieveryherosample.ui.model.mapToUser
import com.egiwon.repository.GithubRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class GithubSharedViewModel(
    private val githubRepository: GithubRepository
) : BaseViewModel() {

    private val _likeUsers = MutableLiveData<List<User>>()
    val likeUsers: LiveData<List<User>> get() = _likeUsers

    private val _unLikeUser = MutableLiveData<User>()
    val unLikeUser: LiveData<User> get() = _unLikeUser

    private val _removedUser = MutableLiveData<User>()
    val removedUser: LiveData<User> get() = _removedUser

    private val _searchUserResultList = MutableLiveData<List<User>>()
    val searchUserResultList: LiveData<List<User>> get() = _searchUserResultList

    private val tab = MutableLiveData<Tab>()

    val searchQuery = MutableLiveData<String>()

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    fun setTab(tab: Tab) {
        this.tab.value = tab
    }

    private fun searchUsers(query: String) {
        if (query.isEmpty()) {
            _searchUserResultList.value = emptyList()
        } else {
            githubRepository.searchUserInfo(query)
                .observeOn(mainThreadSchedulers)
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribeBy(
                    onSuccess = { list ->
                        _searchUserResultList.value = list.map { it.mapToUser() }
                    },
                    onError = {
                        mutableErrorTextResId.value = R.string.error_load_fail
                    }
                )
                .addTo(compositeDisposable)
        }
    }

    private fun searchLikeUsers(query: String) =
        if (query.isEmpty()) {
            getLikeUser()
        } else {
            githubRepository.searchLikeUsers(query)
                .observeOn(mainThreadSchedulers)
                .subscribeBy { domainList ->
                    _likeUsers.value = domainList.map { it.mapToUser() }
                }
                .addTo(compositeDisposable)
        }

    fun saveOrRemoveChangedLikeUser(user: User) =
        if (!user.like) {
            githubRepository.addLikeUser(user.copy(like = true).mapToDomainUser())
        } else {
            _unLikeUser.value = user
            _removedUser.value = user
            githubRepository.removeLikeUser(user.mapToDomainUser())
        }.observeOn(mainThreadSchedulers)
            .subscribe { getLikeUser() }
            .addTo(compositeDisposable)

    fun getLikeUser() = githubRepository.getLikeUser()
        .observeOn(mainThreadSchedulers)
        .subscribeBy(
            onSuccess = { list -> _likeUsers.value = list.map { it.mapToUser() } },
            onError = { mutableErrorTextResId.value = R.string.error_like_user_load_fail }
        )
        .addTo(compositeDisposable)

    fun searchGithubQuery(query: String) {
        Single.create<String> {
            if (tab.value == Tab.API) searchUsers(query) else searchLikeUsers(query)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)
    }
}