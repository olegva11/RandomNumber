package com.example.randomnumber.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryNumberEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "found") var isFound: Boolean,
    @ColumnInfo(name = "number") var number: Long,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "date") var date: String
)
