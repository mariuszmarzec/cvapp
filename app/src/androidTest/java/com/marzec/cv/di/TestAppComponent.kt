package com.marzec.cv.di

import com.marzec.cv.App
import com.marzec.cv.api.CvApi
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.junit.Test
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    RepositoryModule::class,
    InteractorModule::class,
    ActivityBuilder::class,
    ApiModule::class
])
interface TestAppComponent : AppComponent {

    fun getCvApi(): CvApi

    @Component.Builder
    interface Builder {
        fun apiModule(module: ApiModule): Builder
        @BindsInstance
        fun app(App: App): Builder
        fun build(): TestAppComponent
    }
}
