package com.marzec.cv.ui.home

import com.marzec.cv.*
import com.marzec.cv.common.StringProvider
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.interactors.CvInteractor
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestSchedulersRule::class)
internal class HomePresenterTest {

    private val cvInteractor: CvInteractor = mock()
    private val homeScreenMapper: HomeScreenMapper = mock()
    private val stringProvider: StringProvider = MockStringProvider

    private val view: HomeScreenContract.View = mock()

    private lateinit var presenter: HomePresenter

    @BeforeEach
    fun setUp() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.never())
        whenever(homeScreenMapper.mapItems(any())).thenReturn(stubHomeScreenContent())
        presenter = HomePresenter(
            cvInteractor,
            homeScreenMapper,
            stringProvider
        )
    }

    @Test
    fun `observeCv | hide progress and show error in case of rx stream error`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.error(Throwable()))

        presenter.attach(view)

        verify(cvInteractor).observeCv()
        verify(view).hideProgress()
        verify(view).showError(
            stringProvider.getString(R.string.error_dialog_title),
            stringProvider.getString(R.string.error_dialog_message_fatal_error),
            showRetry = false
        )
    }

    @Test
    fun `observeCv | hide progress and show error in case resource error`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.just(Resource.Error(Throwable())))

        presenter.attach(view)

        verify(cvInteractor).observeCv()
        verify(view).hideProgress()
        verify(view).showError(
            stringProvider.getString(R.string.error_dialog_title),
            stringProvider.getString(R.string.error_dialog_message_unknown_error_try_again),
            showRetry = true
        )
    }

    @Test
    fun `observeCv | retry after error caused by resource error`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.just(Resource.Error(Throwable())))

        presenter.attach(view)


        presenter.onRetryDialogButtonClick()
        verify(cvInteractor).observeCv()
        verify(view, times(2)).hideProgress()
        verify(view, times(2)).showError(
            stringProvider.getString(R.string.error_dialog_title),
            stringProvider.getString(R.string.error_dialog_message_unknown_error_try_again),
            showRetry = true
        )
    }

    @Test
    fun `observeCv | show progress on loading resource`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.just(Resource.Loading()))

        presenter.attach(view)

        verify(cvInteractor).observeCv()
        verify(view).showProgress()
    }

    @Test
    fun `observeCv | hide progress and show content on data resource`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.just(Resource.Data(stubCv())))

        presenter.attach(view)

        verify(cvInteractor).observeCv()
        verify(homeScreenMapper).mapItems(stubCv())
        verify(view).hideProgress()
        verify(view).setHeader(stubHomeScreenContent().header)
        verify(view).setContent(stubHomeScreenContent().viewItems)
    }

    @Test
    fun `observeCv | hide progress and show content on loading resource with data`() {
        whenever(cvInteractor.observeCv()).thenReturn(Observable.just(Resource.Loading(stubCv())))

        presenter.attach(view)

        verify(cvInteractor).observeCv()
        verify(homeScreenMapper).mapItems(stubCv())
        verify(view).hideProgress()
        verify(view).setHeader(stubHomeScreenContent().header)
        verify(view).setContent(stubHomeScreenContent().viewItems)
    }

    @Test
    fun `onNoDialogButtonClick | close view`() {
        presenter.attach(view)
        presenter.onNoDialogButtonClick()

        verify(view).close()
    }
}