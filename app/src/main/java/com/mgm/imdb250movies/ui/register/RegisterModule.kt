package com.mgm.imdb250movies.ui.register

import com.mgm.imdb250movies.models.register.BodyRegister
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class RegisterModule {
    @Provides
    @Singleton
    fun provideUserBody():BodyRegister = BodyRegister()
}