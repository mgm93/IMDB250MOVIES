package com.mgm.imdb250movies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.imdb250movies.databinding.ItemHomeGenresBinding
import com.mgm.imdb250movies.models.home.ResponseGenresList
import com.mgm.imdb250movies.models.home.ResponseGenresList.*
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class GenresListAdapter @Inject constructor() : RecyclerView.Adapter<GenresListAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemHomeGenresBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListAdapter.ViewHolder {
        binding =
            ItemHomeGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: GenresListAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ResponseGenresListItem) {
            binding.apply {
                txtGenre.text = item.name

            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ResponseGenresListItem>() {
        override fun areItemsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenresListItem, newItem: ResponseGenresListItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}