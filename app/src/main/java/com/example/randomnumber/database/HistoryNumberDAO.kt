package com.example.randomnumber.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryNumberDAO {
    @Insert
    suspend fun insert(number: HistoryNumberEntity)

    @Delete
    suspend fun delete(number: HistoryNumberEntity)

    @Query("SELECT * FROM history")
    fun selectAll() : Flow<List<HistoryNumberEntity>>

}