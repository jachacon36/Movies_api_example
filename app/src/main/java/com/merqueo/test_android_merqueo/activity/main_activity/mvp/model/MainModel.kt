package com.merqueo.test_android_merqueo.activity.main_activity.mvp.model

import com.merqueo.test_android_merqueo.dagger.network.Network
import com.merqueo.test_android_merqueo.helpers.APIClientURL
import io.reactivex.Observable
import retrofit2.Response

class MainModel(private val network: Network) {

    fun getMovies(page: Int): Observable<Response<String>> {
        return network.postLogin(APIClientURL.API_KEY, page)!!
    }


}