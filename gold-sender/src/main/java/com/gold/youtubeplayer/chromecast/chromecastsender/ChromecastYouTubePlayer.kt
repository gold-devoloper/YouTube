package com.gold.youtubeplayer.chromecast.chromecastsender

import com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure.ChromecastCommunicationChannel
import com.gold.youtubeplayer.chromecast.chromecastsender.io.youtube.ChromecastCommunicationConstants
import com.gold.youtubeplayer.chromecast.chromecastsender.io.youtube.ChromecastYouTubeMessageDispatcher
import com.gold.youtubeplayer.chromecast.chromecastsender.utils.JSONUtils
import com.gold.youtubeplayer.core.player.PlayerConstants
import com.gold.youtubeplayer.core.player.YouTubePlayer
import com.gold.youtubeplayer.core.player.YouTubePlayerBridge
import com.gold.youtubeplayer.core.player.listeners.YouTubePlayerListener
import com.gold.youtubeplayer.core.player.toFloat

class ChromecastYouTubePlayer internal constructor(private val chromecastCommunicationChannel: ChromecastCommunicationChannel) : YouTubePlayer, YouTubePlayerBridge.YouTubePlayerBridgeCallbacks {

    private lateinit var youTubePlayerInitListener: (YouTubePlayer) -> Unit

    private val inputMessageDispatcher = ChromecastYouTubeMessageDispatcher(YouTubePlayerBridge(this))
    private val youTubePlayerListeners = HashSet<YouTubePlayerListener>()

    internal fun initialize(initListener: (YouTubePlayer) -> Unit) {
        youTubePlayerListeners.clear()

        youTubePlayerInitListener = initListener

        chromecastCommunicationChannel.addObserver(inputMessageDispatcher)
    }

    override fun onYouTubeIFrameAPIReady() {
        youTubePlayerInitListener(this)
    }

    override fun getInstance(): YouTubePlayer {
        return this
    }

    override fun loadVideo(videoId: String, startSeconds: Float) {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.LOAD,
                "videoId" to videoId,
                "startSeconds" to startSeconds.toString()
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun cueVideo(videoId: String, startSeconds: Float) {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.CUE,
                "videoId" to videoId,
                "startSeconds" to startSeconds.toString()
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun play() {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.PLAY
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun pause() {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.PAUSE
        )

        chromecastCommunicationChannel.sendMessage(message)
    }
    override fun mute() {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.MUTE
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun unMute() {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.UNMUTE
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun setVolume(volumePercent: Int) {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.SET_VOLUME,
                "volumePercent" to volumePercent.toString()
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun seekTo(time: Float) {
        val message = JSONUtils.buildFlatJson(
                "command" to ChromecastCommunicationConstants.SEEK_TO,
                "time" to time.toString()
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun setPlaybackRate(playbackRate: PlayerConstants.PlaybackRate) {
        val message = JSONUtils.buildFlatJson(
            "command" to ChromecastCommunicationConstants.SET_PLAYBACK_RATE,
            "playbackRate" to playbackRate.toFloat().toString()
        )

        chromecastCommunicationChannel.sendMessage(message)
    }

    override fun addListener(listener: YouTubePlayerListener): Boolean = youTubePlayerListeners.add(listener)
    override fun removeListener(listener: YouTubePlayerListener): Boolean = youTubePlayerListeners.remove(listener)
    override fun getListeners(): MutableCollection<YouTubePlayerListener> = youTubePlayerListeners
}