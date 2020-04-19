package com.marzec.cv.interactors

import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.repositories.CvRepository
import com.marzec.cv.stubCv
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CvInteractorImplTest {

    private val repository: CvRepository = mock()

    private lateinit var interactor: CvInteractorImpl

    @BeforeEach
    fun setUp() {
        interactor = CvInteractorImpl(repository)
    }

    @Test
    fun observeCv() {
        val subject = PublishSubject.create<Resource<Cv>>()
        whenever(repository.observeCv()) doReturn subject

        val testObserver = interactor.observeCv()
            .test()

        subject.onNext(Resource.Loading())
        subject.onNext(Resource.Data(stubCv()))

        testObserver
            .assertValueAt(0, Resource.Loading())
            .assertValueAt(1, Resource.Data(stubCv()))

        verify(repository).observeCv()
    }
}