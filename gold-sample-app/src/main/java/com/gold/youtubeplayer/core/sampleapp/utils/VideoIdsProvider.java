package com.gold.youtubeplayer.core.sampleapp.utils;

import java.util.Random;

public class VideoIdsProvider {
    private static String[] videoIds = {"Orw8CZpzIDU", "51mE341m5X0", "upiS93DCR6o", "upiS93DCR6o"};
    private static String[] liveVideoIds = {"w4Ay4lHpR4o"};
    private static Random random = new Random();

    public static String getNextVideoId() {
        return videoIds[random.nextInt(videoIds.length)];
    }

    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
}
