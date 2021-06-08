package ru.startandroid.develop.pulttaxi.model.data

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("active_order")
    val activeOrder: Any,
    @SerializedName("birth_day")
    val birthDay: Any,
    @SerializedName("city")
    val city: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("need_registration")
    val needRegistration: Boolean,
    @SerializedName("organization_id")
    val organizationId: Any,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("sex")
    val sex: Any,
    @SerializedName("status")
    val status: Int
)
