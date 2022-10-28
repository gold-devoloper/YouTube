package com.gold.youtubeplayer.core.sampleapp.utils;

import android.graphics.Bitmap;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;

public class YouTubeDataEndpoint {

    private static final String APP_NAME = "Gold You Tube Player";
    private static final String YOUTUBE_DATA_API_KEY = "AIzaSyAWrGfcAY1soRof5a4cj157mjG72_R_TGs";

    public static VideoInfo getVideoInfoFromYouTubeDataAPIs(String videoId) {
        try {
            YouTube youTubeDataAPIEndpoint = buildYouTubeEndpoint();

            YouTube.Videos.List query = buildVideosListQuery(youTubeDataAPIEndpoint, videoId);
            VideoListResponse videoListResponse = query.execute();

            if(videoListResponse.getItems().size() != 1)
                throw new RuntimeException("There should be exactly one video with the specified id");

            Video video = videoListResponse.getItems().get(0);

            String videoTitle = video.getSnippet().getTitle();
            Bitmap bitmap = NetworkUtils.getBitmapFromURL(video.getSnippet().getThumbnails().getMedium().getUrl());

            ChannelListResponse channel = buildChannelsListQuery(youTubeDataAPIEndpoint, video.getSnippet().getChannelId()).execute();
            String channelTitle = channel.getItems().get(0).getSnippet().getTitle();

            return new VideoInfo(videoTitle, channelTitle, bitmap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static YouTube.Videos.List buildVideosListQuery(YouTube youTubeDataAPIEndpoint, String videoId) throws IOException {
        return youTubeDataAPIEndpoint
                .videos()
                .list("snippet")
                .setFields("items(snippet(title,channelId,thumbnails(medium(url))))")
                .setId(videoId)
                .setKey(YOUTUBE_DATA_API_KEY);
    }

    private static YouTube.Channels.List buildChannelsListQuery(YouTube youTubeDataAPIEndpoint, String channelId) throws IOException {
        return youTubeDataAPIEndpoint
                .channels()
                .list("snippet")
                .setFields("items(snippet(title))")
                .setId(channelId)
                .setKey(YOUTUBE_DATA_API_KEY);
    }

    private static YouTube buildYouTubeEndpoint() {
        return new YouTube
                .Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setApplicationName(APP_NAME)
                .build();
    }
}
