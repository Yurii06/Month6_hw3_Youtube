package com.geektech.month6_hw3.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.month6_hw3.core.network.RemoteDataSource
import com.geektech.month6_hw3.core.network.Resource
import com.geektech.month6_hw3.data.model.PlaylistItemModel
import com.geektech.month6_hw3.data.model.PlaylistsModel
import kotlinx.coroutines.Dispatchers

class Repository(private val remoteDataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<Resource<PlaylistsModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getPlaylists())
        }
    }

    fun getPlaylistItems(playlistId: String): LiveData<Resource<PlaylistItemModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            emit(remoteDataSource.getPlaylistItems(playlistId))
        }
    }
}