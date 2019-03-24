package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.google.android.material.navigation.NavigationView;

public class systemView_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout buttonContainer;
    private Galaxy_viewmodel galaxyViewmodel;
    private Configuration_viewmodel configuration_viewmodel;
    private Game game;
    private int viewCenterX;
    private int viewCenterY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view_);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
