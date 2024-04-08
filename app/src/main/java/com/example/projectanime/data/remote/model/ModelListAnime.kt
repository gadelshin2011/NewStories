package com.example.projectanime.data.remote.model

data class ModelListAnime(
    val code: String,
    val id: Int,
    val names: Names,
    val announce: String,
    val status: Status,
    val posters: Posters,
    val updated: Int,
    val last_change: Int

)