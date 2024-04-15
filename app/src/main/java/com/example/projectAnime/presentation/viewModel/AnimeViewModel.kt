package com.example.projectAnime.presentation.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.projectAnime.data.remote.RetrofitClient
import com.example.projectAnime.data.remote.model.ListAnimeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeViewModel(apl: Application) : AndroidViewModel(apl) {
    private val webRepo = RetrofitClient()

    private val _animeList: MutableStateFlow<List<ListAnimeModel>> = MutableStateFlow(
        emptyList()
    )

    val animeList = _animeList.asStateFlow()

    init {

    }

    suspend fun requestAnimeList() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = webRepo.retrofit.getListAnime()
                _animeList.value = result.body()!!.list
            }
        }
    }


