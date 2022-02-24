package com.example.cinema.hilt

import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.utils.Constants.base_url
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)  //creates obj once when the is started
@Module
object HiltModules {

    //things to inject
    fun getApiInterface() : CinemaDbInterface {
         return  return Retrofit.Builder()
             .baseUrl(base_url)
             .addConverterFactory(GsonConverterFactory.create()) // we need to add converter factory to  convert JSON object to Java object
             .build()
             .create(CinemaDbInterface::class.java)
    }


}