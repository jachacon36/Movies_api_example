package com.movies_api.movies_aplication.dagger.app_component

import android.app.Application
import android.content.Context


import com.movies_api.movies_aplication.dagger.app_scope.AppScope
import com.movies_api.movies_aplication.dagger.module.AppModule
import com.movies_api.movies_aplication.dagger.module.ContextModule
import com.movies_api.movies_aplication.dagger.module.GsonModule
import com.movies_api.movies_aplication.dagger.module.NetworkModule
import com.movies_api.movies_aplication.dagger.network.Network
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, GsonModule::class, ContextModule::class ) )
interface AppComponent {

    fun inject(context: Context)

    fun inject(application: Application)

    fun smartcontrolNetwork(): Network

}