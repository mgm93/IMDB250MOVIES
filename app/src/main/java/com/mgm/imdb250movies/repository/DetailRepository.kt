package com.mgm.imdb250movies.repository

import com.mgm.imdb250movies.api.ApiService
import com.mgm.imdb250movies.db.FavMoviesDao
import com.mgm.imdb250movies.db.FavMoviesEntity
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
class DetailRepository @Inject constructor(private val apiService: ApiService, private val dao: FavMoviesDao) {
    //Api
    suspend fun getMovieDetail(movieId: Int) = apiService.detailMovie(movieId)
    //Database
    suspend fun insertMovie(entity: FavMoviesEntity) = dao.insertFavMovie(entity)
    suspend fun deleteMovie(entity: FavMoviesEntity) = dao.deleteFavMovie(entity)
    suspend fun existsMovie(id: Int) = dao.existFavMovie(id)
}