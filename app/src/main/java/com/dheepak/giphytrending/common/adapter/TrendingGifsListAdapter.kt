package com.dheepak.giphytrending.common.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheepak.giphytrending.R
import com.dheepak.giphytrending.common.model.DataItem
import kotlinx.android.synthetic.main.trending_gifs_holder.view.*

class TrendingGifsListAdapter(private val onclick: OnCLick?,
                              private val isFromFavoritesFragment: Boolean = false,
                              private var favorites: List<DataItem> = listOf()): PagingDataAdapter<DataItem, TrendingGifsListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if(isFromFavoritesFragment || favorites.contains(item)) {
            holder.itemView.favorite.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ff0000"))
        } else {
            holder.itemView.favorite.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
        }
        Glide.with(holder.itemView.context)
            .load(Uri.parse(getItem(position)?.images?.previewGif?.url))
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(holder.itemView.gif_image)
        holder.itemView.favorite.setOnClickListener {
            onclick?.performOperationForFavoriteClick(item!!)
        }
    }

    fun updateFavorites(favorites: List<DataItem>) {
        this.favorites = favorites
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.trending_gifs_holder, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<DataItem>() {

        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

    interface OnCLick {
        fun performOperationForFavoriteClick(dataItem: DataItem)
    }
}