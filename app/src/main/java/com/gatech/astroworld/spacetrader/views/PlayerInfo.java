package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;

public class PlayerInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        TextView shipName = findViewById(R.id.shipName);
        TextView shipFuel = findViewById(R.id.shipFuel);
        TextView availSpace = findViewById(R.id.availSpace);
        TextView currSys = findViewById(R.id.currSystem);
        TextView currPlanet = findViewById(R.id.currPlanet);
        Button backButton = findViewById(R.id.backButton);

        shipName.setText("Ship Name: " + Game.getInstance().getPlayer().getShip().toString());
        shipFuel.setText("Fuel Remaining: " + Game.getInstance().getPlayer().getShip().getFuel());
        availSpace.setText("Space Left in Cargo: " + Game.getInstance().getPlayer().getShip().getSpaceLeft());
        currSys.setText("Solar System: " + Game.getInstance().getPlayer().getCurrentSystem().getName());
        currPlanet.setText("Planet: " + Game.getInstance().getPlayer().getCurrentPlanet());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                startActivity(i);
            }
        });
    }
}
