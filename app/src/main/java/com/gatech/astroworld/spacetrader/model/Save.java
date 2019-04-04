package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Save {

    static DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    static DatabaseReference shipRef = mRootRef.child("player").child("currentShip");
    static DatabaseReference sysListRef = mRootRef.child("systemList");

    public static void savePlayerInformation() {
        Player player = Game.getInstance().getPlayer();
        mRootRef.child("player").child("repuation").setValue(player.getReputation());
        mRootRef.child("player").child("credits").setValue(player.getCredits());
        mRootRef.child("player").child("skillPoints")
                .child("pliotPoints").setValue(player.getPilotPoints());
        mRootRef.child("player").child("skillPoints")
                .child("remainingPoints").setValue(player.getSkillPoints());
        mRootRef.child("player").child("skillPoints")
                .child("traderPoints").setValue(player.getTraderPoints());
        mRootRef.child("player").child("skillPoints")
                .child("engineerPoints").setValue(player.getEngineerPoints());
        mRootRef.child("player").child("skillPoints")
                .child("fighterPoints").setValue(player.getFighterPoints());
        mRootRef.child("player").child("curSystemIndex").setValue(player.getCurSystemReference());
        mRootRef.child("player").child("curPlanetIndex").setValue(player.getCurPlanetReference());
    }

    public static void saveSpaceShipInformation() {
        Spaceship ship = Game.getInstance().getPlayer().getShip();
        shipRef.child("fuel").setValue(ship.getFuel());
        shipRef.child("capacity").setValue(ship.getCapacity());
        shipRef.child("name").setValue(ship.getName());


        List<TradeGood> cList = Game.getInstance().getPlayer().getShip().getCargoList();
                for (TradeGood t: cList) {
                    String shipName = Game.getInstance().getPlayer().getShip().toString();
                    String tradeName = t.getName();
                    DatabaseReference cargoRef = shipRef.child(shipName).child("listCargo").child(tradeName);
             }

        //Save ship


    }


    public static void saveSolarSystemList() {
        List<SolarSystem> ssList = Game.getInstance().getSystemList();

                int sysNum = 1;
                for (SolarSystem sys: ssList) {
                    String sysName = sys.getName();
                    sysListRef.child(sysName).child("sysLocation").
                            child("xPos").setValue(sys.getSysLocation().getxPos());
                    sysListRef.child(sysName).child("sysLocation").
                            child("yPos").setValue(sys.getSysLocation().getyPos());
                    sysListRef.child(sysName).child("techLevel").setValue(sys.getTechLevel().toString());

                    List<Planet> pList = sys.getListOfPlanets();
                    int planNum = 1;
                    for (Planet plan: pList) {
                        String planName = plan.getName();
                        DatabaseReference planRef = sysListRef.child(sysName).child("listPlanets").
                                child(planName);
                        planRef.child("planLocation").
                                child("xPos").setValue(plan.getPlanLocation().getxPos());
                        planRef.child("planLocation").
                                child("yPos").setValue(plan.getPlanLocation().getyPos());
                        planRef.child("gov").setValue(plan.getGov().toString());
                        planRef.child("store").child("storeCredits").
                                setValue(plan.getStore().getStoreCredits());
                        List<MarketGood> sInventory = plan.getStore().getStoreInventory();
                        for (MarketGood mark: sInventory) {
                            String markName = mark.getName();
                            DatabaseReference markRef = planRef.child("store").
                                    child("store inventory").child(markName);
                            markRef.child("price").setValue(mark.getPrice());
                            markRef.child("quantity").setValue(mark.getQuantity());
                            markRef.child("count").setValue(mark.getCount());

                        }



                        planNum++;
                    }
                    sysNum++;
                }
    }
}
