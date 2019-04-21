package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Shipyard;

public class ShipyardActivity extends AppCompatActivity {

    private final Shipyard shipyard = new Shipyard();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipyard);
        Button plusButton = findViewById(R.id.plusButton);
        Button minusButton = findViewById(R.id.minusButton);
        final TextView display =  findViewById(R.id.countText);
        final Toast error = Toast.makeText(getApplicationContext(), "You do not" +
                " have enough money to buy fuel!", Toast.LENGTH_SHORT);
        final TextView remainingCredits = findViewById(R.id.yourCredits);
        remainingCredits.setText("Remaining Credits: " +
                String.valueOf(Game.getInstance().getPlayer().getCredits()));
        Button buy = findViewById(R.id.buyShipStuff);
        display.setText(String.valueOf(shipyard.getFuelCount()));

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipyard.incrementFuelCount();
                display.setText(String.valueOf(shipyard.getFuelCount()));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipyard.decrementFuelCount();
                display.setText(String.valueOf(shipyard.getFuelCount()));
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean transaction = shipyard.buyFuel
                        (shipyard.getFuelCount());
                if (transaction == false) {
                    error.show();
                }
                shipyard.setFuelCount(0);
                display.setText(String.valueOf(shipyard.getFuelCount()));
                remainingCredits.setText("Remaining Credits: " +
                        String.valueOf(Game.getInstance().getPlayer().getCredits()));
            }
        });

    }
}
