package com.marzec.cv.ui.home

import android.os.Bundle
import android.view.View
import com.marzec.cv.R
import com.marzec.cv.base.BaseFragment
import com.marzec.cv.views.ImageLoader
import com.marzec.cv.views.model.ListItemView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_header.*
import javax.inject.Inject


class HomeFragment
    : BaseFragment<HomeScreenContract.View, HomeScreenContract.Presenter>(R.layout.fragment_home),
    HomeScreenContract.View {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showError() {
        // TODO
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }

    override fun setHeader(header: HeaderModel) {
        imageLoader.loadImage(header.image, photo)
        fullName.text = header.fulName
        phone.text = header.phoneNumber
        email.text = header.email
    }

    override fun setContent(items: List<ListItemView>) {
        // TODO
    }
}
