package com.thiago.dragonballzapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.thiago.dragonballzapp.data.repository.Repository
import com.thiago.dragonballzapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository : Repository
    ) {
    operator fun invoke(): Flow<PagingData<Hero>> {
        return  repository.getAllHeroes()
    }

}