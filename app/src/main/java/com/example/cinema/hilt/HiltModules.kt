package com.example.cinema.hilt

import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.data.repository.CinemaDetailsRepository
import com.example.cinema.utils.Constants

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)  //creates obj once when the app is started
@Module
object HiltModules {

    //things to inject
    @Singleton
    @Provides
    fun getApiInterface() : CinemaDbInterface {
         return  return Retrofit.Builder()
             .baseUrl(Constants.base_url)
             .addConverterFactory(GsonConverterFactory.create()) // we need to add converter factory to  convert JSON object to Java object
             .build()
             .create(CinemaDbInterface::class.java)
    }

    @Provides
    fun provideRepository(cinemaDbInterface: CinemaDbInterface) : CinemaDetailsRepository {
        return  CinemaDetailsRepository(cinemaDbInterface)

    }



}