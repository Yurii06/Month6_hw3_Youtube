package com.geektech.month6_hw3.core.network

import com.geektech.month6_hw3.BuildConfig
import com.geektech.month6_hw3.core.base.BaseDataSource
import com.geektech.month6_hw3.data.remote.PlaylistApiService
import com.geektech.month6_hw3.utils.Constants

class RemoteDataSource(private val apiService: PlaylistApiService) : BaseDataSource() {

    suspend fun getPlaylists() = getResult {
        apiService.getPlaylists(
            key = BuildConfig.API_KEY,
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            maxResults = 10
        )
    }

    suspend fun getPlaylistItems(playlistId: String) = getResult {
        apiService.getPlaylistItems(
            key = BuildConfig.API_KEY,
            part = Constants.PART,
            playlistId = playlistId,
            maxResults = 20
        )
    }
}