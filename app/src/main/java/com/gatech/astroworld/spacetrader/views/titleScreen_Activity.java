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
    public static Activity mainActivity;
    private Button playButton;
    private Button settingsButton;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Load.loadPlayer();
        Load.loadSystemList();
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen_);

        playButton = findViewById(R.id.menu_play_button);
        settingsButton = findViewById(R.id.menu_settings_button);
        continueButton = findViewById(R.id.menu_continue_button);

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
                System.out.println(Game.getInstance().getPlayer().getName());
                System.out.println(Game.getInstance().getPlayer().getCredits());
                System.out.println(Game.getInstance().getPlayer().getPilotPoints());
                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getxPos());
                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getyPos());
                System.out.println(Game.getInstance().getSystemList().get(0).getTechLevel());
                System.out.println(Game.getInstance().getSystemList().get(0).getName());
//                Load.loadShip();
//                Load.loadSystemList();
//                Load.loadCurrentPlanet();
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                startActivity(i);
            }
        });

    }
}
