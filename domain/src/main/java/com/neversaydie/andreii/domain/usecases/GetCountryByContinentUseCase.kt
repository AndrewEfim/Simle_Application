package com.neversaydie.andreii.domain.usecases

import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.domain.entity.CountryByContinent
import com.neversaydie.andreii.domain.executor.PostExecutorThread
import com.neversaydie.andreii.domain.repositories.CountryRepository
import io.reactivex.Observable

class GetCountryByContinentUseCase(postExecutorThread: PostExecutorThread
                                   , private val countryRepository: CountryRepository) : BaseUseCase(postExecutorThread)
{
    fun getByContinent(getcountyr: CountryByContinent):Observable<List<Country>>{
        return countryRepository.getCountryByContinent(getcountyr)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecurorThread)
    }

}