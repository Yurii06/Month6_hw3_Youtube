package com.geektech.month6_hw3.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.month6_hw3.BuildConfig
import com.geektech.month6_hw3.core.base.BaseViewModel
import com.geektech.month6_hw3.data.model.PlaylistsModel
import com.geektech.month6_hw3.core.network.Retrofit
import com.geektech.month6_hw3.utils.Constans
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : BaseViewModel() {

    private var _playlists: MutableLiveData<PlaylistsModel> = MutableLiveData<PlaylistsModel>()
    val playlists: LiveData<PlaylistsModel> = _playlists

    fun getPlayList() {
        Retrofit.api.getPlaylists(
            part = Constans.PART,
            channelId = Constans.CHANNEL_ID,
            key = BuildConfig.API_KEY,
            maxResults = 10
        ).enqueue(
            object : Callback<PlaylistsModel> {
                override fun onResponse(
                    call: Call<PlaylistsModel>,
                    response: Response<PlaylistsModel>
                ) {
                    if (response.isSuccessful) {
                        _playlists.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<PlaylistsModel>, t: Throwable) {
                    Log.e("ololo", t.message.toString())
                }

            }
        )
    }
}