package com.example.projectanime.data.remote

import com.example.projectanime.data.remote.model.ModelListAnime
import retrofit2.http.GET

interface NetworkAPI {
    @GET("v3/title")
    suspend fun getListAnime(): Result<ModelListAnime>
}