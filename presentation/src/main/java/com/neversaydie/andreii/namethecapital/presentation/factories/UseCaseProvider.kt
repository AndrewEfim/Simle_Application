package com.neversaydie.andreii.namethecapital.presentation.factories

import com.neversaydie.andreii.data.net.RestService
import com.neversaydie.andreii.data.repositories.CountryRepositoryImpl
import com.neversaydie.andreii.domain.usecases.GetCountryByContinentUseCase
import com.neversaydie.andreii.domain.usecases.GetCountryUseCase
import com.neversaydie.andreii.namethecapital.presentation.executor.UIThread

object UseCaseProvider {

    var data: String = "https://api.backendless.com/3893C5E2-9BD1-A9D0-FFAF-578551CA9B00/4913DCA6-FB11-5DB3-FF95-B17903816C00/data/"
    val restService = RestService(data)
    val uiThread = UIThread()
    val cntrRpstr = CountryRepositoryImpl(restService)

    fun provideCountryListUseCase(): GetCountryUseCase {
        return GetCountryUseCase(uiThread, cntrRpstr)
    }

    fun provideCountryByContinentListUseCase():GetCountryByContinentUseCase{
        return GetCountryByContinentUseCase(uiThread, cntrRpstr)
    }
}