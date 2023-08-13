package com.thiago.dragonballzapp.di

import android.content.Context
import com.thiago.dragonballzapp.data.repository.DataStoreOperationImpl
import com.thiago.dragonballzapp.data.repository.Repository
import com.thiago.dragonballzapp.domain.repository.DataStoreOperation
import com.thiago.dragonballzapp.domain.use_cases.UseCases
import com.thiago.dragonballzapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.thiago.dragonballzapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.thiago.dragonballzapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.thiago.dragonballzapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStorageOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperation{
       return  DataStoreOperationImpl(context = context )
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository) : UseCases{
        return UseCases(
            saveOnBoardingUserCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )

    }
}