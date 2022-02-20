package com.example.cinema.data.vo

data class CinemaData(
    val Response: String,
    val Search: List<CinemaDetails>,
    val totalResults: String
)