package com.example.projectAnime.data.remote

import com.example.projectAnime.data.remote.model.MainModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {
    @GET("/v3/title?id=9000")
    suspend fun getListAnime(): Response<MainModel>
}