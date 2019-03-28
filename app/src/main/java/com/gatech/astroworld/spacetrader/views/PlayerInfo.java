package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Player;

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
        Game game = Game.getInstance();
        Player player = game.getPlayer();
        StringBuilder strBuild = new StringBuilder();

        shipName.setText(new StringBuilder().append("Ship Name: ").append(player.getShip().toString()).toString());
        shipFuel.setText(new StringBuilder().append("Fuel Remaining: ").append(player.getShip().getFuel()).toString());
        availSpace.setText(new StringBuilder().append("Space Left in Cargo: ").append(player.getShip().getSpaceLeft()).toString());
        currSys.setText(new StringBuilder().append("Solar System: ").append(player.getCurrentSystem().getName()).toString());
        currPlanet.setText(new StringBuilder().append("Planet: ").append(player.getCurrentPlanet()).toString());
    }
}
