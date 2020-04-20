package com.marzec.cv

import com.marzec.cv.di.DaggerTestAppComponent
import com.marzec.cv.di.FakeApiModule
import com.marzec.cv.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TestApp : App() {

    lateinit var component: TestAppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val builder = DaggerTestAppComponent.builder()
            .apiModule(FakeApiModule())
            .app(this)
        component = builder.build()
        return component
    }
}