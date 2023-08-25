package com.geektech.month6_hw3.ui.main

import androidx.lifecycle.ViewModelProvider
import com.geektech.month6_hw3.core.base.BaseActivity
import com.geektech.month6_hw3.databinding.ActivityMainBinding

class PlaylistsActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun initViewModel(): MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    private val adapter = PlaylistAdapter()

    override fun initLiveData() {
        super.initLiveData()
        viewModel.playlists.observe(this) {
            adapter.setList(it)
        }
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        viewModel.getPlayList()
    }
}