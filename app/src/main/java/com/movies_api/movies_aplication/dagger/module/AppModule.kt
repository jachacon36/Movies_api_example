package com.movies_api.movies_aplication.dagger.module

import android.content.Context
import com.movies_api.movies_aplication.dagger.aplication.Aplication_test
import com.movies_api.movies_aplication.dagger.app_scope.AppScope
import com.movies_api.movies_aplication.dagger.qualifers.ApplicationContext
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