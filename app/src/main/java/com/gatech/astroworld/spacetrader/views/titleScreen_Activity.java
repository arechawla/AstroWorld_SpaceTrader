package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Load;

public class titleScreen_Activity extends AppCompatActivity {
    //public static Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen_);

        //MOVED to Loading Screen
//        Load.loadSystemList();
//        Load.loadPlayer();
//        Load.loadShip();

        Button playButton = findViewById(R.id.menu_play_button);
        Button settingsButton = findViewById(R.id.menu_settings_button);
        Button continueButton = findViewById(R.id.menu_continue_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//                Load.loadPlayer();
                int a = Game.getInstance().getPlayer().getCurSystemReference();
                int b = Game.getInstance().getPlayer().getCurPlanetReference();
                Game.getInstance().getPlayer().setCurrentSystem(
                        Game.getInstance().getSystemList().get(a));


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
}
