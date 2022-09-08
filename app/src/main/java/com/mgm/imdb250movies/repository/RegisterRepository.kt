package com.mgm.imdb250movies.repository

import com.mgm.imdb250movies.api.ApiService
import com.mgm.imdb250movies.models.register.BodyRegister
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
class RegisterRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun registerUser(bodyRegister: BodyRegister) = apiService.registerUser(bodyRegister)
}