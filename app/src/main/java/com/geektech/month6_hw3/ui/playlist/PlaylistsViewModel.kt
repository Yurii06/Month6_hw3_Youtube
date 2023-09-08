package com.geektech.month6_hw3.ui.playlist

import androidx.lifecycle.LiveData
import com.geektech.month6_hw3.core.base.BaseViewModel
import com.geektech.month6_hw3.core.network.Resource
import com.geektech.month6_hw3.data.model.PlaylistsModel
import com.geektech.month6_hw3.data.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<Resource<PlaylistsModel>> {
        return repository.getPlaylist()
    }
}