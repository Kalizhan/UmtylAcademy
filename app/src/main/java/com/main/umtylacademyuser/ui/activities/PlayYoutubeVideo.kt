package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.Api_key
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_play_youtube_video.*

class PlayYoutubeVideo : YouTubeBaseActivity(), View.OnClickListener {

    private lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener
    private var videoUri: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_play_youtube_video)
//        setSupportActionBar(toolbar2)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true);
//        supportActionBar?.setDisplayShowHomeEnabled(true);

        initViews()
        btn2.setOnClickListener(this)
    }

    private fun initViews() {
        videoUri = intent.getStringExtra("videoUri").toString()
        youtubePlayerView = findViewById(R.id.youtube_player_view)
        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1!!.cueVideo(videoUri)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(
                    this@PlayYoutubeVideo,
                    resources.getString(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        youtubePlayerView.initialize(Api_key.API_KEY, onInitializedListener)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn2 -> {
                val intent = Intent(this, CourseActivity::class.java)
                finish()
                startActivity(intent)
            }
        }
    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }
}