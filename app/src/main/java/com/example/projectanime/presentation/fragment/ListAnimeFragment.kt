package com.example.projectanime.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectanime.data.remote.model.ListAnimeModel
import com.example.projectanime.databinding.FragmentMainBinding
import com.example.projectanime.presentation.adapters.AnimeListAdapter
import com.example.projectanime.presentation.viewModel.AnimeViewModel
import kotlinx.coroutines.launch

class ListAnimeFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: AnimeViewModel
    private lateinit var adapter: AnimeListAdapter
    private var data : Boolean = true
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
        binding.rcViewListAnime.layoutManager
        binding.rcViewListAnime.adapter = adapter
    }

    private fun requestListAnime() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                data = viewModel.requestAnimeList()
            }
        }
    }

    private fun showAnimeList() {

        if (data){
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.animeList.collect {
                        adapter.setAnimeList(it)
                        //что-то нифига не работает
                    }

                }
            }
        } else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }


    }
}


