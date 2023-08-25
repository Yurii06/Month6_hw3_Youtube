package com.geektech.month6_hw3.data.remote

import com.geektech.month6_hw3.BuildConfig
import com.geektech.month6_hw3.data.model.PlaylistsModel
import com.geektech.month6_hw3.utils.Constans
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int,
    ): Call<PlaylistsModel>
}