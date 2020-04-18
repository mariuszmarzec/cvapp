package com.marzec.cv.di

import android.content.Context
import dagger.Binds
import dagger.Module
import com.marzec.cv.App

@Module
interface AppModule {
    @Binds
    fun bindContext(application: App): Context
}