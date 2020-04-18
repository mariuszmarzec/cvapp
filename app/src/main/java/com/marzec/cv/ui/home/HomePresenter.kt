package com.marzec.cv.ui.home

import com.marzec.cv.base.BaseMvpPresenter
import javax.inject.Inject

class HomePresenter @Inject constructor(
) : BaseMvpPresenter<HomeScreenContract.View>(), HomeScreenContract.Presenter {

    override fun attach(view: HomeScreenContract.View) {
        super.attach(view)
    }
}