package com.neversaydie.andreii.data.net

import com.neversaydie.andreii.data.entity.CountryResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface RestApi {


    @GET("countrys")
    fun getCountry():Observable<List<CountryResponse>>
}