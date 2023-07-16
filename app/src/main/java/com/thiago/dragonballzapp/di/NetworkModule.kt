package com.thiago.dragonballzapp.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.thiago.dragonballzapp.data.local.DragonballDatabase
import com.thiago.dragonballzapp.data.remote.DragonballApi
import com.thiago.dragonballzapp.data.repository.RemoteDataSourceImpl
import com.thiago.dragonballzapp.domain.repository.RemoteDataSource
import com.thiago.dragonballzapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton




@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideDragonballApi(retrofit: Retrofit): DragonballApi =
        retrofit.create(DragonballApi::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        dragonballApi: DragonballApi,
        dragonballDatabase: DragonballDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            dragonballApi =  dragonballApi,
            dragonballDatabase = dragonballDatabase
        )
    }

}