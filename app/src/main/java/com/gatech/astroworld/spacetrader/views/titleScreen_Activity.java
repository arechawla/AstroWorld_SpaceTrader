package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;

public class titleScreen_Activity extends AppCompatActivity {
    //public static Activity mainActivity;
    private MediaPlayer menuMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen_);

        menuMusic = MediaPlayer.create(getApplicationContext(), R.raw.menu_music);



        Button playButton = findViewById(R.id.menu_play_button);
        Button settingsButton = findViewById(R.id.menu_settings_button);
        Button continueButton = findViewById(R.id.menu_continue_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuMusic.pause();
                Intent i = new Intent(getApplicationContext(), configScreen_Activity.class);
                startActivity(i);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Game.getInstance().getPlayer().getCurSystemReference();
                int b = Game.getInstance().getPlayer().getCurPlanetReference();
                Game.getInstance().getPlayer().setCurrentSystem(
                        Game.getInstance().getSystemList().get(a));
                menuMusic.pause();

                if (Game.getInstance().getPlayer().getCurPlanetReference() == -1) {
                    Intent intent = new Intent(getApplicationContext(), systemView_Activity.class);
                    startActivity(intent);
                } else {
                    Game.getInstance().getPlayer().setCurrentPlanet(
                            Game.getInstance().getSystemList().get(a).getListOfPlanets().get(b));
                    Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        menuMusic.start();
    }
}
