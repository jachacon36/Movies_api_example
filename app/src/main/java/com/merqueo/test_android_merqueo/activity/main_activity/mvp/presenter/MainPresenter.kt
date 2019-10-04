package com.merqueo.test_android_merqueo.activity.main_activity.mvp.presenter

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.merqueo.test_android_merqueo.activity.data_model.Movies_model
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.model.MainModel
import com.merqueo.test_android_merqueo.activity.main_activity.mvp.view.MainView
import com.merqueo.test_android_merqueo.adapter.Movies_adapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults

class MainPresenter(
    private val view: MainView,
    private val model: MainModel
) {
    private val realm = Realm.getDefaultInstance()
    private val REQUEST = 101
    lateinit var moviesAdapter : Movies_adapter

    fun getMovies(page : Int): Disposable {
        return model.getMovies(page)
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

    fun check_permissionsRequest(): Boolean? {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ContextCompat.checkSelfPermission(
                view.activity.applicationContext, WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                view.activity.applicationContext,
                READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                view.activity.applicationContext,
                INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                view.activity,
                arrayOf(
                    WRITE_EXTERNAL_STORAGE,
                    READ_EXTERNAL_STORAGE,
                    INTERNET

                ),
                REQUEST
            )
            return true
        }
        return false
    }


    fun createAddres_adapter(movies: RealmResults<Movies_model>): Movies_adapter {
        var adapter = Movies_adapter(movies)
        movies.addChangeListener { t, changeSet -> adapter.notifyDataSetChanged() }
        return adapter
    }

    fun getMoviesDB(): RealmResults<Movies_model> {
        return realm.where(Movies_model::class.java).findAll()
    }


}