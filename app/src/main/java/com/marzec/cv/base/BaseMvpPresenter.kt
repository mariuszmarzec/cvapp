package com.marzec.cv.base

import androidx.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import com.marzec.cv.extensions.plusAssign

abstract class BaseMvpPresenter<V : MvpView> : MvpPresenter<V> {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected var view: V? = null

    @CallSuper
    override fun attach(view: V) {
        this.view = view
    }

    @CallSuper
    override fun detach() {
        compositeDisposable.clear()
        view = null
    }

    fun <T> Observable<T>.subscribeTillDetach(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        compositeDisposable += subscribe(onSuccess, onError)
    }
}