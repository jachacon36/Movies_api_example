package com.merqueo.test_android_merqueo.activity.main_activity.mvp.presenter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merqueo.test_android_merqueo.activity.data_model.Movies_model
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.model.MainModel
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class MainPresenter(
    private val view: MainView,
    private val model: MainModel
) {
    private val realm = Realm.getDefaultInstance()

    fun getMovies(api_key : String,page : Int): Disposable {
        return model.getMovies(api_key, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                val code: Int = response.code()
                when (code) {
                    200 -> {

                        val movies: Movies_model = gsonBuilder().fromJson(response.body(), Movies_model::class.java)
                        realm.executeTransaction { realm -> realm.insertOrUpdate(movies) }

                    }

                    401 -> {

                    }

                    422 -> {

                    }

                    500 -> {

                    }
                    else -> {

                    }
                }


            }, {

            })
    }

    private fun gsonBuilder(): Gson {

        lateinit var gson: Gson
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        gson = gsonBuilder.create()

        return gson
    }
}