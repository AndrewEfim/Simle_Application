package com.neversaydie.andreii.data.entity

import com.google.gson.annotations.SerializedName

data class CountryResponse(
        @SerializedName("objectId")
        val id:String,
        @SerializedName("country")
        val country:String,
        @SerializedName("capital")
        val capital:String
) {
}