package com.thiago.dragonballzapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.domain.model.Hero
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val dragonballApi: DragonballApi,
    private val query: String
) : PagingSource<Int, Hero>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try{

            val apiResponse = dragonballApi.searchHeroes(name=query)
            val heroes = apiResponse.heroes
            if(heroes.isNotEmpty()){
                LoadResult.Page(
                    data = heroes,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey =null
                )
            }
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return  state.anchorPosition
    }


}