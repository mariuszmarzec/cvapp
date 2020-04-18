package com.marzec.cv.ui.home

import com.marzec.cv.base.MvpPresenter
import com.marzec.cv.base.MvpView


interface HomeScreenContract {

    interface View : MvpView {

    }

    interface Presenter : MvpPresenter<View> {

    }
}