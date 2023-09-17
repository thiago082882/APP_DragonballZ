package com.thiago.dragonballzapp.data.repository


import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.domain.model.Hero
import com.thiago.dragonballzapp.domain.repository.LocalDataSource

class LocalDataSourceImpl(dragonballDatabase: DragonballDatabase) : LocalDataSource {

    private val heroDao = dragonballDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero =
        heroDao.getSelectedHero(heroId = heroId)
}