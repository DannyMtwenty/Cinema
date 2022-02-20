package com.example.cinema.data.api

import com.example.cinema.data.vo.CinemaData
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CinemaDbInterface {

    //add the endpoints of the URL

    @GET()
    fun getCinema(

        @Query("s") s :String,
        @Query("page") page :Int,
        @Query("apikey") apikey : String,

    ) : Call<CinemaData>

}