package com.jesusrojo.usersmvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesusrojo.usersmvvm.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract  class UsersDatabase: RoomDatabase(){
    abstract fun getMyDAO():UsersDAO
}