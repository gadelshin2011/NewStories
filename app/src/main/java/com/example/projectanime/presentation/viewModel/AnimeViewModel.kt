package com.example.projectanime.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.projectanime.data.remote.RetrofitClient
import com.example.projectanime.data.remote.model.ListAnimeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnimeViewModel(apl: Application) : AndroidViewModel(apl) {
    private val webRepo = RetrofitClient()

    private val _animeList: MutableStateFlow<List<ListAnimeModel>> = MutableStateFlow(
        emptyList()
    )

    val animeList = _animeList.asStateFlow()

    init {

    }

    suspend fun requestAnimeList(): Boolean {
        val result = webRepo.retrofit.getListAnime()

        return if (result.isSuccessful) {
            _animeList.value = result.body()!!.list
            true
        } else {
            false
        }


    }
}