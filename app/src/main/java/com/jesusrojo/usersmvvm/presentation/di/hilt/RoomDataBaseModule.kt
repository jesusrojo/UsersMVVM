package com.jesusrojo.usersmvvm.presentation.di.hilt

import android.app.Application
import androidx.room.Room
import com.jesusrojo.usersmvvm.data.db.UsersDAO
import com.jesusrojo.usersmvvm.data.db.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application): UsersDatabase {
        return Room.databaseBuilder(app,
            UsersDatabase::class.java,
            "users_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMyDao(database: UsersDatabase): UsersDAO {
        return database.getMyDAO()
    }
}