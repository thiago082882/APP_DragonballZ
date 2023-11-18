package com.thiago.dragonballzapp.data.remote

import com.thiago.dragonballzapp.domain.model.ApiResponse
import com.thiago.dragonballzapp.domain.model.Hero
import java.io.IOException

class FakeDbzApi2 :DragonballApi{

        private val heroes : Map<Int,List<Hero>> by lazy {
            mapOf(
                1 to page1,
                2 to page2,
                3 to page3,
                4 to page4,
                5 to page5
            )

        }

    private var page1 = listOf(
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
        )

    )
    private var page2 = listOf(
        Hero(
            id = 4,
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
            id = 5,
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
            id = 6,
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
        )

    )
    private var page3 = listOf(
        Hero(
            id = 7,
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
            id = 8,
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
            id = 9,
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
        )

    )
    private var page4 = listOf(
        Hero(
            id = 10,
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
            id =11,
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
            id = 12,
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
        )

    )
    private var page5 = listOf(
        Hero(
            id = 13,
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
            id = 14,
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
            id = 15,
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
        )

    )
    fun clearData(){
        page1 = emptyList()
    }
    private var exception = false
    fun addException(){
        exception = true
    }

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        if(exception){
            throw  IOException()
        }
      require(page in 1..5)
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculate(page=page)["prevPage"],
            nextPage = calculate(page = page)["nextPage"],
            heroes = heroes[page]!!
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse {
        return  ApiResponse(
            success = false
        )
    }
private fun calculate(page:Int): Map<String,Int?>{
    if(page1.isEmpty()){
        return mapOf("prevPage" to null,"nextPage" to null)
    }
    var prevPage : Int? = page
    var nextPage : Int? = page
    if(page in 1..4){
        nextPage = nextPage?.plus(1)
    }
    if(page in 2..5){
       prevPage = prevPage?.minus(1)
    }
    if(page in 1..4){
       prevPage= null
    }
    if(page ==5){
        nextPage = null
    }
    return  mapOf("prevPage" to prevPage,"nextPage" to nextPage)
}

}