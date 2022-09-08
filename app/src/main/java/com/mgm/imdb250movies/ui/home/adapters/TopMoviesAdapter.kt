package com.mgm.imdb250movies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.imdb250movies.databinding.ItemHomeTopMovieBinding
import com.mgm.imdb250movies.models.home.ResponseMoviesList.Data
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class TopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemHomeTopMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesAdapter.ViewHolder {
        binding =
            ItemHomeTopMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: TopMoviesAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = 5

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Data) {
            binding.apply {
                imgCover.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                txtName.text = item.title
                txtInfo.text = " ${item.imdbRating} |  ${item.year} | ${item.country}"
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}