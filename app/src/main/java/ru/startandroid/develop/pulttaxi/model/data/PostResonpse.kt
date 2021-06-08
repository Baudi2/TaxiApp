package ru.startandroid.develop.pulttaxi.model.data

import com.google.gson.annotations.SerializedName

data class PostResonpse(
    @SerializedName("token")
    val token : String,
    @SerializedName("error")
    val error: String
)
