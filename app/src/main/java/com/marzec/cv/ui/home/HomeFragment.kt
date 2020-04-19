package com.marzec.cv.ui.home

import android.os.Bundle
import android.view.View
import com.marzec.cv.R
import com.marzec.cv.base.BaseFragment
import com.marzec.cv.views.model.ListItemView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment
    : BaseFragment<HomeScreenContract.View, HomeScreenContract.Presenter>(R.layout.fragment_home),
    HomeScreenContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showError() {

    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun setHeader(header: HeaderModel) {

    }

    override fun setContent(items: List<ListItemView>) {

    }
}
