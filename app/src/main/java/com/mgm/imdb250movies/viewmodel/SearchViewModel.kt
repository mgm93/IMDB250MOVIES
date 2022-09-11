package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.imdb250movies.models.home.ResponseMoviesList
import com.mgm.imdb250movies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/11/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {
    val response = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()
    val emptyResponse = MutableLiveData<Boolean>()

    fun loadSearch(name: String) = viewModelScope.launch {
        loading.postValue(true)
        val res = repository.searchMovies(name)
        if (res.isSuccessful) {
            if (res.body()!!.data.isNotEmpty()) {
                response.postValue(res.body())
                emptyResponse.postValue(false)
            } else {
                emptyResponse.postValue(true)
            }

        }
        loading.postValue(false)
    }
}