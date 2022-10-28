package com.gold.youtubeplayer.chromecast.chromecastsender.io.youtube

import com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure.ChromecastCommunicationChannel
import com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure.MessageFromReceiver
import com.gold.youtubeplayer.core.player.YouTubePlayerBridge

/**
 * Class responsible for dispatching messages received from the cast receiver.
  */
internal class ChromecastYouTubeMessageDispatcher(private val bridge: YouTubePlayerBridge) : ChromecastCommunicationChannel.ChromecastChannelObserver {
    override fun onMessageReceived(messageFromReceiver: MessageFromReceiver) {
        when (messageFromReceiver.type) {
            ChromecastCommunicationConstants.IFRAME_API_READY -> bridge.sendYouTubeIFrameAPIReady()
            ChromecastCommunicationConstants.READY -> bridge.sendReady()
            ChromecastCommunicationConstants.STATE_CHANGED -> bridge.sendStateChange(messageFromReceiver.data)
            ChromecastCommunicationConstants.PLAYBACK_QUALITY_CHANGED -> bridge.sendPlaybackQualityChange(messageFromReceiver.data)
            ChromecastCommunicationConstants.PLAYBACK_RATE_CHANGED -> bridge.sendPlaybackRateChange(messageFromReceiver.data)
            ChromecastCommunicationConstants.ERROR -> bridge.sendError(messageFromReceiver.data)
            ChromecastCommunicationConstants.API_CHANGED -> bridge.sendApiChange()
            ChromecastCommunicationConstants.VIDEO_CURRENT_TIME -> bridge.sendVideoCurrentTime(messageFromReceiver.data)
            ChromecastCommunicationConstants.VIDEO_DURATION -> bridge.sendVideoDuration(messageFromReceiver.data)
            ChromecastCommunicationConstants.VIDEO_ID -> bridge.sendVideoId(messageFromReceiver.data)
        }
    }
}