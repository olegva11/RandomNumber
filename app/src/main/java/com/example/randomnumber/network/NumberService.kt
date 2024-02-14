package com.example.randomnumber.network

import com.example.randomnumber.models.NumberResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NumberService {

    @GET("{number}")
    suspend fun getInfoByNumber(
        @Path("number") number: Long
    ): NumberResponse

    @GET("random?min=0&max=${Long.MAX_VALUE}")//
    suspend fun getInfoByRandomNumber(
    ): NumberResponse
}