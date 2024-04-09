package com.example.projectanime.data.remote

import com.example.projectanime.data.remote.model.MainModel
import retrofit2.Response
import retrofit2.http.GET

interface NetworkAPI {
    @GET("/v3/title?id=9000")
    suspend fun getListAnime(): Response<MainModel>
}