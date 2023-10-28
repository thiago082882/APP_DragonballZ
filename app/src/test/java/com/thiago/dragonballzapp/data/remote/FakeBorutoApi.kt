package com.thiago.dragonballzapp.data.remote

import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.domain.model.ApiResponse
import com.thiago.dragonballzapp.domain.model.Hero

class FakeDbzApi : DragonballApi{
    private val heroes = listOf(
        Hero(
            id = 1,
            name = "Son Goku",
            image = "/images/goku.jpg",
            about = "Son Goku(カカロット, Kakarotto) is the main protagonist of the Dragon Ball series...",
            rating = 5.0,
            power = 100,
            month = "April",
            day = "16th",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 2,
            name = "Vegeta",
            image = "/images/vegeta.jpg",
            about = "Vegeta(ベジータ, Bejīta) is one of the main characters in the Dragon Ball series...",
            rating = 4.5,
            power = 95,
            month = "December",
            day = "24th",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 3,
            name = "Piccolo",
            image = "/images/picolo.png",
            about = "Piccolo(ピッコロ, Pikkoro) is a Namekian warrior and one of the main characters in Dragon Ball Z...",
            rating = 4.2,
            power = 80,
            month = "May",
            day = "9th",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
    )
    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
     val searchedHeroes = findHeroes(name=name)
        return  ApiResponse(
            success = true,
            message = "ok",
            heroes = searchedHeroes
        )
    }
    private fun findHeroes(name:String):List<Hero>{
        val founded = mutableListOf<Hero>()
        return if(name.isNotEmpty()){
            heroes.forEach{ hero->
                if(hero.name.lowercase().contains(name.lowercase())){
                    founded.add(hero)
                }
            }
            founded
        }else{
            emptyList()
        }
    }
}