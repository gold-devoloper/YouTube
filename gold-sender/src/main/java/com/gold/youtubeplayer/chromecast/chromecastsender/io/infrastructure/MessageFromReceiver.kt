package com.gold.youtubeplayer.chromecast.chromecastsender.io.infrastructure

/**
 * POJO representing a message received from the cast receiver.
 */
internal data class MessageFromReceiver(val type: String, val data: String)