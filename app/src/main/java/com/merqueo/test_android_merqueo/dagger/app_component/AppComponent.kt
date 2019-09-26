package com.merqueo.test_android_merqueo.dagger.app_component

import android.app.Application
import android.content.Context


import com.merqueo.test_android_merqueo.dagger.app_scope.AppScope
import com.merqueo.test_android_merqueo.dagger.module.AppModule
import com.merqueo.test_android_merqueo.dagger.module.ContextModule
import com.merqueo.test_android_merqueo.dagger.module.GsonModule
import com.merqueo.test_android_merqueo.dagger.module.NetworkModule
import com.merqueo.test_android_merqueo.dagger.network.Network
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, NetworkModule::class, GsonModule::class, ContextModule::class ) )
interface AppComponent {

    fun inject(context: Context)

    fun inject(application: Application)

    fun smartcontrolNetwork(): Network

}