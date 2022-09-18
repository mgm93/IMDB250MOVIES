package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.imdb250movies.db.FavMoviesEntity
import com.mgm.imdb250movies.models.home.ResponseMoviesList
import com.mgm.imdb250movies.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/18/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository): ViewModel() {
    val response = MutableLiveData<List<FavMoviesEntity>>()
    val emptyResponse = MutableLiveData<Boolean>()

    fun loadFavorite() = viewModelScope.launch {
        val res = repository.getAllFavoriteMovies()
            if (res.isNotEmpty()) {
                response.postValue(res)
                emptyResponse.postValue(false)
            } else {
                emptyResponse.postValue(true)
            }

    }
}