package com.main.umtylacademyuser.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.MusicAdapter
import com.main.umtylacademyuser.utils.viewmodels.MusicViewModel

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.bottom_sheet_dialog_layout.*


class MusicFragment : Fragment(), Player.EventListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var musicViewModel: MusicViewModel

    private lateinit var simpleExoplayer: SimpleExoPlayer
    private var playbackPosition: Long = 0
    private val mp4Url = "https://html5demos.com/assets/dizzy.mp4"
//    private val dashUrl = "https://storage.googleapis.com/wvmedia/clear/vp9/tears/tears_uhd.mpd"
//    private lateinit var musicUrl: String
    private val urlList = listOf(mp4Url to "default")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_music, container, false)

        recyclerView = view.findViewById(R.id.musicRecycler)
        musicViewModel = ViewModelProvider(this)[MusicViewModel::class.java]

        musicViewModel.getListOfMusic()
        observeData()

        return view
    }

    private val dataSourceFactory: DataSource.Factory by lazy {
        DefaultDataSourceFactory(requireContext(), "exoplayer-sample")
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer() {
        simpleExoplayer = SimpleExoPlayer.Builder(requireContext()).build()
        val randomUrl = urlList.random()
        preparePlayer(randomUrl.first, randomUrl.second)
        playerView.player = simpleExoplayer
        simpleExoplayer.seekTo(playbackPosition)
        simpleExoplayer.playWhenReady = true
        simpleExoplayer.addListener(this)
    }

    private fun buildMediaSource(uri: Uri, type: String): MediaSource {
        return if (type == "dash") {
            DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        } else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri)
        }
    }

    private fun preparePlayer(videoUrl: String, type: String) {
        val uri = Uri.parse(videoUrl)
        val mediaSource = buildMediaSource(uri, type)
        simpleExoplayer.prepare(mediaSource)
    }

    private fun releasePlayer() {
        playbackPosition = simpleExoplayer.currentPosition
        simpleExoplayer.release()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_BUFFERING)
            progressBar.visibility = View.VISIBLE
        else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_ENDED)
            progressBar.visibility = View.INVISIBLE
    }


    private fun observeData() {
        Log.i("BBB", "smth")
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        musicViewModel.lst.observe(requireActivity(), Observer {
            Log.i("BBB", it.toString())
            recyclerView.adapter = MusicAdapter(requireContext(), musicViewModel.musicList)
        })
    }

//    override fun valueFromAdapter(data: String?) {
//        musicUrl = data!!
//    }
}