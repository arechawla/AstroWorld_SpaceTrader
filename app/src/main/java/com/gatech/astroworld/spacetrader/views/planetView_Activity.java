package com.gatech.astroworld.spacetrader.views;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;


public class planetView_Activity extends FragmentActivity {

    ImageView planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_view_);
        Button marketButton = findViewById(R.id.accessMarket_button);
        Button playerButton = findViewById(R.id.playerInfo);
        Button orbitButton = findViewById(R.id.returnToOrbit_button);
        Button shipYardButton = findViewById(R.id.shipYardbutton);
        Button gambleButton = findViewById(R.id.gambleButton);
        TextView title = findViewById(R.id.planetName);
        planet = findViewById(R.id.imageView2);
        System.out.println(Game.getInstance().getPlayer().getName());

        title.setText("Welcome to " + Game.getInstance().getPlayer().getCurrentPlanet().getName());
        TextView info = findViewById(R.id.information);
        String gov = "Political System: " + Game.getInstance().getPlayer().getCurrentPlanet()
                .getGov().toString();
        String tech = "Tech Level: " + Game.getInstance().getPlayer().getCurrentSystem()
                .getTechLevel().name();
        String system = "Solar System: " + Game.getInstance().getPlayer().getCurrentSystem()
                .getName();
        info.setText(system + "\n" + tech + "\n" + gov);

        int resourceId = R.drawable.planet_spinning;
        Glide.with(this).load(resourceId).into(planet);


        marketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), market_Activity.class);
                startActivity(i);
            }
        });

        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PlayerInfo.class);
                startActivity(i);
            }
        });

        orbitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), systemView_Activity.class);
                startActivity(i);
            }
        });

        shipYardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ShipyardActivity.class);
                startActivity(i);
            }
        });
        gambleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), miniGame.class);
                startActivity(i);
            }
        });

    }
}
