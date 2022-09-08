package com.mgm.imdb250movies.repository

import com.mgm.imdb250movies.api.ApiService
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class HomeRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getTopMovies(id: Int) = apiService.getTopMovies(id)
}