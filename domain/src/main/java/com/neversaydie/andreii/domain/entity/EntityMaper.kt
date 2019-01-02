package com.neversaydie.andreii.domain.entity

fun CountryByContinent.transformToSearchRequest():String{
    return "continent LIKE '$continent%'"
}
