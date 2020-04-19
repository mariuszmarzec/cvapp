package com.marzec.cv.ui.home

import com.marzec.cv.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindConvertFragment() : HomeFragment
}