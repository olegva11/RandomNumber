package com.example.randomnumber.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnumber.models.NumberResponse
import com.example.randomnumber.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val queryResponse = MutableStateFlow(NumberResponse())

    fun getInfoByNumber(number: Int)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            queryResponse.value = Api.retrofitService.getInfoByNumber(number)
        }
    }

    fun getInfoByRandomNumber()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            queryResponse.value = Api.retrofitService.getInfoByRandomNumber()
        }
    }

}