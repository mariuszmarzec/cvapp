package com.marzec.cv.ui.home

import com.marzec.cv.base.MvpPresenter
import com.marzec.cv.base.MvpView
import com.marzec.cv.views.model.ListItemView


interface HomeScreenContract {

    interface View : MvpView {

        fun showError(title: String, message: String, showRetry: Boolean)
        fun showProgress()
        fun hideProgress()
        fun setHeader(header: HeaderModel)
        fun setContent(items: List<ListItemView>)
        fun close()
    }

    interface Presenter : MvpPresenter<View> {
        fun onRetryDialogButtonClick()
        fun onNoDialogButtonClick()
    }
}