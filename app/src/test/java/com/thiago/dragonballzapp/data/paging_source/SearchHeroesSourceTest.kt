package com.thiago.dragonballzapp.data.paging_source

import androidx.paging.PagingSource
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.data.remote.FakeDbzApi
import com.thiago.dragonballzapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Before
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class SearchHeroesSourceTest {

    private lateinit var dragonballApi: DragonballApi
    private lateinit var heroes: List<Hero>

    @Before
    fun setup() {
        dragonballApi = FakeDbzApi()
        heroes = listOf(
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
    }

    @Test
    fun `Search api with existing hero name,expect single hero result, assert LoadResult_Page`() =
        runBlocking {
            val heroSource = SearchHeroesSource(dragonballApi = dragonballApi, query = "Goku")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )


        }

    @Test
    fun `Search api with existing hero name,expect multiple hero result, assert LoadResult_Page`() =
        runBlocking {
            val heroSource = SearchHeroesSource(dragonballApi = dragonballApi, query = "Go")
            assertEquals<PagingSource.LoadResult<Int, Hero>>(
                expected = PagingSource.LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )


        }

    @Test
    fun `Search api with empty hero name,assert empty heroes list LoadResult_Page`() =
        runBlocking {
            val heroSource = SearchHeroesSource(dragonballApi = dragonballApi, query = "")
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = dragonballApi.searchHeroes("").heroes
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }


        }

    @Test
    fun `Search api with non_existing hero name,assert empty heroes list LoadResult_Page`() =
        runBlocking {
            val heroSource = SearchHeroesSource(dragonballApi = dragonballApi, query = "Unknown")
            val loadResult = heroSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result = dragonballApi.searchHeroes("Unknown").heroes
            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }


        }
}