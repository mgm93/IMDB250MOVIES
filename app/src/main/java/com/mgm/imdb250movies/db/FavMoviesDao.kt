package com.mgm.imdb250movies.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mgm.imdb250movies.utils.Constants

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Dao
interface FavMoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertFavMovie(entity: FavMoviesEntity)

    @Delete
    suspend fun deleteFavMovie(entity: FavMoviesEntity)

    @Query("SELECT * FROM ${Constants.FAV_TABLE}")
    fun getAllFavMovie(): MutableList<FavMoviesEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM  ${Constants.FAV_TABLE} WHERE id = :movieId)")
    suspend fun existFavMovie(movieId: Int):Boolean
}