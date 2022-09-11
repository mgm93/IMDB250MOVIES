package com.mgm.imdb250movies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.imdb250movies.databinding.ItemHomeLastMoviesBinding
import com.mgm.imdb250movies.models.home.ResponseMoviesList.Data
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class LastMoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<LastMoviesAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemHomeLastMoviesBinding
    //other
    private var moviesList = emptyList<Data>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastMoviesAdapter.ViewHolder {
        binding =
            ItemHomeLastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LastMoviesAdapter.ViewHolder, position: Int) {
        holder.bind(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Data) {
            binding.apply {
                imgPoster.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                txtName.text = item.title
                txtRate.text = item.imdbRating
                txtCountry.text = item.country
                txtYear.text = item.year
            }
        }
    }

    fun setData(newItem: List<Data>){
        val moviesDiffUtil = MoviesDiffUtil(moviesList, newItem)
        val diffUtil = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = newItem
        diffUtil.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtil(private val oldItem: List<Data>, private val newItem: List<Data>) :
        DiffUtil.Callback() {
        override fun getOldListSize() = oldItem.size

        override fun getNewListSize() = newItem.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition].id === newItem[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }
}