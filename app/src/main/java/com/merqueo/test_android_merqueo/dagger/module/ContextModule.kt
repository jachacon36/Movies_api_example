package com.merqueo.test_android_merqueo.dagger.module
import android.content.Context

import com.merqueo.test_android_merqueo.dagger.app_scope.AppScope
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