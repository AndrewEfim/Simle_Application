package com.neversaydie.andreii.domain.repositories

import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.domain.entity.CountryByContinent
import io.reactivex.Observable

interface CountryRepository: BaseRepository {

    fun getCountry():Observable<List<Country>>

    fun getCountryByContinent(continent:CountryByContinent):Observable<List<Country>>

}