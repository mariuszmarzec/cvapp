package com.marzec.cv.ui.home

import com.marzec.cv.di.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ConverterFragmentModule {

    @Binds
    @FragmentScope
    abstract fun taskPresenter(presenter: HomePresenter): HomeScreenContract.Presenter
}
