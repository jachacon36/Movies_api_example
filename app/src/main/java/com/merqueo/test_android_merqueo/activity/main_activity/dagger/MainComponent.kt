package com.merqueo.test_android_merqueo.activity.main_activity.dagger

import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity
import com.merqueo.test_android_merqueo.dagger.app_component.AppComponent
import dagger.Component

@MainScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun injectMainActivity(activity: MainActivity)
}