package com.thiago.dragonballzapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.data.repository.LocalDataSourceImpl
import com.thiago.dragonballzapp.domain.repository.LocalDataSource
import com.thiago.dragonballzapp.util.Constants.DRAGONBALL_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context : Context
    ):DragonballDatabase{
        return  Room.databaseBuilder(
            context,
            DragonballDatabase::class.java,
            DRAGONBALL_DATABASE
        ).build()
    }
    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: DragonballDatabase
    ): LocalDataSource =
        LocalDataSourceImpl(dragonballDatabase = database)
}