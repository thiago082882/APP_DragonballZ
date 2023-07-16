package com.thiago.dragonballzapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thiago.dragonballzapp.data.local.dao.HeroDao
import com.thiago.dragonballzapp.data.local.dao.HeroRemoteKeysDao

import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class DragonballDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}