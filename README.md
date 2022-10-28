# YouTube Player library



Step 1. Add the JitPack repository to your build file

Add it in your root settings.gradle at the end of repositories:

       maven { url 'https://jitpack.io' }

Step 2. Add the dependency

       implementation 'com.github.gold-devoloper:YouTube:Tag'

Xml Code
       
       <com.gold.youtubeplayer.core.player.views.YouTubePlayerView
         android:id="@+id/youtube_player_view"
         android:layout_width="match_parent"
         android:layout_height="wrap_content"
         app:videoId="Orw8CZpzIDU"
         app:autoPlay="true" />
         
Java Code

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
        @Override
        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
              String videoId = "Orw8CZpzIDU";
              youTubePlayer.loadVideo(videoId, 0);
           }
        });
        
  Done
        
        
