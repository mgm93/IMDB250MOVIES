package com.mgm.imdb250movies.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Database(entities = [FavMoviesEntity::class], version = 1, exportSchema = false)
abstract class FavMovieDatabase : RoomDatabase() {
    abstract fun favMovieDao() : FavMoviesDao
}