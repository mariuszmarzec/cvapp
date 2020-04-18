package com.marzec.cv.di

import com.marzec.cv.ui.home.HomeActivity
import com.marzec.cv.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindMainActivity(): HomeActivity
}