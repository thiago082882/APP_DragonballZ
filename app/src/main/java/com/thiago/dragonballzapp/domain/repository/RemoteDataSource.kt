package com.thiago.dragonballzapp.domain.repository

import androidx.paging.PagingData
import com.thiago.dragonballzapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes() : Flow<PagingData<Hero>>
}