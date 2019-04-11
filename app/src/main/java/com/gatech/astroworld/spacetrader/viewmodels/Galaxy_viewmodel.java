package com.gatech.astroworld.spacetrader.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.views.galaxyView_Activity;
import java.util.Random;

public class Galaxy_viewmodel extends AndroidViewModel {
    private Game game;
    private Random rand = new Random();
    private galaxyView_Activity galaxyViewActivity;

    public Galaxy_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }

}
