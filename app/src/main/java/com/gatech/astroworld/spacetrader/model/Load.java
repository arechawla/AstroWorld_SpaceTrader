package com.gatech.astroworld.spacetrader.model;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.gatech.astroworld.spacetrader.entity.TechLevel;
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
        sysListRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<SolarSystem> ssList = Game.getInstance().getSystemList();
                for (DataSnapshot sysNode: dataSnapshot.getChildren()) {
                    DisplayMetrics disp = Resources.getSystem().getDisplayMetrics();
                    int width = disp.widthPixels;
                    int height = disp.heightPixels;
                    SolarSystem sys =  new SolarSystem(width, height);
                    int xPos = sysNode.child("sysLocation").
                            child("xPos").getValue(Integer.class);
                    int yPos = sysNode.child("sysLocation").
                            child("yPos").getValue(Integer.class);
                    String name = sysNode.child("name").getValue(String.class);
                    String techLev = sysNode.child("techLevel").getValue(String.class);
                    System.out.println(xPos);
                    System.out.println(yPos);
                    sys.getSysLocation().setxPos(xPos);
                    sys.getSysLocation().setyPos(yPos);
                    sys.setName(name);
                    sys.setTechLevel(TechLevel.valueOf(techLev));
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

        mRootRef.child("player").child("credits").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int creds = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().setCredits(creds);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRootRef.child("player").child("skillPoints").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int[] arr = new int [5];
                int i = 0;
                for (DataSnapshot skillNode: dataSnapshot.getChildren()) {
                    int sys = skillNode.getValue(Integer.class);
                    arr[i] = sys;
                    i++;
                }
                Game.getInstance().getPlayer().setEngineerPoints(arr[0]);
                Game.getInstance().getPlayer().setFighterPoints(arr[1]);
                Game.getInstance().getPlayer().setPilotPoints(arr[2]);
                Game.getInstance().getPlayer().setTraderPoints(arr[4]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}
