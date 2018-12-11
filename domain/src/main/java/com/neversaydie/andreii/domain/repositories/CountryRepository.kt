package com.neversaydie.andreii.domain.repositories

import com.neversaydie.andreii.domain.entity.Country
import io.reactivex.Observable

interface CountryRepository: BaseRepository {

    fun getCountry():Observable<List<Country>>
}