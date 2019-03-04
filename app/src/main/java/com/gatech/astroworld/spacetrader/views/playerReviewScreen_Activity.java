package com.gatech.astroworld.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.entity.Player;
import com.gatech.astroworld.spacetrader.model.Game;
import org.w3c.dom.Text;

import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;

import java.util.Random;

public class playerReviewScreen_Activity extends AppCompatActivity {
    private Galaxy_viewmodel galaxy_viewmodel;
    private Configuration_viewmodel playerConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_review_screen);

        galaxy_viewmodel = ViewModelProviders.of(this).get(Galaxy_viewmodel.class);
        playerConfig = ViewModelProviders.of(this).get(Configuration_viewmodel.class);

        //Init Counters
        TextView pilotPointCount = findViewById(R.id.pilotPointCount);
        TextView traderPointCount = findViewById(R.id.traderPointCount);
        TextView fighterPointCount = findViewById(R.id.fighterPointCount);
        TextView engineerPointCount = findViewById(R.id.engineerPointCount);
        //Init Categories
        TextView pilotDescription = findViewById(R.id.pilotDescription);
        TextView traderDescription = findViewById(R.id.traderDescription);
        TextView fighterDescription = findViewById(R.id.fighterDescription);
        TextView engineerDescription = findViewById(R.id.engineerDescription);
        //Init Name
        TextView playerName = findViewById(R.id.playerName);
        //Init Button
        Button continueButton = findViewById(R.id.continueButton);
        //Init Ship Name
        TextView shipName = findViewById(R.id.shipHeader);

        Player tempPlayer = Game.getInstance().getPlayer();
        int pilotPoints = tempPlayer.getPilotPoints();
        int traderPoints = tempPlayer.getTraderPoints();
        int fighterPoints = tempPlayer.getFighterPoints();
        int engineerPoints = tempPlayer.getEngineerPoints();

        shipName.setText("Ship: " + tempPlayer.getShip().toString());
        playerName.setText(tempPlayer.getName());
        pilotPointCount.setText(String.valueOf(pilotPoints));
        traderPointCount.setText(String.valueOf(traderPoints));
        fighterPointCount.setText(String.valueOf(fighterPoints));
        engineerPointCount.setText(String.valueOf(engineerPoints));

        if (pilotPoints <= 4) {
            pilotDescription.setText("Guaranteed space dust.");
            pilotDescription.setTextColor(Color.RED);
        } else if (pilotPoints <= 8) {
            pilotDescription.setText("You'll make it out of orbit.");
            pilotDescription.setTextColor(Color.BLUE);
        } else {
            pilotDescription.setText("Good luck Han!");
            pilotDescription.setTextColor(Color.GREEN);
        }

        if (traderPoints <= 4) {
            traderDescription.setText("Lemonade stand material.");
            traderDescription.setTextColor(Color.RED);
        } else if (traderPoints <= 8) {
            traderDescription.setText("Moderate Businessman");
            traderDescription.setTextColor(Color.BLUE);
        } else {
            traderDescription.setText("Expert Auctioneer");
            traderDescription.setTextColor(Color.GREEN);
        }

        if (fighterPoints <= 4) {
            fighterDescription.setText("Here, take this stick.");
            fighterDescription.setTextColor(Color.RED);
        } else if (fighterPoints <= 8) {
            fighterDescription.setText("A worthy opponent in battle.");
            fighterDescription.setTextColor(Color.BLUE);
        } else {
            fighterDescription.setText("'Tis but a scratch!");
            fighterDescription.setTextColor(Color.GREEN);
        }

        if (engineerPoints <= 4) {
            engineerDescription.setText("If it ain't broke");
            engineerDescription.setTextColor(Color.RED);
        } else if (pilotPoints <= 8) {
            engineerDescription.setText("Righty tighty, lefty lucy.");
            engineerDescription.setTextColor(Color.BLUE);
        } else {
            engineerDescription.setText("Did you try unplugging it? ALWAYS do that first.");
            engineerDescription.setTextColor(Color.GREEN);
        }

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rand = new Random();
                Player currPlayer = playerConfig.getPlayer();
                //Generate max number of systems
                galaxy_viewmodel.generateSystems();
                //Assign the player a random system
                currPlayer.setCurrentSystem(galaxy_viewmodel.getRandomSystem());
                //Assign the player a random planet in that system
                currPlayer.setCurrentPlanet(currPlayer.getCurrentSystem().getListOfPlanets().get(
                        rand.nextInt(currPlayer.getCurrentSystem().getListOfPlanets().size() - 1))); 
                //Update the player
                playerConfig.updatePlayer(currPlayer);
                //Start planet view activity (Should be changed to galaxy view later)
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
}
