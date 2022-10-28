package com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure

import com.gold.youtubeplayer.chromecast.chromecastsender.ChromecastYouTubePlayerContext

/**
 * Implement this interface to be notified about changes in the cast connection.
 */
interface ChromecastConnectionListener {
    fun onChromecastConnecting()
    fun onChromecastConnected(chromecastYouTubePlayerContext: ChromecastYouTubePlayerContext)
    fun onChromecastDisconnected()
}