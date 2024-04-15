package com.example.projectAnime.data.remote.model

data class ListAnimeModel(
    val code: String,
    val names: Map<String,String>,
    val posters: Map<String,Posters>
)