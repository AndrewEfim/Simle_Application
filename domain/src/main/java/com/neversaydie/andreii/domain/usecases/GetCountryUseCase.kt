package com.neversaydie.andreii.domain.usecases

import android.util.Log
import com.neversaydie.andreii.domain.entity.Country
import com.neversaydie.andreii.domain.executor.PostExecutorThread
import com.neversaydie.andreii.domain.repositories.CountryRepository
import io.reactivex.Observable

class GetCountryUseCase(postExecutorThread: PostExecutorThread
                        , private val countryRepository: CountryRepository) : BaseUseCase(postExecutorThread) {




    fun get(): Observable<List<Country>> {
     //   Log.d(TAG,"get From GET COUNTRY USE CASE")
        return countryRepository.getCountry()
                .observeOn(postExecutorThread)
                .subscribeOn(workExecurorThread)
    }
}