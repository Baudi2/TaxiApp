package ru.startandroid.develop.pulttaxi.model.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaxiApi {
    companion object {
        const val BASE_URL = "https://dev.pulttaxi.ru/api/"
    }

    @GET("requestSMSCodeClient")
    suspend fun getVerificationPin(
        @Query("phone") phone: Long
    )

    @POST("authenticateClients")
    suspend fun authenticateClient(
        @Query("phone_number") number: Int,
        @Query("password") password: Int
    ) : String

    @GET("client/getInfo")
    suspend fun clientInfo(
        @Query("token") token: String
    ) : Error
}