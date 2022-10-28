package com.gold.youtubeplayer.chromecast.sampleapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.gold.youtubeplayer.chromecast.chromecastsender.ChromecastYouTubePlayerContext
import com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure.ChromecastConnectionListener

/**
 * This broadcast receiver is used to react to notification actions.
 */
class PlaybackControllerBroadcastReceiver(
        var togglePlayback: () -> Unit = { Log.d(PlaybackControllerBroadcastReceiver::class.java.simpleName, "no-op") }
    ): BroadcastReceiver(), ChromecastConnectionListener {

    companion object {
        const val TOGGLE_PLAYBACK = "com.pierfrancescosoffritti.youtubeplayer.chromecast.sampleapp.TOGGLE_PLAYBACK"
        const val STOP_CAST_SESSION = "com.pierfrancescosoffritti.youtubeplayer.chromecast.sampleapp.STOP_CAST_SESSION"
    }

    private lateinit var chromecastYouTubePlayerContext: ChromecastYouTubePlayerContext

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(javaClass.simpleName, "intent received ${intent.action}")
        when(intent.action) {
            TOGGLE_PLAYBACK -> togglePlayback()
            STOP_CAST_SESSION -> chromecastYouTubePlayerContext.endCurrentSession()
        }
    }

    override fun onChromecastConnected(chromecastYouTubePlayerContext: ChromecastYouTubePlayerContext) {
        this.chromecastYouTubePlayerContext = chromecastYouTubePlayerContext
    }

    override fun onChromecastConnecting() {

    }

    override fun onChromecastDisconnected() {

    }
}