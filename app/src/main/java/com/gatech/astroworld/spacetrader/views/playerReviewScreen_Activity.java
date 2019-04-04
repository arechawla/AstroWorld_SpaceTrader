package com.gatech.astroworld.spacetrader.views;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.Game;

import com.gatech.astroworld.spacetrader.model.Save;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.model.Spaceship;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

public class playerReviewScreen_Activity extends AppCompatActivity {
    private Galaxy_viewmodel galaxy_viewmodel;
    private Configuration_viewmodel playerConfig;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference currSysRef = mRootRef.child("player").child("currentSystem");
    DatabaseReference currPlanRef = mRootRef.child("player").child("currentPlanet");
    DatabaseReference sysListRef = mRootRef.child("systemList");
    DatabaseReference shipRef = mRootRef.child("player").child("currentShip");

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

        final Player tempPlayer = Game.getInstance().getPlayer();
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

        /**
         *
         */
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start planet view activity (Should be changed to galaxy view later)
                Game.getInstance().initializePlayerPlanet();

//                mRootRef.setValue(Game.getInstance().getPlayer().getCurrentPlanet().getStore()
//                        );


                //Set player values

//                Player player = Game.getInstance().getPlayer();
//                mRootRef.child("player").child("repuation").setValue(player.getReputation());
//                mRootRef.child("player").child("credits").setValue(player.getCredits());
//
//                currSysRef.child("name").setValue(Game.getInstance().getPlayer().
//                        getCurrentSystem().getName());
//                currSysRef.child("techLevel").setValue(Game.getInstance().getPlayer().
//                        getCurrentSystem().getTechLevel().toString());
//                currSysRef.child("sysLocation").child("xPos").setValue(Game.getInstance().
//                        getPlayer().getCurrentSystem().getSysLocation().getxPos());
//                currSysRef.child("sysLocation").child("yPos").setValue(Game.getInstance().
//                        getPlayer().getCurrentSystem().getSysLocation().getyPos());
//
//                currPlanRef.child("name").setValue(Game.getInstance().getPlayer().
//                        getCurrentPlanet().getName());
//                currPlanRef.child("gov").setValue(Game.getInstance().getPlayer().
//                        getCurrentPlanet().getGov().toString());
//                currPlanRef.child("planLocation").child("xPos").setValue(Game.getInstance().
//                        getPlayer().getCurrentPlanet().getPlanLocation().getxPos());
//                currPlanRef.child("planLocation").child("yPos").setValue(Game.getInstance().
//                        getPlayer().getCurrentPlanet().getPlanLocation().getyPos());
//                currPlanRef.child("store").child("storeCredits").setValue(Game.getInstance().
//                        getPlayer().getCurrentPlanet().getStore().getStoreCredits());
//                List<MarketGood> stInventory = Game.getInstance().getPlayer().getCurrentPlanet()
//                        .getStore().getStoreInventory();
//
//
//                for (MarketGood mark: stInventory) {
//                    String markName = mark.getName();
//                    DatabaseReference markRef = currPlanRef.child("store").child("store inventory").
//                            child(markName);
//                    markRef.child("price").setValue(mark.getPrice());
//                    markRef.child("quantity").setValue(mark.getQuantity());
//                    markRef.child("count").setValue(mark.getCount());
//
//                }
//
//
//                List<SolarSystem> ssList = Game.getInstance().getSystemList();
//
//                int sysNum = 1;
//                for (SolarSystem sys: ssList) {
//                    String sysName = sys.getName();
//                    sysListRef.child(sysName).child("sysLocation").
//                            child("xPos").setValue(sys.getSysLocation().getxPos());
//                    sysListRef.child(sysName).child("sysLocation").
//                            child("yPos").setValue(sys.getSysLocation().getyPos());
//                    sysListRef.child("techLevel").setValue(sys.getTechLevel().toString());
//
//                    List<Planet> pList = sys.getListOfPlanets();
//                    int planNum = 1;
//                    for (Planet plan: pList) {
//                        String planName = plan.getName();
//                        DatabaseReference planRef = sysListRef.child(sysName).child("listPlanets").
//                                child(planName);
//                        planRef.child("planLocation").
//                                child("xPos").setValue(plan.getPlanLocation().getxPos());
//                        planRef.child("planLocation").
//                                child("yPos").setValue(plan.getPlanLocation().getyPos());
//                        planRef.child("gov").setValue(plan.getGov().toString());
//                        planRef.child("store").child("storeCredits").
//                                setValue(plan.getStore().getStoreCredits());
//                        List<MarketGood> sInventory = plan.getStore().getStoreInventory();
//                        for (MarketGood mark: sInventory) {
//                            String markName = mark.getName();
//                            DatabaseReference markRef = planRef.child("store").
//                                    child("store inventory").child(markName);
//                            markRef.child("price").setValue(mark.getPrice());
//                            markRef.child("quantity").setValue(mark.getQuantity());
//                            markRef.child("count").setValue(mark.getCount());
//
//                        }
//
//
//
//                        planNum++;
//                    }
//                    sysNum++;
//                }
//
//
//                //Setting ship
//                Spaceship currShip = Game.getInstance().getPlayer().getShip();
//                shipRef.child("name").setValue(Game.getInstance().getPlayer().getShip().toString());
//                shipRef.child("fuel").setValue(currShip.getFuel());
//                shipRef.child("capacity").setValue(currShip.getCapacity());
//                shipRef.child("unitFuelUse").setValue(currShip.getUnitFuelUse());
//                shipRef.child("ssFuelMult").setValue(currShip.getSsFuelMultiplier());
//
//                List<TradeGood> cList = Game.getInstance().getPlayer().getShip().getCargoList();
//                for (TradeGood t: cList) {
//                    String shipName = Game.getInstance().getPlayer().getShip().toString();
//                    String tradeName = t.getName();
//                    DatabaseReference cargoRef = shipRef.child(shipName).child("listCargo").child(tradeName);
//                }



                Save.savePlayerInformation();
                Save.saveSpaceShipInformation();
                Save.saveSolarSystemList();
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
}
