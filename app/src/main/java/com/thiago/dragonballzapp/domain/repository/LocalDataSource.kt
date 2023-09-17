package com.thiago.dragonballzapp.domain.repository

import com.thiago.dragonballzapp.domain.model.Hero



interface LocalDataSource {

    suspend fun getSelectedHero(heroId: Int): Hero
}