package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.JsonWriter;

import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.google.gson.Gson;
import com.gatech.astroworld.spacetrader.views.galaxyView_Activity;
import com.gatech.astroworld.spacetrader.views.configScreen_Activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.Random;

public class Galaxy_viewmodel extends AndroidViewModel {
    private Game game;
    private Random rand = new Random();
    private galaxyView_Activity galaxyViewActivity;

    public Galaxy_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }
    public List<SolarSystem> getSystemList(){
        return game.getSystemList();
    }


    public SolarSystem getRandomSystem(){
        return game.getSystemList().get(rand.nextInt(game.getSystemList().size() - 1));
    }

    public void generateGalaxy (int galSizeX, int galSizeY) {
        //Generate max number of systems
        for (int i = 0; i < game.getMaxSystems(); i++) {
            game.getSystemList().add(new SolarSystem(galSizeX, galSizeY));
        }
    }
}
