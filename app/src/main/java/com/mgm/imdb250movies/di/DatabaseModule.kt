package com.mgm.imdb250movies.di

import android.content.Context
import androidx.room.Room
import com.mgm.imdb250movies.db.FavMovieDatabase
import com.mgm.imdb250movies.db.FavMoviesDao
import com.mgm.imdb250movies.db.FavMoviesEntity
import com.mgm.imdb250movies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, FavMovieDatabase::class.java, Constants.FAV_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: FavMovieDatabase)= db.favMovieDao()

    @Provides
    @Singleton
    fun provideFavMovieEntity() = FavMoviesEntity()
}