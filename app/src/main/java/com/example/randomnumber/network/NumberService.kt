package com.example.randomnumber.network

import com.example.randomnumber.models.NumberResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberService {

    @GET("{number}")
    suspend fun getInfoByNumber(
        @Path("number") number: Int
    ): NumberResponse

    @GET("random")
    suspend fun getInfoByRandomNumber(
    ): NumberResponse
}