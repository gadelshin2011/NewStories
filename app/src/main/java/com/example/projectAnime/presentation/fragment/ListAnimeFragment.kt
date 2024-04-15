package com.example.projectAnime.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectAnime.databinding.FragmentMainBinding
import com.example.projectAnime.presentation.adapters.AnimeListAdapter
import com.example.projectAnime.presentation.viewModel.AnimeViewModel
import kotlinx.coroutines.launch

class ListAnimeFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: AnimeViewModel
    private lateinit var adapter: AnimeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        showAnimeList()
        requestListAnime()


    }

    private fun init() {
        adapter = AnimeListAdapter()
        binding.rcViewListAnime.layoutManager = LinearLayoutManager(context)
        binding.rcViewListAnime.adapter = adapter
    }

    private fun requestListAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.requestAnimeList()
            }
        }
    }
    private fun showAnimeList() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.animeList.collect {
                    adapter.setAnimeList(it)
                }
            }
        }
    }
}



