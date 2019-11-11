package com.movies_api.movies_aplication.activity.main_activity.mvp.model

import com.movies_api.movies_aplication.dagger.network.Network
import com.movies_api.movies_aplication.helpers.APIClientURL
import io.reactivex.Observable
import retrofit2.Response

class MainModel(private val network: Network) {

    fun getMovies(page: Int): Observable<Response<String>> {
        return network.postLogin(APIClientURL.API_KEY, page)!!
    }


}