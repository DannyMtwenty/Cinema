package com.example.cinema.data.api

import retrofit2.Call
import retrofit2.http.GET

interface TestInterface {

    @GET("programs")   //includes endpoint
    fun getPrograms() : Call<List<ProgramsItem>>
}