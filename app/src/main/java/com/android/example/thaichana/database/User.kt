package com.android.example.thaichana.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "user_table")
data class User(


    @PrimaryKey(autoGenerate = true)
val id: Int,

@ColumnInfo(name = "shopid") var shopid: String,

@ColumnInfo(name = "place") var place: String,

@ColumnInfo(name = "date") var date: String,

@ColumnInfo(name = "Check in ") var check: Boolean





   /* @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") var id: Int*/




)
