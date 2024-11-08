package com.example.videoview

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.videoview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val videoList = listOf(
        R.raw.consistency, R.raw.consistencytwo, R.raw.gym
    )
    private var currentVideoIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        playVideo()
        
        binding.prevButton.setOnClickListener {
            currentVideoIndex = if (currentVideoIndex - 1 >= 0) currentVideoIndex - 1 else videoList.size - 1
            playVideo()
        }

        binding.nextButton.setOnClickListener {
            currentVideoIndex = (currentVideoIndex + 1) % videoList.size
            playVideo()
        }

        binding.videoView.setOnClickListener {
            if (binding.videoView.isPlaying) {
                binding.videoView.pause()
            } else {
                binding.videoView.start()
            }
        }
    }

    private fun playVideo() {
        val videoUri = Uri.parse("android.resource://$packageName/${videoList[currentVideoIndex]}")
        binding.videoView.setVideoURI(videoUri)
        binding.videoView.requestFocus()
        binding.videoView.start()
    }
}
