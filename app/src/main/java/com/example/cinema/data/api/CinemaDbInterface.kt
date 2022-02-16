package com.example.cinema.data.api

import com.example.cinema.data.vo.CinemaDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CinemaDbInterface {

    //add the endpoints of the URL

    @GET("movie/{movie_id}")

    fun getCinema(@Path("movie_id")id:Int) :Single<CinemaDetails>  //SIngle => observable in rx java

}