package com.movies_api.movies_aplication.activity.main_activity.dagger

import com.movies_api.movies_aplication.activity.main_activity.mvp.activity.MainActivity
import com.movies_api.movies_aplication.activity.main_activity.mvp.model.MainModel
import com.movies_api.movies_aplication.activity.main_activity.mvp.presenter.MainPresenter
import com.movies_api.movies_aplication.activity.main_activity.mvp.view.MainView
import com.movies_api.movies_aplication.dagger.network.Network
import com.movies_api.movies_aplication.fragment.DetailMovie
import com.movies_api.movies_aplication.fragment.ShoppingCart
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
    fun fragmentShoppingCart(): DetailMovie {
        return DetailMovie()
    }

    @Provides
    @MainScope
    fun shoppingCart(): ShoppingCart {
        return ShoppingCart()
    }



}