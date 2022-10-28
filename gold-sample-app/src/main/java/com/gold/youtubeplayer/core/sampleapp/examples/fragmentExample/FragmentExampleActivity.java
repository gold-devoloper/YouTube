package com.gold.youtubeplayer.core.sampleapp.examples.fragmentExample;

import android.os.Bundle;

import com.gold.player.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        if (savedInstanceState == null) {
            Fragment newFragment = new FragmentExampleFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, newFragment).commit();
        }
    }
}
