package com.example.randomnumber.ui.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomnumber.MainApp
import com.example.randomnumber.database.HistoryNumberEntity
import com.example.randomnumber.models.NumberResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = (application as MainApp).appContainer.repository
    private val queryResponse = MutableStateFlow(NumberResponse())
    private val _allRecords = MutableStateFlow<List<HistoryNumberEntity>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            queryResponse.collect { result ->
                writeInfoNumber(result)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNumberInfo().collect {
                _allRecords.value = it
            }
        }
    }

    val allRecords: StateFlow<List<HistoryNumberEntity>> get() = _allRecords

    fun getInfoByNumber(number: Long) {
        viewModelScope.launch(Dispatchers.IO)
        {
            queryResponse.value = repository.getInfoByNumber(number)
        }
    }

    fun getInfoByRandomNumber() {
        viewModelScope.launch(Dispatchers.IO)
        {
            queryResponse.value = repository.getInfoByRandomNumber()
        }
    }

    private fun writeInfoNumber(number: NumberResponse) {
        if (!number.text.isNullOrEmpty()) {
            val historyNumberEntity = HistoryNumberEntity(
                id = 0,
                text = number.text,
                isFound = number.found ?: false,
                number = number.number ?: 0,
                type = number.type ?: "",
                date = number.date ?: ""
            )
            viewModelScope.launch(Dispatchers.IO)
            {
                repository.insertNumberInfo(historyNumberEntity)
            }
        }
    }
}