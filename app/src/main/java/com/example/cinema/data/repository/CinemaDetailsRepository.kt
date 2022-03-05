package com.example.cinema.data.repository

import com.example.cinema.data.api.CinemaDbInterface
import com.example.cinema.data.vo.cinemaDetails.CinemaItem

import com.example.cinema.data.vo.cinemaDetails.cinemaDetails
import com.example.cinema.utils.Constants
import com.example.cinema.utils.Result
import com.example.cinema.utils.Status

class CinemaDetailsRepository(private val cinemaDbInterface: CinemaDbInterface) {

    suspend fun getCinemaDetails(imdbId: String) : Result<CinemaItem>{

        return  try {
            val response =cinemaDbInterface.getCinemaDetails(imdbId, Constants.api_key)
            if (response.isSuccessful){
                Result(Status.SUCCESS,response.body(),null)

            }else{
                Result(Status.ERROR,null,null)
            }


        }catch (e:Exception){
           Result(Status.ERROR,null,null)
        }
    }
}