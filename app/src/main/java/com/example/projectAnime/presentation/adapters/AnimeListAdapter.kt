package com.example.projectAnime.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectAnime.data.remote.model.ListAnimeModel
import com.example.projectAnime.data.remote.model.Names
import com.example.projectAnime.data.remote.model.Posters
import com.example.projectAnime.databinding.RcviewItemAnimeBinding

class AnimeListAdapter : RecyclerView.Adapter<AnimeListAdapter.MyHolder>() {
    private var listItem: MutableList<ListAnimeModel> = mutableListOf()


    class MyHolder(
        private val binding: RcviewItemAnimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animeModel: ListAnimeModel) {
            setPoster(animeModel.posters)
            setName(animeModel.names)
        }

        private fun setPoster(posters: Map<String, Posters>) {
            Glide.with(itemView.context).load(posters["medium"]).into(binding.imageView2)
        }

        private fun setName(names: Map<String, String>) {
            binding.textView.text = names["ru"]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = RcviewItemAnimeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(listItem[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAnimeList(list: List<ListAnimeModel>) {
        listItem.addAll(list)
        notifyDataSetChanged()
    }


}