package com.thiago.dragonballzapp.data.repository

import androidx.paging.PagingData
import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.repository.DataStoreOperation
import com.thiago.dragonballzapp.domain.repository.LocalDataSource
import com.thiago.dragonballzapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperation,

    ) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remote.getAllHeroes()
    }

    fun searchHeroes(query:String):Flow<PagingData<Hero>>{
        return  remote.searchHeroes(query=query)

    }
    suspend fun getSelectedHero(heroId: Int): Hero =
        local.getSelectedHero(heroId = heroId)
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}