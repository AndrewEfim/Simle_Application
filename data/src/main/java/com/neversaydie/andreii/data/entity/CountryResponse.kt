package com.neversaydie.andreii.data.entity

import com.google.gson.annotations.SerializedName

data class CountryResponse(
        @SerializedName("objectId")
        val id: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("capital")
        val capital: String,
        @SerializedName("othercity_one")
        val otherCityOne: String,
        @SerializedName("othercity_two")
        val otherCityTwo: String,
        @SerializedName("othercity_three")
        val otherCityThree: String
) {
}