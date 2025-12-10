package com.jikanAPI.data.repository

import com.jikanAPI.common.Resource
import com.jikanAPI.data.local.AnimeDatabase
import com.jikanAPI.data.local.entity.AnimeEntity
import com.jikanAPI.data.remote.JikanApi
import com.jikanAPI.domain.model.Anime
import com.jikanAPI.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi,
    private val db: AnimeDatabase
) : AnimeRepository {

    private val dao = db.dao

    override fun getTopAnime(): Flow<Resource<List<Anime>>> = flow {
        emit(Resource.Loading())

        val localAnime = dao.getAnimeList().map { it.toAnime() }
        emit(Resource.Loading(data = localAnime))

        try {
            val remoteAnime = api.getTopAnime()
            dao.clearAnimeList()
            dao.insertAnimeList(remoteAnime.data.map { dto ->
                AnimeEntity(
                    malId = dto.malId,
                    title = dto.title,
                    episodes = dto.episodes,
                    score = dto.score,
                    imageUrl = dto.images.jpg.imageUrl,
                    synopsis = dto.synopsis,
                    genres = dto.genres?.joinToString(",") { it.name } ?: "",
                    trailerUrl = dto.trailer?.url
                )
            })
            val newLocalAnime = dao.getAnimeList().map { it.toAnime() }
            emit(Resource.Success(newLocalAnime))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = localAnime
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = localAnime
            ))
        }
    }

    override fun getAnimeDetails(id: Int): Flow<Resource<Anime>> = flow {
        emit(Resource.Loading())

        val localAnime = dao.getAnime(id)?.toAnime()
        emit(Resource.Loading(data = localAnime))

        try {
            val remoteAnime = api.getAnimeDetails(id)
            val dto = remoteAnime.data
            val entity = AnimeEntity(
                malId = dto.malId,
                title = dto.title,
                episodes = dto.episodes,
                score = dto.score,
                imageUrl = dto.images.jpg.imageUrl,
                synopsis = dto.synopsis,
                genres = dto.genres?.joinToString(",") { it.name } ?: "",
                trailerUrl = dto.trailer?.url
            )
            dao.insertAnime(entity)
            emit(Resource.Success(entity.toAnime()))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = localAnime
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = localAnime
            ))
        }
    }
}
