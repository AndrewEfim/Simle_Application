package com.neversaydie.andreii.data.net

import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.GsonBuilder
import com.neversaydie.andreii.data.entity.CountryResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService(private val apiUrl: String) {
    private val restApi: RestApi


    init {
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)

        okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build()
        restApi = retrofit.create(RestApi::class.java)
    }

    fun getCountrys(): Observable<List<CountryResponse>> {
      //  Log.d(TARGET_LOG,"getCountry from RestService")
        return restApi.getCountry()
    }

    fun getCountrysByContinent(continent:String):Observable<List<CountryResponse>>{
        return restApi.getCountryByContinent(continent)
    }
}