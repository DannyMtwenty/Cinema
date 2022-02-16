package com.example.cinema.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED

}

class NetworkState(val status: Status,val string: String) {
    //static properties
    companion object{
        val Loaded : NetworkState
        val Loading : NetworkState
        val Error : NetworkState


    //initialize properties
    init {
        Loaded=NetworkState(Status.SUCCESS,"success")
        Loading=NetworkState(Status.RUNNING,"running")
        Error=NetworkState(Status.FAILED,"Something went wrong")
    }
    }
}