package com.example.architecture

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel<VS>: ViewModel() {

    abstract val viewState: BehaviorSubject<VS>

    protected val bindings = CompositeDisposable()

    protected fun bind(vararg disposables: Disposable) {
        bindings.addAll(*disposables)
    }

    override fun onCleared() {
        bindings.dispose()
    }

}