package com.example.cinema.data.vo

data class CinemaResponse(
    val Response: String,
    val Search: List<Cinema>,
    val totalResults: String
)