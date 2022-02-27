package com.example.cinema.data.api

import com.example.cinema.data.vo.CinemaResponse
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface CinemaDbInterface {

    //add the endpoints of the URL

    @GET("/")
    suspend fun getCinema(

        @Query("s") s :String,
        @Query("page") page :Int,
        @Query("apikey") apikey : String,

    ) : Response<CinemaResponse>

}