package com.marzec.cv.ui.home

import com.marzec.cv.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class HomeFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindPresenter(presenter: HomePresenter): HomeScreenContract.Presenter

    @Binds
    @FragmentScope
    abstract fun bindHomeScreenMapper(homeScreenMapper: HomeScreenMapperImpl): HomeScreenMapper
}
