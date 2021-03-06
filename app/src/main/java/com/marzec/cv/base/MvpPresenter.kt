package com.marzec.cv.base

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)
    fun detach()
}