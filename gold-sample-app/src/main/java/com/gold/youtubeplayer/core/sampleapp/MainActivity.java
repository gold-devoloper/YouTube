package com.gold.youtubeplayer.core.sampleapp;

import android.content.Intent;
import android.os.Bundle;

import com.gold.youtubeplayer.core.sampleapp.examples.completeExample.CompleteExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.chromecastExample.ChromeCastExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.customUiExample.CustomUiActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.fragmentExample.FragmentExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.iFramePlayerOptionsExample.IFramePlayerOptionsExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.liveVideoExample.LiveVideoActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.noLifecycleObserverExample.NoLifecycleObserverExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.pictureInPictureExample.PictureInPictureActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.playerStateExample.PlayerStateActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.playlistExample.PlaylistExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.recyclerViewExample.RecyclerViewActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.simpleExample.SimpleExampleActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.viewPagerExample.ViewPagerActivity;
import com.gold.youtubeplayer.core.sampleapp.examples.defaultCustomUiExample.DefaultCustomUiExampleActivity;
import com.gold.player.R;
import com.psoffritti.librarysampleapptemplate.core.Constants;
import com.psoffritti.librarysampleapptemplate.core.SampleAppTemplateActivity;
import com.psoffritti.librarysampleapptemplate.core.utils.ExampleActivityDetails;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, SampleAppTemplateActivity.class);

        intent.putExtra(Constants.TITLE.name(), getString(R.string.app_name));
        intent.putExtra(Constants.GITHUB_URL.name(), "https://github.com/gold-devoloper/");
        intent.putExtra(Constants.HOMEPAGE_URL.name(), "https://github.com/gold-devoloper/");

        ExampleActivityDetails[] examples = new ExampleActivityDetails[]{
                new ExampleActivityDetails(R.string.simple_example,null, SimpleExampleActivity.class),
                new ExampleActivityDetails(R.string.complete_example,null, CompleteExampleActivity.class),
                new ExampleActivityDetails(R.string.default_custom_ui_example,null, DefaultCustomUiExampleActivity.class),
                new ExampleActivityDetails(R.string.custom_ui_example,null, CustomUiActivity.class),
                new ExampleActivityDetails(R.string.recycler_view_example,null, RecyclerViewActivity.class),
                new ExampleActivityDetails(R.string.view_pager_example,null, ViewPagerActivity.class),
                new ExampleActivityDetails(R.string.fragment_example,null, FragmentExampleActivity.class),
                new ExampleActivityDetails(R.string.live_video_example,null, LiveVideoActivity.class),
                new ExampleActivityDetails(R.string.player_status_example,null, PlayerStateActivity.class),
                new ExampleActivityDetails(R.string.picture_in_picture_example,null, PictureInPictureActivity.class),
                new ExampleActivityDetails(R.string.chromecast_example,null, ChromeCastExampleActivity.class),
                new ExampleActivityDetails(R.string.iframe_player_options_example,null, IFramePlayerOptionsExampleActivity.class),
                new ExampleActivityDetails(R.string.playlist_example,null, PlaylistExampleActivity.class),
                new ExampleActivityDetails(R.string.no_lifecycle_observer_example,null, NoLifecycleObserverExampleActivity.class)
        };

        intent.putExtra(Constants.EXAMPLES.name(), examples);

        startActivity(intent);
        finish();
    }
}
