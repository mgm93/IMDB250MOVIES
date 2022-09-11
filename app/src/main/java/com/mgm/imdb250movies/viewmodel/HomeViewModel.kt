package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.imdb250movies.models.home.ResponseGenresList
import com.mgm.imdb250movies.models.home.ResponseMoviesList
import com.mgm.imdb250movies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenresList>()
    val lastMoviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()

    fun loadTopMovies(id: Int) = viewModelScope.launch {
        val res = repository.getTopMovies(id)
        if (res.isSuccessful) {
            topMoviesList.postValue(res.body())
        }
    }

    fun loadGenres() = viewModelScope.launch {
        val res = repository.getGenresList()
        if (res.isSuccessful)
            genresList.postValue(res.body())
    }

    fun loadLastMovies() = viewModelScope.launch {
        loading.postValue(true)
        val res = repository.getLastMovies()
        if (res.isSuccessful) {
            lastMoviesList.postValue(res.body())
        }
        loading.postValue(false)
    }
}