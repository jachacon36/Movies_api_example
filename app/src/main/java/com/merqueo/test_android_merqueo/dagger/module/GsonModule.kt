package com.merqueo.test_android_merqueo.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merqueo.test_android_merqueo.dagger.app_scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class GsonModule {

    @AppScope
    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }
}