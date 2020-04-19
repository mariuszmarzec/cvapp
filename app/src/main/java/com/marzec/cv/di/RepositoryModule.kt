package com.marzec.cv.di

import com.marzec.cv.repositories.CvRepository
import com.marzec.cv.repositories.CvRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindCvRepository(repository: CvRepositoryImpl): CvRepository
}