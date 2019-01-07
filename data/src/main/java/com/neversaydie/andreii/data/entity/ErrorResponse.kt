package com.neversaydie.andreii.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("message")
        val message: String = "",

        @SerializedName("code")
        val errorCode: String = ""
):DataEntity {
}