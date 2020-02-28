package com.egiwon.delieveryherosample

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.egiwon.delieveryherosample.base.BaseActivity
import com.egiwon.delieveryherosample.databinding.ActivityMainBinding
import com.egiwon.delieveryherosample.ui.GithubActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = viewModel

        val uri = intent.data

        val code = uri?.getQueryParameter("code")
        if (!code.isNullOrEmpty()) {
            viewModel.requestAccessToken(code)
        }
    }

    override fun addObserve() {
        super.addObserve()
        viewModel.authUriLiveData.observe(this, Observer {
            ContextCompat.startActivity(this, Intent(Intent.ACTION_VIEW, it), null)
        })

        viewModel.accessToken.observe(this, Observer {
            if (it.isNotEmpty()) {
                startActivity(Intent(this, GithubActivity::class.java))
            }
        })

    }

}