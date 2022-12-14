package com.gold.youtubeplayer.core.sampleapp.examples.iFramePlayerOptionsExample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import com.gold.youtubeplayer.core.player.utils.YouTubePlayerUtils;
import com.gold.youtubeplayer.core.player.views.YouTubePlayerView;
import com.gold.youtubeplayer.core.player.options.IFramePlayerOptions;
import com.gold.youtubeplayer.core.player.YouTubePlayer;
import com.gold.youtubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.gold.youtubeplayer.core.sampleapp.utils.VideoIdsProvider;
import com.gold.player.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class IFramePlayerOptionsExampleActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iframe_player_options_example);

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        initYouTubePlayerView();
    }

    private void initYouTubePlayerView() {
        youTubePlayerView.inflateCustomPlayerUi(R.layout.ayp_empty_layout);

        IFramePlayerOptions iFramePlayerOptions = new IFramePlayerOptions.Builder()
                .controls(0)
                .rel(0)
                .ivLoadPolicy(1)
                .ccLoadPolicy(1)
                .build();

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                setPlayNextVideoButtonClickListener(youTubePlayer);

                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        VideoIdsProvider.getNextVideoId(),0f
                );
            }
        }, true, iFramePlayerOptions);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }

    /**
     * Set a click listener on the "Play next video" button
     */
    private void setPlayNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
        Button playNextVideoButton = findViewById(R.id.next_video_button);

        playNextVideoButton.setOnClickListener(view ->
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        VideoIdsProvider.getNextVideoId(),0f
                )
        );
    }
}
