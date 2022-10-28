package com.gold.youtubeplayer.chromecast.sampleapp.utils

import java.util.*

object VideoIdsProvider {
    private val videoIds = arrayOf("Orw8CZpzIDU", "51mE341m5X0", "upiS93DCR6o", "upiS93DCR6o")
    private val random = Random()

    fun getNextVideoId(): String {
        return videoIds[random.nextInt(videoIds.size)]
    }
}