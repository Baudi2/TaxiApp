package ru.startandroid.develop.pulttaxi.model.data

import com.google.gson.annotations.SerializedName

data class PostResonpse(
    @SerializedName("error")
    val error: String,
    @SerializedName("token")
    val token : String,
    @SerializedName("status")
    val status: String
)
