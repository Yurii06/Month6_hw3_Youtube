package com.geektech.month6_hw3.ui.play

import android.annotation.SuppressLint
import android.view.View
import com.geektech.month6_hw3.core.base.BaseActivity
import com.geektech.month6_hw3.databinding.ActivityPlayBinding
import com.geektech.month6_hw3.utils.ConnectionLiveData
import com.geektech.month6_hw3.utils.Constants.VIDEO_DESCRIPTION
import com.geektech.month6_hw3.utils.Constants.VIDEO_ITEM_ID
import com.geektech.month6_hw3.utils.Constants.VIDEO_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayActivity() : BaseActivity<ActivityPlayBinding, PlayViewModel>() {

    override fun inflateViewBinding(): ActivityPlayBinding =
        ActivityPlayBinding.inflate(layoutInflater)

    override val viewModel: PlayViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun initLiveData() {
        super.initLiveData()
        val getIntentItemId = intent.getStringExtra(VIDEO_ITEM_ID)
        val getIntentVideoId = intent.getStringExtra(VIDEO_ITEM_ID)
        val getIntentDesc = intent.getStringExtra(VIDEO_DESCRIPTION)
        val getIntentTitle = intent.getStringExtra(VIDEO_TITLE)
    }

    override fun initView() {
        super.initView()
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        with(binding) {
            tvTitle.text
        }
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