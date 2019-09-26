package com.merqueo.test_android_merqueo.activity.main_activity.mvp.model

import com.merqueo.test_android_merqueo.dagger.network.Network
import io.reactivex.Observable
import retrofit2.Response

class MainModel(private val network: Network) {

    fun getMovies(api_key: String, page: Int): Observable<Response<String>> {
        return network.postLogin(api_key,page)!!
    }


}