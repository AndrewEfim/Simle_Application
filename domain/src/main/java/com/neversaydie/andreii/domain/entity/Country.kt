package com.neversaydie.andreii.domain.entity

data class Country(
        val id: String,
        val country: String,
        val capital: String,
        val otherCityOne: String,
        val otherCityTwo: String,
        val otherCityThree: String

) : DomainEntity {
}