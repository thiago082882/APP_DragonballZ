package com.thiago.dragonballzapp.domain.use_cases

import com.thiago.dragonballzapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.thiago.dragonballzapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.thiago.dragonballzapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.thiago.dragonballzapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import javax.inject.Inject

data class  UseCases(
    val saveOnBoardingUserCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCase,
//    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)


