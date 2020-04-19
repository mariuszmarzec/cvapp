package com.marzec.cv.di

import android.content.Context
import dagger.Binds
import dagger.Module
import com.marzec.cv.App
import com.marzec.cv.common.DefaultImageLoader
import com.marzec.cv.common.ImageLoader
import com.marzec.cv.common.StringProvider
import com.marzec.cv.common.StringProviderImpl
import com.squareup.picasso.Picasso
import dagger.Provides

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: App): Context

    @Binds
    abstract fun bindImageDisplayer(defaultImageLoader: DefaultImageLoader): ImageLoader

    @Binds
    abstract fun bindStringProvider(stringProvider: StringProviderImpl): StringProvider

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providePicasso(): Picasso = Picasso.get()
    }
}