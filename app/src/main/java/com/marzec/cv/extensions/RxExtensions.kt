package com.marzec.cv.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.onIo(): Observable<T> = this.subscribeOn(Schedulers.io())

fun <T> Observable<T>.toMainThread(): Observable<T> = this.observeOn(AndroidSchedulers.mainThread())
