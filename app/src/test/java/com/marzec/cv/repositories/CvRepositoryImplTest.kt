package com.marzec.cv.repositories

import com.marzec.cv.TestSchedulersRule
import com.marzec.cv.api.CvApi
import com.marzec.cv.api.stubCvDto
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.repositories.CvRepositoryImpl
import com.marzec.cv.stubCv
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestSchedulersRule::class)
class CvRepositoryImplTest {

    private val api: CvApi = mock()

    private lateinit var repository: CvRepositoryImpl

    @BeforeEach
    fun setUp() {
        repository = CvRepositoryImpl(api)
    }

    @Test
    fun `observeCv | loading resource emitted first, value second`() {
        whenever(api.getCv()) doReturn Single.just(stubCvDto())

        repository.observeCv()
            .test()
            .assertValueAt(0, Resource.Loading())
            .assertValueAt(1, Resource.Data(stubCv()))

        verify(api).getCv()
    }

    @Test
    fun `observeCv | loading resource emitted first, error occurred`() {
        whenever(api.getCv()) doReturn Single.error(Exception())

        repository.observeCv()
            .test()
            .assertValueAt(0, Resource.Loading())
//            .assertValueAt(1) { res -> res is Resource.Error }
// commented because of e: java.lang.IllegalStateException: Backend Internal error: Exception during file facade code generation
            .assertNoErrors()
        verify(api).getCv()
    }
}