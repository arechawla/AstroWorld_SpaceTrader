package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.SolarSystem;

import java.util.List;
import java.util.Random;

public class Galaxy_viewmodel extends AndroidViewModel {

    private Game game;
    private Random rand = new Random();

    public Galaxy_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }
    public void generateSystems(){
        for (int i = 0; i < game.getMaxSystems(); i++) {
            game.getSystemList().add(new SolarSystem());
        }
    }
    public List<SolarSystem> getSystemList(){
        return game.getSystemList();
    }
    public SolarSystem getRandomSystem(){
        if (game.getSystemList().size() <= 0) {
            generateSystems();
        }
        return game.getSystemList().get(rand.nextInt(game.getSystemList().size() - 1));
    }
}
