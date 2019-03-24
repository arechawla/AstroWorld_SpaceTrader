package com.gatech.astroworld.spacetrader.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;

import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.gatech.astroworld.spacetrader.R;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class galaxyView_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout buttonContainer;
    private Galaxy_viewmodel galaxyViewmodel;
    private Configuration_viewmodel configuration_viewmodel;
    private Game game;
    private int viewCenterX;
    private int viewCenterY;
    private Point galaxyButtonSize = new Point(100, 100);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy_view_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        galaxyViewmodel = ViewModelProviders.of(this).get(Galaxy_viewmodel.class);
        configuration_viewmodel = ViewModelProviders.of(this).get(Configuration_viewmodel.class);
        //Generate buttons for galaxy view
        game = Game.getInstance();

        buttonContainer = findViewById(R.id.buttonContainer);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        Random rand = new Random();
//        Player currPlayer = game.getPlayer();
        //Assign the player a random system

        View v = (View) findViewById(R.id.buttonContainer);
        galaxyViewmodel.generateGalaxy(v.getWidth(), v.getHeight());

//        currPlayer.setCurrentSystem(galaxyViewmodel.getRandomSystem());
//        //Assign the player a random planet in that system
//        currPlayer.setCurrentPlanet(currPlayer.getCurrentSystem().getListOfPlanets().get(
//                rand.nextInt(currPlayer.getCurrentSystem().getListOfPlanets().size())));
//        //Update the player
//        configuration_viewmodel.updatePlayer(currPlayer);
    }
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        View v = (View) findViewById(R.id.buttonContainer);
        viewCenterX = v.getWidth() / 2;
        viewCenterY = v.getHeight() / 2;
        Random rand = new Random();
        Player currPlayer = game.getPlayer();
        galaxyViewmodel.generateGalaxy(viewCenterX * 2, viewCenterY * 2);
        currPlayer.setCurrentSystem(galaxyViewmodel.getRandomSystem());
        //Assign the player a random planet in that system
        currPlayer.setCurrentPlanet(currPlayer.getCurrentSystem().getListOfPlanets().get(
                rand.nextInt(currPlayer.getCurrentSystem().getListOfPlanets().size())));
        //Update the player
        configuration_viewmodel.updatePlayer(currPlayer);
        List<ImageButton> buttonList = new ArrayList<>();
        for (SolarSystem system : game.getSystemList()) {
            double xPos = system.getSysLocation().getxPos();
            double yPos = system.getSysLocation().getyPos();
            generateSystemButton(xPos, yPos, buttonContainer, buttonList);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.galaxy_view_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void generateSystemButton(double xPos, double yPos, RelativeLayout layout, List<ImageButton> buttonList) {

        ImageButton systemButton = new ImageButton(this);
        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem);
        image = Bitmap.createScaledBitmap(image, galaxyButtonSize.x, galaxyButtonSize.y, true);
        systemButton.setImageBitmap(image);
        systemButton.setBackgroundResource(0);
        buttonList.add(systemButton);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((int)xPos + viewCenterX, (int)yPos + viewCenterY, 0, 0);
        layout.addView(systemButton, params);
    }

}
