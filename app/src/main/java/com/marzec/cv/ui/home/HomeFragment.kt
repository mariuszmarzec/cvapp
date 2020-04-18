package com.marzec.cv.ui.home

import android.os.Bundle
import android.view.View
import com.marzec.cv.R
import com.marzec.cv.base.BaseFragment


class HomeFragment
    : BaseFragment<HomeScreenContract.View, HomeScreenContract.Presenter>(R.layout.fragment_home),
    HomeScreenContract.View {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
