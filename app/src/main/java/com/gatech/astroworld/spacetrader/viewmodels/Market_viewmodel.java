package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.gatech.astroworld.spacetrader.model.Game;

public class Market_viewmodel extends AndroidViewModel {
    private Game game = Game.getInstance();
    public Market_viewmodel(@NonNull Application application) {
        super(application);

    }
}

