package com.mgm.imdb250movies.api

import com.mgm.imdb250movies.models.detail.ResponseDetailMovie
import com.mgm.imdb250movies.models.home.ResponseGenresList
import com.mgm.imdb250movies.models.home.ResponseMoviesList
import com.mgm.imdb250movies.models.register.BodyRegister
import com.mgm.imdb250movies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body bodyRegister: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMovies(@Path("genre_id") genreId:Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun getGenresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun getLastMovies(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovies(@Query("q") name:String): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") id:Int): Response<ResponseDetailMovie>

}