package com.marzec.cv.di

import com.marzec.cv.api.CvApi
import com.nhaarman.mockitokotlin2.mock
import retrofit2.Retrofit

class FakeApiModule : ApiModule() {

    override fun provideCvApi(retrofit: Retrofit): CvApi = mock()
}