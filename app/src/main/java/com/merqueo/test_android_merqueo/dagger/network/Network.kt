package com.merqueo.test_android_merqueo.dagger.network

import com.merqueo.test_android_merqueo.helpers.APIClientURL
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Network {

    @GET(APIClientURL.MOVIE_POPULAR )
    fun postLogin(@Query("api_key") api_key : String,
                  @Query("page") page : Int): Observable<Response<String>>
}