package com.example.stefan.vezbanje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class RssfeedActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);

        Log.i("RssActivitySenta","onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("RssActivitySenta","onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("RssActivitySenta","onResume()");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("RssActivitySenta","onPause()");

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("RssActivitySenta","onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("RssActivitySenta","onDestroy()");

    }


    @Override
    public void onRssItemSelected(String text) {
        DetailFragment fragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.detailFragment);
        fragment.setText(text);
    }
}
