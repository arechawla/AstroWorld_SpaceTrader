package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.views.systemView_Activity;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class System_viewmodel extends AndroidViewModel {

    private Game game;
    public System_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }
    public List<Planet> getPlanetList(){
        return game.getPlayer().getCurrentSystem().getListOfPlanets();
    }


}
