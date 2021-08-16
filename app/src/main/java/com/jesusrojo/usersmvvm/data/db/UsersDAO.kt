package com.jesusrojo.usersmvvm.data.db

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesusrojo.usersmvvm.data.model.User

@Dao
interface UsersDAO {

    @WorkerThread
    @Query("SELECT * FROM users_table")
    fun fetchAll(): List<User>

    @WorkerThread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(enties: List<User>)

    @WorkerThread
    @Query("DELETE FROM users_table")
    suspend fun deleteAll()

}