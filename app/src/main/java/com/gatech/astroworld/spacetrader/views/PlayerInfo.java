package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Player;

public class PlayerInfo extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        TextView shipName = findViewById(R.id.shipName);
        TextView shipFuel = findViewById(R.id.shipFuel);
        TextView availSpace = findViewById(R.id.availSpace);
        TextView currSys = findViewById(R.id.currSystem);
        TextView currPlanet = findViewById(R.id.currPlanet);
        Game game = Game.getInstance();
        Player player = game.getPlayer();

        shipName.setText("Ship Name: " + player.getShip().toString());
        shipFuel.setText("Fuel Remaining: " + player.getShip().getFuel());
        availSpace.setText("Space Left in Cargo: " + player.getShip().getSpaceLeft());
        currSys.setText("Solar System: " + player.getCurrentSystem().getName());
        currPlanet.setText("Planet: " + player.getCurrentPlanet());
    }
}
