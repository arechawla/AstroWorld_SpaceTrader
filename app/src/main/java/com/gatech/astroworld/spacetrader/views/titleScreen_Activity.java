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
import com.gatech.astroworld.spacetrader.model.Player;

public class titleScreen_Activity extends AppCompatActivity {
    public Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen_);

        Load.loadSystemList();
        Load.loadPlayer();
        Load.loadShip();

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
                Player player = Game.getInstance().getPlayer();
                Load.loadPlayer();
//                System.out.println("Name: " + Game.getInstance().getPlayer().getName());
//                System.out.println("Credits: " + Game.getInstance().getPlayer().getCredits());
//                System.out.println("Pilot Points: " + Game.getInstance().getPlayer().getPilotPoints());
//                System.out.println("Planet ref: " + Game.getInstance().getPlayer().getCurPlanetReference());
//                System.out.println("System Ref: " + Game.getInstance().getPlayer().getCurSystemReference());
//                System.out.println("Reputation :" + Game.getInstance().getPlayer().getReputation());
//                System.out.println("");
//                System.out.println("Ship name: " + Game.getInstance().getPlayer().getShip().getName());
//                System.out.println("Ship Fuel: " + Game.getInstance().getPlayer().getShip().getFuel());
//                System.out.println("Ship Capacity: " + Game.getInstance().getPlayer().getShip().getCapacity());
//                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getxPos());
//                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getyPos());
//                System.out.println(Game.getInstance().getSystemList().get(0).getTechLevel());
//                System.out.println(Game.getInstance().getSystemList().get(0).getName());
//                System.out.println(Game.getInstance().getSystemList().get(0).getListOfPlanets().get(0).getName());
                int a = player.getCurSystemReference();
                int b = player.getCurPlanetReference();
                System.out.println(a);
                System.out.println(b);
                Game.getInstance().getPlayer().setCurrentSystem(Game.getInstance().getSystemList().get(a));
                if (b >= 0) {
                    Game.getInstance().getPlayer().setCurrentPlanet(Game.getInstance().getSystemList().get(a).
                            getListOfPlanets().get(b));
                }

                Load.loadShip();
                Load.loadSystemList();
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                startActivity(i);
            }
        });

    }
}
