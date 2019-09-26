package com.merqueo.test_android_merqueo.dagger.module

import android.content.Context
import com.merqueo.test_android_merqueo.dagger.aplication.Aplication_test
import com.merqueo.test_android_merqueo.dagger.app_scope.AppScope
import com.merqueo.test_android_merqueo.dagger.qualifers.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val application: Aplication_test) {


    @Provides
    @AppScope
    @ApplicationContext
    fun application(): Aplication_test {
        return application
    }


    @Provides
    @AppScope
    @ApplicationContext
    fun context(): Context {
        return application
    }
}