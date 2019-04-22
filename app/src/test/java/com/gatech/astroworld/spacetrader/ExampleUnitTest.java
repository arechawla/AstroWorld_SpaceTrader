package com.gatech.astroworld.spacetrader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.gatech.astroworld.spacetrader.model.Spaceship;

import java.util.ArrayList;
import java.util.List;


public class ExampleUnitTest {
    private Spaceship ship1, ship2;
    private static final int TIMEOUT = 200;
    @Before
    public void setup() {
        ship1 = new Spaceship("Ship1", 100, 100);
        ship2 = new Spaceship("Ship2", 0, 100);
        List<TradeGood> list = new ArrayList<>();

        for (int i = 0; i < ship1.getCapacity(); i++) {
            list.add(new TradeGood(GoodType.WATER));
        }

        ship1.setCargoList(list);
    }
    @Test(timeout = TIMEOUT)
    public void testShip1() {
        Integer answer = ship1.containsCargo(new TradeGood(GoodType.FOOD));
        Assert.assertEquals((long)-1, (long)answer);
        answer = ship1.containsCargo(new TradeGood(GoodType.WATER));
        Assert.assertEquals(0, (long)answer);
    }
    @Test(timeout = TIMEOUT)
    public void testShip2() {
        Integer answer = ship1.containsCargo(new TradeGood(GoodType.FOOD));
        Assert.assertEquals((long)-1, (long)answer);
    }
}