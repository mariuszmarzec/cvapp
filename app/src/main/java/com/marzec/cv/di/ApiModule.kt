package com.marzec.cv.di

import com.marzec.cv.api.CvApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofitForConverter(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://gist.githubusercontent.com/mariuszmarzec/771836f886d466cf1e5ff9f8e550eae7/raw/111c3ca89cda577d63c6c8de9e6aec7befad1b74/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCvApi(retrofit: Retrofit): CvApi = retrofit.create(CvApi::class.java)
}