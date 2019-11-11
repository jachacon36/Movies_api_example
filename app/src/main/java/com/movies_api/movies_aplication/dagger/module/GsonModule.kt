package com.movies_api.movies_aplication.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.movies_api.movies_aplication.dagger.app_scope.AppScope
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