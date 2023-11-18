package com.thiago.dragonballzapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.data.remote.FakeDbzApi2
import com.thiago.dragonballzapp.domain.model.Hero
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue


class HeroRemoteMediatorTest {
    private lateinit var dragonballApi: FakeDbzApi2
    private lateinit var dragonballDatabase: DragonballDatabase

    @Before
    fun setup() {
        dragonballApi = FakeDbzApi2()
        dragonballDatabase = DragonballDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup() {
        dragonballDatabase.clearAllTables()

    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnSuccessResultWhenMoreDataIsPresent() =
        runBlocking() {
            val remoteMediator = HeroRemoteMediator(
                dragonballApi = dragonballApi,
                dragonballDatabase = dragonballDatabase
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)

            assertFalse(
                (result as
                        RemoteMediator.MediatorResult.Success).endOfPaginationReached
            )

        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking() {
            dragonballApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                dragonballApi = dragonballApi,
                dragonballDatabase = dragonballDatabase
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue(
                (result as
                        RemoteMediator.MediatorResult.Success).endOfPaginationReached
            )

        }
    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsErrorResultWhenError()=
        runBlocking() {
            dragonballApi.addException()
            val remoteMediator = HeroRemoteMediator(
                dragonballApi = dragonballApi,
                dragonballDatabase = dragonballDatabase
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is RemoteMediator.MediatorResult.Error)


        }
}