package com.merqueo.test_android_merqueo.activity.main_activity.dagger

import com.merqueo.test_android_merqueo.activity.main_activity.mvp.activity.MainActivity
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.model.MainModel
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.presenter.MainPresenter
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.view.MainView
import com.merqueo.test_android_merqueo.dagger.network.Network
import com.merqueo.test_android_merqueo.fragment.FragmentShoppingCart
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {

    // MPV

    //View
    @Provides
    @MainScope
    fun view(): MainView {
        return MainView(activity)
    }


    //Presenter
    @Provides
    @MainScope
    fun presenter(view: MainView, model: MainModel): MainPresenter {
        return MainPresenter(view, model)
    }


    //Model
    @Provides
    @MainScope
    fun model(network: Network): MainModel {
        return MainModel(network)
    }

    @Provides
    @MainScope
    fun fragmentShoppingCart(): FragmentShoppingCart {
        return FragmentShoppingCart()
    }



}