package com.gatech.astroworld.spacetrader.views;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;

public class planetView_Activity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_view_);
        Button marketButton = findViewById(R.id.accessMarket_button);
        Button playerButton = findViewById(R.id.playerInfo);
        TextView title = findViewById(R.id.planetName);
        title.setText("Welcome to " + Game.getInstance().getPlayer().getCurrentPlanet().getName());
        TextView info = findViewById(R.id.information);
        String gov = "Political System: " + Game.getInstance().getPlayer().getCurrentPlanet().getGov().toString();
        String tech = "Tech Level: " + Game.getInstance().getPlayer().getCurrentSystem().getTechLevel().name();
        String system = "Solar System: " + Game.getInstance().getPlayer().getCurrentSystem().getName();
        info.setText(system + "\n" + tech + "\n" + gov);



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

    }
}
