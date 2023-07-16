package com.thiago.dragonballzapp.data.paging_source


import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.model.HeroRemoteKeys
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val dragonballApi: DragonballApi,
    private val dragonballDatabase: DragonballDatabase
) : RemoteMediator<Int,Hero>() {

    private val heroDao = dragonballDatabase.heroDao()
    private val heroRemoteKeyDao = dragonballDatabase.heroRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
       val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeyDao.getRemoteKeys(heroId  = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        //Log.d("RemoteMediator", "Current Time: ${parseMillis(currentTime)} ")
        //Log.d("RemoteMediator", "Last Updated Time: ${parseMillis(lastUpdated)}")

        val diffInMinutes = (currentTime - lastUpdated) / 1000/60
        return  if (diffInMinutes.toInt() <= cacheTimeout){
            Log.d("RemoteMediator", "UP TO DATE! ")
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            Log.d("RemoteMediator", "REFRESH ")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosetToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return  MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = dragonballApi.getAllHeroes(page = page)

            if (response.heroes.isNotEmpty()) {
                dragonballDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeyDao.deleteAllRemoteKeys()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val lastUpdated = response.lastUpdated
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = lastUpdated
                        )
                    }
                    heroRemoteKeyDao.addAllRemoteKey(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }

            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {

            return MediatorResult.Error(e)

        }
    }



    private suspend fun getRemoteKeyClosetToCurrentPosition(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeyDao.getRemoteKeys(heroId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
            heroRemoteKeyDao.getRemoteKeys(heroId = hero.id)
        }

    }
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys?  {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero -> heroRemoteKeyDao.getRemoteKeys(heroId = hero.id) }
    }

//    private fun parseMillis(millis : Long) : String {
//        val date = Date(millis)
//        val format = SimpleDateFormat("yyy.MM.dd HH:mm", Locale.ROOT)
//        return  format.format(date)
//    }

}