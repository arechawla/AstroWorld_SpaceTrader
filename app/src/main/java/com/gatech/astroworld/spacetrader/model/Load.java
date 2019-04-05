package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Load {


    FirebaseDatabase mDataBase = FirebaseDatabase.getInstance();
    DatabaseReference mRefPlayer = mDataBase.getReference("player");

    static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    static DatabaseReference shipRef = mRootRef.child("player").child("currentShip");
    static DatabaseReference sysListRef = mRootRef.child("systemList");
    static DatabaseReference systemRef = mRootRef.child("player").child("curSystemIndex");
    static DatabaseReference planetRef = mRootRef.child("player").child("curPlanetIndex");



    public static void loadCurrentPlanet() {

        systemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer sysInd = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().
                        setCurrentSystem(Game.getInstance().getSystemList().get(sysInd));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        planetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer planInd = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().
                        setCurrentPlanet(Game.getInstance().getPlayer().getCurrentSystem()
                                .getListOfPlanets().get(planInd));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    public static void loadSystemList() {
        shipRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                List<SolarSystem> ssList = Game.getInstance().getSystemList();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    SolarSystem sys = keyNode.getValue(SolarSystem.class);
                    ssList.add(sys);
                }
                Game.getInstance().setSystemList(ssList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public static void loadShip() {
        sysListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Game.getInstance().getPlayer().setShip(dataSnapshot.getValue(Spaceship.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    mRootRef.child("player").child("name").setValue(player.getName());
//        mRootRef.child("player").child("repuation").setValue(player.getReputation());
//        mRootRef.child("player").child("credits").setValue(player.getCredits());
//        mRootRef.child("player").child("skillPoints")
//                .child("pliotPoints").setValue(player.getPilotPoints());
//        mRootRef.child("player").child("skillPoints")
//                .child("remainingPoints").setValue(player.getSkillPoints());
//        mRootRef.child("player").child("skillPoints")
//                .child("traderPoints").setValue(player.getTraderPoints());
//        mRootRef.child("player").child("skillPoints")
//                .child("engineerPoints").setValue(player.getEngineerPoints());
//        mRootRef.child("player").child("skillPoints")
//                .child("fighterPoints").setValue(player.getFighterPoints());
//        mRootRef.child("player").child("curSystemIndex").setValue(player.getCurSystemReference());
//        mRootRef.child("player").child("curPlanetIndex").setValue(player.getCurPlanetReference());


    public static void loadPlayer() {
        mRootRef.child("player").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                Game.getInstance().getPlayer().setName(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
