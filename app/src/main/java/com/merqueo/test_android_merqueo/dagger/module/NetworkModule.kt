package com.merqueo.test_android_merqueo.dagger.module


import android.content.Context

import com.google.gson.GsonBuilder
import com.merqueo.test_android_merqueo.dagger.app_scope.AppScope
import com.merqueo.test_android_merqueo.dagger.network.Network
import com.merqueo.test_android_merqueo.dagger.qualifers.ApplicationContext
import com.merqueo.test_android_merqueo.helpers.APIClientURL
import com.merqueo.test_android_merqueo.helpers.Constants

import java.io.File
import java.util.concurrent.TimeUnit


import dagger.Module
import dagger.Provides

import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class NetworkModule {


    @AppScope
    @Provides
    fun cache(@ApplicationContext context: Context): Cache {
        return Cache(
            File(context.cacheDir, Constants.HTTP_CACHE_DIR),
            Constants.HTTP_CACHE_SIZE.toLong()
        )
    }

    @AppScope
    @Provides
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .callTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(block = { chain ->
                val ongoing = chain.request().newBuilder()
                ongoing.addHeader("Accept", "application/json")
                chain.proceed(ongoing.build())
            })
            .cache(cache)
            .build()
    }


    @AppScope
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        return logging.apply { logging.level = HttpLoggingInterceptor.Level.BASIC }
    }


    @AppScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(APIClientURL.URL_BASE)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @AppScope
    @Provides
    fun network(retrofit: Retrofit): Network {
        return retrofit.create<Network>(
            Network::class.java!!)
    }


}
