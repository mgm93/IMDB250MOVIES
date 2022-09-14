package com.mgm.imdb250movies.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mgm.imdb250movies.databinding.ItemDetailImageBinding
import javax.inject.Inject

/**
 * Created by Majid-Golmoradi on 9/8/2022.
 * Email: golmoradi.majid@gmail.com
 */
class ActorsImagesAdapter @Inject constructor() : RecyclerView.Adapter<ActorsImagesAdapter.ViewHolder>() {
    //Binding
    private lateinit var binding: ItemDetailImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsImagesAdapter.ViewHolder {
        binding =
            ItemDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ActorsImagesAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: String) {
            binding.apply {
                actorImg.load(item) {
                    crossfade(true)
                    crossfade(800)
                }

            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)
}