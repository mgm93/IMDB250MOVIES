package com.mgm.imdb250movies.repository

import com.mgm.imdb250movies.api.ApiService
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/11/2022.
 * Email: golmoradi.majid@gmail.com
 */
class SearchRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun searchMovies(name:String) = apiService.searchMovies(name)
}