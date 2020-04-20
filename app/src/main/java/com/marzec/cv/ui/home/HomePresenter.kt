package com.marzec.cv.ui.home

import android.util.Log
import com.marzec.cv.R
import com.marzec.cv.base.BaseMvpPresenter
import com.marzec.cv.common.StringProvider
import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.extensions.toMainThread
import com.marzec.cv.interactors.CvInteractor
import io.reactivex.subjects.PublishSubject
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val cvInteractor: CvInteractor,
    private val homeScreenMapper: HomeScreenMapper,
    private val stringProvider: StringProvider
) : BaseMvpPresenter<HomeScreenContract.View>(), HomeScreenContract.Presenter {

    private val loadingTrigger = PublishSubject.create<Unit>()

    override fun attach(view: HomeScreenContract.View) {
        super.attach(view)

        cvInteractor.observeCv()
            .repeatWhen { loadingTrigger }
            .toMainThread()
            .subscribeTillDetach(
                onSuccess = this::onDataLoading,
                onError = { throwable -> showFatalError(throwable) }
            )
    }

    private fun showFatalError(throwable: Throwable) {
        Log.e(this::class.simpleName, throwable.message, throwable)
        view?.apply {
            hideProgress()
            showError(
                stringProvider.getString(R.string.error_dialog_title),
                stringProvider.getString(R.string.error_dialog_message_fatal_error),
                showRetry = false
            )
        }
    }

    private fun onDataLoading(resource: Resource<Cv>) {
        when (resource) {
            is Resource.Data -> {
                val cv = resource.content
                showContent(cv)
            }
            is Resource.Loading -> {
                resource.content?.let { showContent(it) } ?: view?.showProgress()
            }
            is Resource.Error -> {
                Log.e(this::class.simpleName, resource.error.message, resource.error)
                view?.apply {
                    hideProgress()
                    showError(
                        stringProvider.getString(R.string.error_dialog_title),
                        stringProvider.getString(R.string.error_dialog_message_unknown_error_try_again),
                        showRetry = true
                    )
                }
            }
        }
    }

    private fun showContent(cv: Cv) {
        val content = homeScreenMapper.mapItems(cv)
        view?.apply {
            hideProgress()
            setHeader(content.header)
            setContent(content.viewItems)
        }
    }

    override fun onRetryDialogButtonClick() {
        loadingTrigger.onNext(Unit)
    }

    override fun onNoDialogButtonClick() {
        view?.close()
    }

}