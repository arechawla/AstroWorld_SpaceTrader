package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Load;
import com.google.firebase.database.FirebaseDatabase;

public class LoadingScreen extends AppCompatActivity {
    public static Activity mainActivity;
    private static int SPLASH_TIME_OUT = 2000;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        loading = (ProgressBar) findViewById(R.id.progressBar);
        loading.setMax(100);
        loading.setProgress(0);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Load.loadSystemList();
        Load.loadPlayer();
        Load.loadShip();

        final Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        loading.setProgress(i);
                        sleep(20);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getApplicationContext(), titleScreen_Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        thread.start();


    }
}
