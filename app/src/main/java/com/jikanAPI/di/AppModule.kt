package com.jikanAPI.di

import android.app.Application
import androidx.room.Room
import com.jikanAPI.data.local.AnimeDatabase
import com.jikanAPI.data.remote.JikanApi
import com.jikanAPI.data.repository.AnimeRepositoryImpl
import com.jikanAPI.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJikanApi(): JikanApi {
        val moshi = Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JikanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(app: Application): AnimeDatabase {
        return Room.databaseBuilder(
            app,
            AnimeDatabase::class.java,
            "anime_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(
        api: JikanApi,
        db: AnimeDatabase
    ): AnimeRepository {
        return AnimeRepositoryImpl(api, db)
    }
}
