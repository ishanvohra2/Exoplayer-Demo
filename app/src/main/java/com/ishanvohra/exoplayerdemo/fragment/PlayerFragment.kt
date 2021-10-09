package com.ishanvohra.exoplayerdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.decoder.Buffer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.ui.StyledPlayerView.SHOW_BUFFERING_ALWAYS
import com.google.android.exoplayer2.util.MimeTypes
import com.ishanvohra.exoplayerdemo.R

class PlayerFragment : Fragment(R.layout.fragment_player) {

    var playerView: StyledPlayerView? = null

    private var player: SimpleExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mediaUrl = arguments?.getString("url")

        playerView = view.findViewById(R.id.player)
        setUpPlayer()
        if (mediaUrl != null) {
            addMediaItem(mediaUrl)
        }
    }

    private fun addMediaItem(mediaUrl: String) {

        //Creating a media item of HLS Type
        val mediaItem = MediaItem.Builder()
            .setUri(mediaUrl)
            .setMimeType(MimeTypes.APPLICATION_M3U8) //m3u8 is the extension used with HLS sources
            .build()

        player?.setMediaItem(mediaItem)

        player?.prepare()
        player?.repeatMode = Player.REPEAT_MODE_ONE //repeating the video from start after it's over
        player?.play()
    }

    private fun  setUpPlayer(){

        //initializing exoplayer
        player = SimpleExoPlayer.Builder(requireContext()).build()

        //set up audio attributes
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .setContentType(C.CONTENT_TYPE_MOVIE)
            .build()
        player?.setAudioAttributes(audioAttributes, false)

        playerView?.player = player

        //hiding all the ui StyledPlayerView comes with
        playerView?.setShowNextButton(false)
        playerView?.setShowPreviousButton(false)

        //setting the scaling mode to scale to fit
        player?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
    }

    companion object {
        fun newInstance(url: String) = PlayerFragment()
            .apply {
                arguments = Bundle().apply {
                    putString("url", url)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        playerView?.player?.play()
    }

    override fun onPause() {
        super.onPause()
        playerView?.player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        playerView?.player?.stop()
        playerView?.player?.release()
    }
}