package com.babyapps.retrofitguide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.babyapps.retrofitguide.R
import com.babyapps.retrofitguide.model.RetroColor
import com.babyapps.retrofitguide.databinding.ItemRetrofitBinding
import com.babyapps.retrofitguide.other.GlideApp
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RetrofitAdapter : RecyclerView.Adapter<RetrofitAdapter.RetrofitViewHolder>() {

    inner class RetrofitViewHolder(val binding: ItemRetrofitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPhoto(color: RetroColor) {
            binding.apply {
                GlideApp.with(itemView)
                    .load("https://pngimage.net/wp-content/uploads/2018/05/android-blue-png-1.png")
                    .transition(DrawableTransitionOptions.withCrossFade()).error(R.drawable.error)
                    .into(imageview)
            }
        }


    }

    private val diffCallBack = object : DiffUtil.ItemCallback<RetroColor>() {
        override fun areContentsTheSame(oldItem: RetroColor, newItem: RetroColor): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: RetroColor, newItem: RetroColor): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallBack)
    var colors: List<RetroColor>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        return RetrofitViewHolder(
            ItemRetrofitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {
        holder.binding.apply {
            val color = colors[position]
            textViewAlbumId.text = color.albumId.toString()
            textViewId.text = color.id.toString()
            textViewTitle.text = color.title
            holder.bindPhoto(color)

        }
    }

    override fun getItemCount(): Int = differ.currentList.size

}