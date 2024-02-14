package com.example.randomnumber

import android.content.Context
import com.example.randomnumber.data.Repository
import com.example.randomnumber.database.DatabaseHistory
import com.example.randomnumber.network.Api
import com.example.randomnumber.network.NumberManager

class AppContainer(context: Context) {

    private val retrofit by lazy{
        NumberManager(Api.retrofitService)
    }

    private val database = DatabaseHistory().getInstance(context)

    val repository by lazy{
        Repository(retrofit, database)
    }
}