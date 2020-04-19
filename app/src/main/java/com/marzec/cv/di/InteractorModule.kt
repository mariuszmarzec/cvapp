package com.marzec.cv.di

import com.marzec.cv.interactors.CvInteractor
import com.marzec.cv.interactors.CvInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {

    @Binds
    fun bindCvInteractor(repository: CvInteractorImpl): CvInteractor
}