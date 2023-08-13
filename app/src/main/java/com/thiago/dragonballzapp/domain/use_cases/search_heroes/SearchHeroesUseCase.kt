package com.thiago.dragonballzapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.thiago.dragonballzapp.data.repository.Repository
import com.thiago.dragonballzapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(    private val repository : Repository) {
operator fun invoke(query : String) : Flow<PagingData<Hero>>{
    return  repository.searchHeroes(query = query)
}
}