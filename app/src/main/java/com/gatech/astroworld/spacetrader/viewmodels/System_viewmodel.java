package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.views.galaxyView_Activity;
import com.gatech.astroworld.spacetrader.views.systemView_Activity;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class System_viewmodel extends AndroidViewModel {

    private Game game;
    private Random rand = new Random();
    private systemView_Activity systemViewActivity;

    public System_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }
    public List<Planet> getPlanetList(){
        return game.getPlayer().getCurrentSystem().getListOfPlanets();
    }



//    public void generateGalaxy (int galSizeX, int galSizeY) {
//        //Generate max number of systems
//        for (int i = 0; i < game.getMaxSystems(); i++) {
//            game.getSystemList().add(new SolarSystem(galSizeX, galSizeY));
//        }
//    }
}
