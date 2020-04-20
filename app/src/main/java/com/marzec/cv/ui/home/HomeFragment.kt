package com.marzec.cv.ui.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.marzec.cv.R
import com.marzec.cv.base.BaseFragment
import com.marzec.cv.common.ImageLoader
import com.marzec.cv.views.delegates.DelegateAdapter
import com.marzec.cv.views.delegates.DelegateManager
import com.marzec.cv.views.delegates.SectionHeaderDelegate
import com.marzec.cv.views.delegates.TextRowWithImageDelegate
import com.marzec.cv.views.model.ListItemView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_content.*
import kotlinx.android.synthetic.main.fragment_home_header.*
import org.jetbrains.anko.alert
import javax.inject.Inject


class HomeFragment
    : BaseFragment<HomeScreenContract.View, HomeScreenContract.Presenter>(R.layout.fragment_home),
    HomeScreenContract.View {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val adapter by lazy {
        DelegateAdapter(
            DelegateManager()
                .add(SectionHeaderDelegate())
                .add(TextRowWithImageDelegate(imageLoader))
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun showError(title: String, message: String, showRetry: Boolean) {
        requireActivity().alert(message, title) {
            if (showRetry) positiveButton(R.string.error_dialog_retry) { presenter.onRetryDialogButtonClick() }
            negativeButton(R.string.error_dialog_leave_app) { presenter.onNoDialogButtonClick() }
            isCancelable = false
        }.show()
    }

    override fun close() {
        activity?.finish()
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
        adapter.items = items
    }
}
