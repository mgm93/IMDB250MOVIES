package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun callTopMovies(id: Int) = viewModelScope.launch {
        val res = repository.getTopMovies(id)
        if (res.isSuccessful) {
            topMoviesList.postValue(res.body())
        }
    }
}