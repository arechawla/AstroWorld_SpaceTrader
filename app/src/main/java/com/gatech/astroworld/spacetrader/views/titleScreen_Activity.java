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
import com.google.firebase.database.FirebaseDatabase;

public class titleScreen_Activity extends AppCompatActivity {
    public static Activity mainActivity;
    private Button playButton;
    private Button settingsButton;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Load.loadSystemList();
        Load.loadPlayer();
        Load.loadShip();
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen_);

        playButton = findViewById(R.id.menu_play_button);
        settingsButton = findViewById(R.id.menu_settings_button);
        continueButton = findViewById(R.id.menu_continue_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getInstance().getReference().setValue(null);
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
                System.out.println("Name: " + Game.getInstance().getPlayer().getName());
                System.out.println("Credits: " + Game.getInstance().getPlayer().getCredits());
                System.out.println("Pilot Points: " + Game.getInstance().getPlayer().getPilotPoints());
                System.out.println("Planet ref: " + Game.getInstance().getPlayer().getCurPlanetReference());
                System.out.println("System Ref: " + Game.getInstance().getPlayer().getCurSystemReference());
                System.out.println("Reputation :" + Game.getInstance().getPlayer().getReputation());
                System.out.println("");
                System.out.println("Ship name: " + Game.getInstance().getPlayer().getShip().getName());
                System.out.println("Ship Fuel: " + Game.getInstance().getPlayer().getShip().getFuel());
                System.out.println("Ship Capacity: " + Game.getInstance().getPlayer().getShip().getCapacity());
                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getxPos());
                System.out.println(Game.getInstance().getSystemList().get(0).getSysLocation().getyPos());
                System.out.println(Game.getInstance().getSystemList().get(0).getTechLevel());
                System.out.println(Game.getInstance().getSystemList().get(0).getName());
                System.out.println(Game.getInstance().getSystemList().get(0).getListOfPlanets().get(0).getName());
                int a = Game.getInstance().getPlayer().getCurSystemReference();
                int b = Game.getInstance().getPlayer().getCurPlanetReference();
                System.out.println(a);
                System.out.println(b);
                Game.getInstance().getPlayer().setCurrentSystem(Game.getInstance().getSystemList().get(a));
                Game.getInstance().getPlayer().setCurrentPlanet(Game.getInstance().getSystemList().get(a).
                        getListOfPlanets().get(b));

//                Load.loadShip();
//                Load.loadSystemList();
//                Load.loadCurrentPlanet();
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                startActivity(i);
            }
        });

    }
}
