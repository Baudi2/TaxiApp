package ru.startandroid.develop.pulttaxi.model.data

import com.google.gson.annotations.SerializedName

data class UserData(
    val status : Int,
    val id : Int,
    @SerializedName("phone_number")
    val phoneNumber : String,
    val name : String,
)
