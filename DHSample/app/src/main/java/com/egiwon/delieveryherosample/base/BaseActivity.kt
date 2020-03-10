package com.egiwon.delieveryherosample.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(layoutResId) {

    protected abstract val viewModel: VM

    protected lateinit var binding: B
        private set

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
        addObserve()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    protected open fun addObserve() {
        viewModel.errorTextResId.observe(this, Observer { showToast(it) })
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(textResId: Int) {
        showToast(getString(textResId))
    }
}