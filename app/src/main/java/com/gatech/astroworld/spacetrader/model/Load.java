package com.gatech.astroworld.spacetrader.model;

import android.content.res.Resources;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;
import com.gatech.astroworld.spacetrader.entity.TechLevel;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
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
                    List<Planet> planetList = new ArrayList<>();
                    for (DataSnapshot planNode: sysNode.child("listPlanets").getChildren()) {
                        Planet p = new Planet(sys);
                        int xPos2 = planNode.child("planLocation").
                                child("xPos").getValue(Integer.class);
                        int yPos2 = planNode.child("planLocation").
                                child("yPos").getValue(Integer.class);
                        int storeCreds = planNode.child("store").child("storeCredits").
                                getValue(Integer.class);
                        String gov = planNode.child("gov").getValue(String.class);
                        String name2 = planNode.child("name").getValue(String.class);
                        p.getPlanLocation().setxPos(xPos2);
                        p.getPlanLocation().setyPos(yPos2);
                        p.setGov(PoliticalSystems.valueOf(gov));
                        p.setName(name2);
                        p.getStore().setStoreCredits(storeCreds);
                        List<MarketGood> stInven = new ArrayList<>();
                        for (DataSnapshot goodNode: planNode.child("store").child("store inventory")
                        .getChildren()) {
                            String goodType = goodNode.child("goodType").getValue(String.class);
                            String name3 = goodNode.child("name").getValue(String.class);
                            int price = goodNode.child("price").getValue(Integer.class);
                            int quantity = goodNode.child("quantity").getValue(Integer.class);
                            System.out.println(goodType);
                            MarketGood good = new MarketGood(GoodType.valueOf(goodType), p);
                            good.setName(name3);
                            good.setPrice(price);
                            good.setQuantity(quantity);
                            stInven.add(good);
                        }
                        p.getStore().setStoreInventory(stInven);
                        planetList.add(p);
                    }
                    sys.setListOfPlanets(planetList);
                }
                Game.getInstance().setSystemList(ssList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public static void loadShip() {

        //load ship name
        mRootRef.child("player").child("currentShip").child("capacity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int cap = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().getShip().setCapacity(cap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //load ship fuel
        mRootRef.child("player").child("currentShip").child("fuel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int fuel = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().getShip().setFuel(fuel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRootRef.child("player").child("currentShip").child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                Game.getInstance().getPlayer().getShip().setName(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRootRef.child("player").child("currentShip").child("listCargo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<TradeGood> tradeGoodList = new ArrayList<>();
                for (DataSnapshot tGoodNode: dataSnapshot.getChildren()) {
//                    int price = tGoodNode.child("price").getValue(Integer.class);
//                    int quantity = tGoodNode.child("quantity").getValue(Integer.class);
//                    String name = tGoodNode.child("name").getValue(String.class);
//
//                    cargoRef.child("price").setValue(t.getPrice());
//                    cargoRef.child("quantity").setValue(t.getQuantity());
//                    cargoRef.child("name").setValue(shipName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }

    public static void loadPlayer() {

        //Save name
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


        //save credits
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


        //save skill points
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

        //save reputation
        mRootRef.child("player").child("repuation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int rep = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().setReputation(rep);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //save system index
        mRootRef.child("player").child("curSystemIndex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int ref = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().setCurSystemReference(ref);
//                Game.getInstance().getPlayer().setCurrentSystem(Game.getInstance().getSystemList().get(ref));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //save planet index
        mRootRef.child("player").child("curPlanetIndex").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int planIndex = dataSnapshot.getValue(Integer.class);
                Game.getInstance().getPlayer().setCurPlanetReference(planIndex);
                System.out.println(Game.getInstance().getSystemList().size());
//                Game.getInstance().getPlayer().setCurrentPlanet(Game.getInstance().getSystemList().get(0).
//                        getListOfPlanets().get(planIndex));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }







}
