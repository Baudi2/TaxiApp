package ru.startandroid.develop.pulttaxi.model.api

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.startandroid.develop.pulttaxi.model.data.PostResonpse
import ru.startandroid.develop.pulttaxi.model.data.Response
import ru.startandroid.develop.pulttaxi.model.data.UserData

interface TaxiApi {
    companion object {
        const val BASE_URL = "https://dev.pulttaxi.ru/api/"
    }

    @GET("requestSMSCodeClient")
    suspend fun getVerificationPin(
        @Query("phone_number") phone: Long
    ) : Response

    @POST("authenticateClients")
    suspend fun authenticateClient(
        @Query("phone_number") number: Long,
        @Query("password") password: Int
    ) : PostResonpse

    @GET("client/getInfo")
    suspend fun clientInfo(
        @Query("token") token: String
    ) : UserData
}