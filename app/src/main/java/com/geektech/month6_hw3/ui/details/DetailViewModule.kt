package com.geektech.month6_hw3.ui.details

import androidx.lifecycle.LiveData
import com.geektech.month6_hw3.core.base.BaseViewModel
import com.geektech.month6_hw3.core.network.Resource
import com.geektech.month6_hw3.data.model.PlaylistItemModel
import com.geektech.month6_hw3.data.repository.Repository

class DetailViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItemModel>> {
        return repository.getPlaylistItems(playlistId)
    }
}