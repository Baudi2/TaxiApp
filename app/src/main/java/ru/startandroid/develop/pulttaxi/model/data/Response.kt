package ru.startandroid.develop.pulttaxi.model.data

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status")
    val status : String
)
