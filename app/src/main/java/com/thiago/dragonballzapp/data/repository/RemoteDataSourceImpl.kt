package com.thiago.dragonballzapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.data.paging_source.HeroRemoteMediator
import com.thiago.dragonballzapp.data.paging_source.SearchHeroesSource
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.repository.RemoteDataSource
import com.thiago.dragonballzapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val dragonballApi: DragonballApi,
    private val dragonballDatabase: DragonballDatabase
) : RemoteDataSource {

    private val heroDao = dragonballDatabase.heroDao()


    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                dragonballApi = dragonballApi,
                dragonballDatabase = dragonballDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query : String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(dragonballApi = dragonballApi, query = query)
            }
        ).flow
    }
}