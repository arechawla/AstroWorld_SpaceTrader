package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.System_viewmodel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class systemView_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout buttonContainer;
    private System_viewmodel systemViewmodel;
    private Configuration_viewmodel configuration_viewmodel;
    private Game game;
    private int viewCenterX;
    private int viewCenterY;
    private Point systemButtonSize = new Point(100, 100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        systemViewmodel = ViewModelProviders.of(this).get(System_viewmodel.class);
        configuration_viewmodel = ViewModelProviders.of(this).get(Configuration_viewmodel.class);
        //Generate buttons for galaxy view
        game = Game.getInstance();

        buttonContainer = findViewById(R.id.buttonContainer2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        View v = (View) findViewById(R.id.buttonContainer);
        viewCenterX = v.getWidth() / 2;
        viewCenterY = v.getHeight() / 2;
        Random rand = new Random();
        Player currPlayer = game.getPlayer();
        //Assign the player a random planet in that system
        //Update the player
        configuration_viewmodel.updatePlayer(currPlayer);
        List<ImageButton> buttonList = new ArrayList<>();
        for (Planet planet : systemViewmodel.getPlanetList()) {
            double xPos = planet.getPlanLocation().getxPos();
            double yPos = planet.getPlanLocation().getyPos();
            generatePlanetButton(xPos, yPos, buttonContainer, buttonList);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.system_view_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings2) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_player) {
            Intent i = new Intent(getApplicationContext(), PlayerInfo.class);
            startActivity(i);
        } else if (id == R.id.nav_ship) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void generatePlanetButton(double xPos, double yPos, RelativeLayout layout, List<ImageButton> buttonList) {

        ImageButton planetButton = new ImageButton(this);
        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem);
        image = Bitmap.createScaledBitmap(image, systemButtonSize.x, systemButtonSize.y, true);
        planetButton.setImageBitmap(image);
        planetButton.setBackgroundResource(0);
        buttonList.add(planetButton);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((int)xPos + viewCenterX, (int)yPos + viewCenterY, 0, 0);
        layout.addView(planetButton, params);
    }


}
