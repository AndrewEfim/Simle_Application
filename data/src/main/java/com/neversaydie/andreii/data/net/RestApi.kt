package com.neversaydie.andreii.data.net

import com.neversaydie.andreii.data.entity.CountryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

//?pageSize=100
    @GET("countrys?pageSize=100")
    fun getCountry():Observable<List<CountryResponse>>

    @GET("countrys?pageSize=100")
    fun getCountryByContinent(@Query("where") continent:String ):Observable<List<CountryResponse>>

}