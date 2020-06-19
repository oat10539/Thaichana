package com.android.example.thaichana.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY id DESC"  )
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user_table WHERE `Check in ` = 1 ORDER BY id DESC")
    suspend fun getOne(): List<User>


    @Query("UPDATE user_table SET `Check in ` = 0  WHERE `Check in ` = 1 and id = (SELECT max(id) FROM user_table WHERE `Check in `= 1)  ")
    suspend fun checkout()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:User): Long






}