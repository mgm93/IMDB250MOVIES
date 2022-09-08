package com.mgm.imdb250movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgm.imdb250movies.models.register.BodyRegister
import com.mgm.imdb250movies.models.register.ResponseRegister
import com.mgm.imdb250movies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) :ViewModel() {
    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun sendUserRegister(bodyRegister: BodyRegister)= viewModelScope.launch {
        loading.postValue(true)
        val res = repository.registerUser(bodyRegister)
        if(res.isSuccessful){
            registerUser.postValue(res.body())
        }
        loading.postValue(false)
    }
}