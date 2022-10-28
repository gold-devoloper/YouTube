package com.gold.youtubeplayer.core.sampleapp.examples.defaultCustomUiExample;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import com.gold.youtubeplayer.core.player.YouTubePlayer;
import com.gold.youtubeplayer.core.player.listeners.YouTubePlayerListener;
import com.gold.youtubeplayer.core.player.options.IFramePlayerOptions;
import com.gold.youtubeplayer.core.player.utils.YouTubePlayerUtils;
import com.gold.youtubeplayer.core.player.views.YouTubePlayerView;
import com.gold.youtubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.gold.youtubeplayer.core.sampleapp.utils.VideoIdsProvider;
import com.gold.youtubeplayer.core.ui.DefaultPlayerUiController;
import com.gold.player.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DefaultCustomUiExampleActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_custom_ui_example);

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        initYouTubePlayerView();
    }

    private void initYouTubePlayerView() {
        getLifecycle().addObserver(youTubePlayerView);

        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                // using pre-made custom ui
                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(youTubePlayerView, youTubePlayer);
                youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());

                setPlayNextVideoButtonClickListener(youTubePlayer);

                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        VideoIdsProvider.getNextVideoId(),
                        0f
                );
            }
        };

        // disable web ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();

        youTubePlayerView.initialize(listener, options);
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
                        youTubePlayer,
                        getLifecycle(),
                        VideoIdsProvider.getNextVideoId(),
                        0f
                )
        );
    }
}
