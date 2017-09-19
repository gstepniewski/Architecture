package com.example.architecture

import android.support.v7.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<VS, VM: BaseViewModel<VS>>: AppCompatActivity() {

    protected abstract var viewModel: VM
    protected abstract fun render(viewState: VS)

    private val bindings = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        bindings.add(
            viewModel.viewState.observeOn(AndroidSchedulers.mainThread())
                               .subscribe(this::render)
        )
    }

    override fun onStop() {
        bindings.clear()
        super.onStop()
    }
}