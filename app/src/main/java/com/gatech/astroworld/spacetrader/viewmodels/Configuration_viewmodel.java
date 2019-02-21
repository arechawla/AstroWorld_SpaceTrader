package com.gatech.astroworld.spacetrader.viewmodels;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.gatech.astroworld.spacetrader.entity.Difficulty;
import com.gatech.astroworld.spacetrader.entity.Player;
import com.gatech.astroworld.spacetrader.model.Game;

public class Configuration_viewmodel extends AndroidViewModel {
    private Game game;

    public Configuration_viewmodel(@NonNull Application application) {
        super(application);
        game = Game.getInstance();
    }

    public void updatePlayer (Player player) { game.setPlayer(player); }
    public void updateGame (Difficulty difficulty) { game.setDifficulty(difficulty); }
}
