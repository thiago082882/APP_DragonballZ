package com.thiago.dragonballzapp.domain.use_cases.get_selected_hero

import com.thiago.dragonballzapp.data.repository.Repository
import com.thiago.dragonballzapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero =
        repository.getSelectedHero(heroId = heroId)
}