package com.gold.youtubeplayer.core.sampleapp.examples.noLifecycleObserverExample;

import android.content.res.Configuration;
import android.os.Bundle;

import com.gold.youtubeplayer.core.player.YouTubePlayer;
import com.gold.youtubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.gold.youtubeplayer.core.player.utils.YouTubePlayerUtils;
import com.gold.youtubeplayer.core.player.views.YouTubePlayerView;
import com.gold.youtubeplayer.core.sampleapp.utils.VideoIdsProvider;
import com.gold.player.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NoLifecycleObserverExampleActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_lifecycle_observer_example);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, getLifecycle(), VideoIdsProvider.getNextVideoId(), 0f);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        youTubePlayerView.getYouTubePlayerWhenReady(YouTubePlayer::pause);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
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
}
