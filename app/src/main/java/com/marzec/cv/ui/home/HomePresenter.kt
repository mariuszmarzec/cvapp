package com.marzec.cv.ui.home

import android.util.Log
import com.marzec.cv.base.BaseMvpPresenter
import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.extensions.toMainThread
import com.marzec.cv.interactors.CvInteractor
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val cvInteractor: CvInteractor,
    private val homeScreenMapper: HomeScreenMapper
) : BaseMvpPresenter<HomeScreenContract.View>(), HomeScreenContract.Presenter {

    override fun attach(view: HomeScreenContract.View) {
        super.attach(view)

        cvInteractor.observeCv()
            .toMainThread()
            .subscribeTillDetach(
                onSuccess = this::onDataLoading,
                onError = { throwable -> showError(throwable) }
            )
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
                showError(resource.error)
            }
        }
    }

    private fun showContent(cv: Cv) {
        val content = homeScreenMapper.mapItems(cv)
        view?.hideProgress()
        view?.setHeader(content.header)
        view?.setContent(content.viewItems)
    }

    private fun showError(error: Throwable) {
        Log.e(this::class.simpleName, error.message, error)
        view?.hideProgress()
        view?.showError()
    }
}