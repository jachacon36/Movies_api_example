package com.movies_api.movies_aplication.activity.main_activity.dagger

import com.movies_api.movies_aplication.activity.main_activity.mvp.activity.MainActivity
import com.movies_api.movies_aplication.dagger.app_component.AppComponent
import dagger.Component

@MainScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun injectMainActivity(activity: MainActivity)
}