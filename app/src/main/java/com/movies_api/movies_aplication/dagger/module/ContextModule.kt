package com.movies_api.movies_aplication.dagger.module
import android.content.Context

import com.movies_api.movies_aplication.dagger.app_scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(context: Context) {

    val appContext: Context = context.applicationContext

    @Provides
    @AppScope
    fun context(): Context {
        return appContext
    }
}