package com.mgm.imdb250movies.repository

import com.mgm.imdb250movies.db.FavMoviesDao
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/18/2022.
 * Email: golmoradi.majid@gmail.com
 */
class FavoriteRepository @Inject constructor(private val favMoviesDao: FavMoviesDao) {
   suspend fun getAllFavoriteMovies() = favMoviesDao.getAllFavMovie()
}