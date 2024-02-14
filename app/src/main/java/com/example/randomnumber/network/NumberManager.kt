package com.example.randomnumber.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NumberManager(val service: NumberService) {

    suspend fun getResultsByNumber(number: Long) = withContext(Dispatchers.IO) {
        service.getInfoByNumber(number)
    }

    suspend fun getResultsByRandomNumber() = withContext(Dispatchers.IO) {
        service.getInfoByRandomNumber()
    }

}