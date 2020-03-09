package com.egiwon.delieveryherosample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseViewModel
import com.egiwon.delieveryherosample.ui.model.User
import com.egiwon.delieveryherosample.ui.model.mapToDomainUser
import com.egiwon.delieveryherosample.ui.model.mapToUser
import com.egiwon.repository.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

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

    val searchUserLiveData: LiveData<Unit>
        get() = Transformations.map(searchQuery) {
            searchGithubQuery(it)
        }

    private val tab = MutableLiveData<Tab>()

    val searchQuery = MutableLiveData<String>()

    private val _isShowLoadingProgressBar = MutableLiveData<Boolean>()
    val isShowLoadingProgressBar: LiveData<Boolean> get() = _isShowLoadingProgressBar

    private val querySubject = PublishSubject.create<String>()

    init {
        querySubject.debounce(1000L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (tab.value == Tab.API) {
                    searchUsers(it)
                } else {
                    searchLikeUsers(it)
                }
            }
            .addDisposable()
    }

    fun setTab(tab: Tab) {
        this.tab.value = tab
    }

    private fun searchUsers(query: String) {
        if (query.isEmpty()) {
            _searchUserResultList.value = emptyList()
        } else {
            githubRepository.searchUserInfo(query)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _isShowLoadingProgressBar.value = true
                }
                .doAfterTerminate {
                    _isShowLoadingProgressBar.value = false
                }
                .subscribe({ list ->
                    _searchUserResultList.value = list.map { it.mapToUser() }
                }, {
                    mutableErrorTextResId.value = R.string.error_load_fail
                }).addDisposable()
        }
    }

    private fun searchLikeUsers(query: String) =
        if (query.isEmpty()) {
            getLikeUser()
        } else {
            githubRepository.searchLikeUsers(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { domainList ->
                    _likeUsers.value = domainList.map { it.mapToUser() }
                }
                .addDisposable()
        }


    fun saveOrRemoveChangedLikeUser(user: User) =
        if (user.like) {
            githubRepository.addLikeUser(user.mapToDomainUser())
        } else {
            _unLikeUser.value = user
            _removedUser.value = user
            githubRepository.removeLikeUser(user.mapToDomainUser())
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addDisposable()

    fun getLikeUser() = githubRepository.getLikeUser()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ list ->
            _likeUsers.value = list.map { it.mapToUser() }
        }, {
            mutableErrorTextResId.value = R.string.error_like_user_load_fail
        }).addDisposable()

    private fun searchGithubQuery(query: String) {
        querySubject.onNext(query)
    }
}