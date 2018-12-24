package com.neversaydie.andreii.data.entity

import com.neversaydie.andreii.domain.entity.Country

fun CountryResponse.transformToDomain(): Country {
    return Country(id = id, country = country, capital = capital
            , otherCityOne = otherCityOne
            , otherCityTwo = otherCityTwo
            , otherCityThree = otherCityThree)
}