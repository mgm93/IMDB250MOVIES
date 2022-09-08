package com.mgm.imdb250movies.api

import com.mgm.imdb250movies.models.home.ResponseMoviesList
import com.mgm.imdb250movies.models.register.BodyRegister
import com.mgm.imdb250movies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMovies(@Path("genre_id") genreId:Int): Response<ResponseMoviesList>
}