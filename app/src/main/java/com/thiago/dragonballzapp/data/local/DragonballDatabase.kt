package com.thiago.dragonballzapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thiago.dragonballzapp.data.local.dao.HeroDao
import com.thiago.dragonballzapp.data.local.dao.HeroRemoteKeysDao

import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 2)
@TypeConverters(DatabaseConverter::class)
abstract class DragonballDatabase : RoomDatabase() {

    companion object{
       fun create(context : Context,useInMemory:Boolean):DragonballDatabase{
           val databaseBuilder = if(useInMemory){
               Room.inMemoryDatabaseBuilder(context,DragonballDatabase::class.java)
           }else{
               Room.databaseBuilder(context,DragonballDatabase::class.java,"test_database.db")
           }
           return  databaseBuilder
               .fallbackToDestructiveMigration()
               .build()
       }
    }
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}