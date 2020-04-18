package com.marzec.cv.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<V: MvpView, P: MvpPresenter<V>>(@LayoutRes contentLayoutId: Int)
    : Fragment(contentLayoutId), MvpView {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this as V)
    }

    override fun onPause() {
        presenter.detach()
        super.onPause()
    }
}