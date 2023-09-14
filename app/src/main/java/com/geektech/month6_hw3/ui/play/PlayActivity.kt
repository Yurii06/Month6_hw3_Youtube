package com.geektech.month6_hw3.ui.play

import android.view.View
import android.app.AlertDialog
import android.os.Bundle
import com.geektech.month6_hw3.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.geektech.month6_hw3.core.base.BaseActivity
import com.geektech.month6_hw3.databinding.ActivityPlayBinding
import com.geektech.month6_hw3.utils.ConnectionLiveData
import com.geektech.month6_hw3.utils.Constants.VIDEO_DESCRIPTION
import com.geektech.month6_hw3.utils.Constants.VIDEO_ID
import com.geektech.month6_hw3.utils.Constants.VIDEO_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayActivity() : BaseActivity<ActivityPlayBinding, PlayViewModel>() {

    override fun inflateViewBinding(): ActivityPlayBinding =
        ActivityPlayBinding.inflate(layoutInflater)

    override val viewModel: PlayViewModel by viewModel()

    private var getIntentVideoId: String? = null
    private var getIntentDesc: String? = null
    private var getIntentTitle: String? = null

    private var exoPlayer: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private fun exoPlayerInit() {
        getIntentVideoId = intent.getStringExtra(VIDEO_ID) //didn't use it
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.playerView.player = exoPlayer
        exoPlayer?.playWhenReady = true
        val mediaItem =
            MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4") //change the link
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.seekTo(currentItem, playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private fun exoPlayerRelease() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            currentItem = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.release()
        }
        exoPlayer = null
    }

    override fun initView() {
        super.initView()
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        getIntentDesc = intent.getStringExtra(VIDEO_DESCRIPTION)
        getIntentTitle = intent.getStringExtra(VIDEO_TITLE)
        binding.tvTitle.text = getIntentTitle
        binding.tvDescr.text = getIntentDesc
    }

    override fun initListener() {
        super.initListener()
        binding.llBack.setOnClickListener {
            finish()
        }
        binding.btnDownload.setOnClickListener {
            val listItems = arrayOf("1080p", "720p", "480p")
            var colorSelected = listItems[0]
            val mBuilder = AlertDialog.Builder(this)
            mBuilder.setTitle(getString(R.string.select_video_quality))
            mBuilder.setSingleChoiceItems(listItems, -1) { dialogInterface, i ->
                colorSelected = listItems[i]
            }
            mBuilder.setNeutralButton(R.string.download) { dialog, _ ->
                dialog.cancel()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exoPlayerInit()
    }

    override fun onStart() {
        super.onStart()
        exoPlayerInit()
    }

    override fun onResume() {
        super.onResume()
        exoPlayerInit()
    }

    override fun onPause() {
        super.onPause()
        exoPlayerRelease()
    }

    override fun onStop() {
        super.onStop()
        exoPlayerRelease()
    }
}