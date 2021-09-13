package com.dheepak.giphytrending.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheepak.giphytrending.R
import com.dheepak.giphytrending.model.DataItem
import kotlinx.android.synthetic.main.trending_gifs_holder.view.*

class TrendingGifsListAdapter: PagingDataAdapter<DataItem, TrendingGifsListAdapter.ViewHolder>(DataDifferntiator) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(Uri.parse(getItem(position)?.images?.previewGif?.url))
            .placeholder(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(holder.itemView.gif_image)
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
}