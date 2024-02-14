package com.example.randomnumber.data

import com.example.randomnumber.database.HistoryDatabase
import com.example.randomnumber.database.HistoryNumberEntity
import com.example.randomnumber.network.NumberManager
import kotlinx.coroutines.flow.Flow

class Repository(
    private val manager: NumberManager,
    private val database: HistoryDatabase
) {
    suspend fun getInfoByNumber(number: Long) = manager.getResultsByNumber(number)
    suspend fun getInfoByRandomNumber() = manager.getResultsByRandomNumber()
    suspend fun insertNumberInfo(historyNumber: HistoryNumberEntity) =
        database.historyDao().insert(historyNumber)

    fun getAllNumberInfo(): Flow<List<HistoryNumberEntity>> = database.historyDao().selectAll()

}