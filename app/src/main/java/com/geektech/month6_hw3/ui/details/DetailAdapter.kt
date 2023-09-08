package com.geektech.month6_hw3.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geektech.month6_hw3.data.model.PlaylistItemModel
import com.geektech.month6_hw3.databinding.ItemDetailBinding

class DetailAdapter(private val onClick: (PlaylistItemModel.Item) -> Unit) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = mutableListOf<PlaylistItemModel.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(model: List<PlaylistItemModel.Item>) {
        if (list.isEmpty())list.addAll(model)
        else{
           list.clear()
            list.addAll(model)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    inner class DetailViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(playlistsModelItem: PlaylistItemModel.Item) {
            with(binding) {
                tvTitle.text = playlistsModelItem.snippet?.title
                tvDuration.text = playlistsModelItem.snippet?.publishedAt
                Glide.with(ivDetail).load(playlistsModelItem.snippet?.thumbnails?.default?.url)
                    .into(ivDetail)
                itemView.setOnClickListener {
                    onClick.invoke(playlistsModelItem)
                }
            }
        }
    }
}