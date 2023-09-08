package com.geektech.month6_hw3.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.geektech.month6_hw3.core.base.BaseActivity
import com.geektech.month6_hw3.core.network.Resource
import com.geektech.month6_hw3.data.model.PlaylistItemModel
import com.geektech.month6_hw3.databinding.ActivityDetailBinding
import com.geektech.month6_hw3.ui.play.PlayActivity
import com.geektech.month6_hw3.utils.ConnectionLiveData
import com.geektech.month6_hw3.utils.Constants.PLAYLIST_DESCRIPTION
import com.geektech.month6_hw3.utils.Constants.PLAYLIST_ID
import com.geektech.month6_hw3.utils.Constants.PLAYLIST_TITLE
import com.geektech.month6_hw3.utils.Constants.VIDEO_DESCRIPTION
import com.geektech.month6_hw3.utils.Constants.VIDEO_ITEM_COUNT
import com.geektech.month6_hw3.utils.Constants.VIDEO_ITEM_ID
import com.geektech.month6_hw3.utils.Constants.VIDEO_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    override fun inflateViewBinding(): ActivityDetailBinding =
        ActivityDetailBinding.inflate(layoutInflater)

    override val viewModel: DetailViewModel by viewModel()

    private val adapter = DetailAdapter(this::onClick)

    private fun onClick(item: PlaylistItemModel.Item) {
        val intent = Intent(this, PlayActivity::class.java)
        intent.putExtra(VIDEO_ITEM_ID, item.id)
        intent.putExtra(VIDEO_TITLE, item.snippet?.title)
        intent.putExtra(VIDEO_DESCRIPTION, item.snippet?.description)
    }

    @SuppressLint("SetTextI18n")
    override fun initLiveData() {
        super.initLiveData()
        val getIntentId = intent.getStringExtra(PLAYLIST_ID)
        val getIntentDesc = intent.getStringExtra(PLAYLIST_DESCRIPTION)
        val getIntentTitle = intent.getStringExtra(PLAYLIST_TITLE)
        val getIntentItemCount = intent.getStringExtra(VIDEO_ITEM_COUNT)

        viewModel.getPlaylistItems(getIntentId!!).observe(this) { response ->
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, "success status", Toast.LENGTH_SHORT).show()
                    response.data?.let { adapter.setList(it.items) }
                    viewModel.loading.value = false
                    binding.tvTitle.text = getIntentTitle
                    binding.tvDesc.text = getIntentDesc
                    binding.tvNumberOfSeries.text = "$getIntentItemCount video series"
                }

                Resource.Status.ERROR -> {
                    Toast.makeText(this, "error status", Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = false
                }

                Resource.Status.LOADING -> {
                    Toast.makeText(this, "loading status", Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = true
                }
            }
            viewModel.loading.observe(this) { status ->
                if (status) binding.progressBar.visibility = View.VISIBLE
                else binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.includeRecyclerView.recyclerView.adapter = adapter
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun initListener() {
        super.initListener()
        binding.llBack.setOnClickListener {
            finish()
        }
    }

    override fun checkInternetConnection() {
        super.checkInternetConnection()
        ConnectionLiveData(application).observe(this) { isConnection ->
            if (!isConnection) {
                binding.mainContainer.visibility = View.GONE
                binding.noConnection.visibility = View.VISIBLE
            }
            binding.noInternetConnectionInclude.btnTryAgain.setOnClickListener {
                if (!isConnection) {
                    binding.mainContainer.visibility = View.GONE
                    binding.noConnection.visibility = View.VISIBLE
                } else {
                    binding.mainContainer.visibility = View.VISIBLE
                    binding.noConnection.visibility = View.GONE
                }
            }
        }
    }
}