package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.imdb250movies.db.FavMoviesEntity
import com.mgm.imdb250movies.models.detail.ResponseDetailMovie
import com.mgm.imdb250movies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/14/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository): ViewModel() {
    //api
    val detail = MutableLiveData<ResponseDetailMovie>()
    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovie(movieId:Int) = viewModelScope.launch {
        loading.postValue(true)
        val res = repository.getMovieDetail(movieId)
        if (res.isSuccessful) {
            detail.postValue(res.body())
        }
        loading.postValue(false)
    }

    //database
    val isFavorite = MutableLiveData<Boolean>()
    suspend fun existMovie(id:Int) = withContext(viewModelScope.coroutineContext){ repository.existsMovie(id)}

    fun favoriteMovie(id :  Int, movie: FavMoviesEntity) = viewModelScope.launch {
        val exist = repository.existsMovie(id)
        if (exist) {
            isFavorite.postValue(false)
            repository.deleteMovie(movie)
        }else {
            isFavorite.postValue(true)
            repository.insertMovie(movie)
        }
    }

}