package com.gold.youtubeplayer.chromecast.sampleapp.examples.basicExample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastContext
import com.gold.youtubeplayer.chromecast.chromecastsender.ChromecastYouTubePlayerContext
import com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure.ChromecastConnectionListener
import com.gold.youtubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.gold.uzplayer.R
import com.gold.youtubeplayer.chromecast.sampleapp.utils.MediaRouteButtonUtils
import com.gold.youtubeplayer.chromecast.chromecastsender.utils.PlayServicesUtils
import com.gold.youtubeplayer.core.player.YouTubePlayer
import com.gold.youtubeplayer.chromecast.sampleapp.utils.VideoIdsProvider
import com.gold.youtubeplayer.core.player.PlayerConstants
import com.gold.youtubeplayer.core.player.toFloat

class BasicExampleActivity : AppCompatActivity() {

    private val googlePlayServicesAvailabilityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_example)

        val mediaRouteButton = findViewById<MediaRouteButton>(R.id.media_route_button)

        MediaRouteButtonUtils.initMediaRouteButton(mediaRouteButton)

        // can't use CastContext until I'm sure the user has GooglePlayServices
        PlayServicesUtils.checkGooglePlayServicesAvailability(this, googlePlayServicesAvailabilityRequestCode, Runnable { initChromecast() })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // can't use CastContext until I'm sure the user has GooglePlayServices
        if(requestCode == googlePlayServicesAvailabilityRequestCode)
            PlayServicesUtils.checkGooglePlayServicesAvailability(this, googlePlayServicesAvailabilityRequestCode, Runnable { initChromecast() })
    }

    private fun initChromecast() {
        ChromecastYouTubePlayerContext(CastContext.getSharedInstance(this).sessionManager, SimpleChromecastConnectionListener())
    }

    inner class SimpleChromecastConnectionListener: ChromecastConnectionListener {
        override fun onChromecastConnecting() {
            Log.d(javaClass.simpleName, "onChromecastConnecting")
        }

        override fun onChromecastConnected(chromecastYouTubePlayerContext: ChromecastYouTubePlayerContext) {
            Log.d(javaClass.simpleName, "onChromecastConnected")

            initializeCastPlayer(chromecastYouTubePlayerContext)
        }

        override fun onChromecastDisconnected() {
            Log.d(javaClass.simpleName, "onChromecastDisconnected")
        }

        private fun initializeCastPlayer(chromecastYouTubePlayerContext: ChromecastYouTubePlayerContext) {
            chromecastYouTubePlayerContext.initialize(object: AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(VideoIdsProvider.getNextVideoId(), 0f)

                    initPlaybackSpeedButtons(youTubePlayer)
                }

                override fun onPlaybackRateChange(youTubePlayer: YouTubePlayer, playbackRate: PlayerConstants.PlaybackRate) {
                    val playbackSpeedTextView = findViewById<TextView>(R.id.playback_speed_text_view)
                    playbackSpeedTextView.text = "Playback speed: ${playbackRate.toFloat()}"
                }
            })
        }
    }

    fun initPlaybackSpeedButtons(youTubePlayer: YouTubePlayer) {
        val playbackSpeed_0_25 = findViewById<Button>(R.id.playback_speed_0_25)
        val playbackSpeed_1 = findViewById<Button>(R.id.playback_speed_1)
        val playbackSpeed_2 = findViewById<Button>(R.id.playback_speed_2)

        playbackSpeed_0_25.setOnClickListener { youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_0_25)  }
        playbackSpeed_1.setOnClickListener { youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_1) }
        playbackSpeed_2.setOnClickListener { youTubePlayer.setPlaybackRate(PlayerConstants.PlaybackRate.RATE_2) }
    }
}
