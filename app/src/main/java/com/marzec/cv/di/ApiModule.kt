package com.marzec.cv.di

import com.marzec.cv.api.CvApi
import com.marzec.cv.domain.model.Cv
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@Module
open class ApiModule() {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().hostnameVerifier(
        HostnameVerifier { _, _ -> true }
    ).build()

    @Provides
    @Singleton
    fun provideRetrofitForConverter(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(" https://testapi.io/api/masi/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    open fun provideCvApi(retrofit: Retrofit): CvApi = retrofit.create(CvApi::class.java)
}