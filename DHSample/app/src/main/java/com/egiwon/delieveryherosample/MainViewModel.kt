package com.egiwon.delieveryherosample

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egiwon.delieveryherosample.base.BaseViewModel
import com.egiwon.delieveryherosample.data.AccessTokenProvider
import com.egiwon.delieveryherosample.data.source.GithubRepository
import io.reactivex.android.schedulers.AndroidSchedulers

class MainViewModel(
    private val repository: GithubRepository
) : BaseViewModel() {

    private val _authUrlLiveData = MutableLiveData<Uri>()
    val authUriLiveData: LiveData<Uri> get() = _authUrlLiveData

    private val _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String> get() = _accessToken

    fun getAuthUri() {
        _authUrlLiveData.value = Uri.Builder()
            .scheme("https")
            .authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.GithubClientId)
            .build()
    }

    fun requestAccessToken(code: String) {
        repository.requestAccessToken(code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _accessToken.value = it.accessToken
                AccessTokenProvider.token = it.accessToken
            }, {
                mutableErrorTextResId.value = R.string.error_access_token_load_fail
            }).addDisposable()
    }

}