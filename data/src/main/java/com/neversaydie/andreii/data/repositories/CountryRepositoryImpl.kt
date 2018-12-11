package com.neversaydie.andreii.data.repositories

import android.util.Log
import com.neversaydie.andreii.data.entity.transformToDomain
import com.neversaydie.andreii.data.net.RestService
import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.domain.repositories.CountryRepository
import io.reactivex.Observable

class CountryRepositoryImpl(val apiService: RestService) : CountryRepository {




    override fun getCountry(): Observable<List<Country>> {
       // Log.d(TARGET_LOG, "getCountry From CountryRepositoryImpl ")
        return apiService.getCountrys().map { list -> list.map { country -> country.transformToDomain() } }
    }
}