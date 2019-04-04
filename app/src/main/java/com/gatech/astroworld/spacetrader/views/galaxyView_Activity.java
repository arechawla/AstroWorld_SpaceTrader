package com.gatech.astroworld.spacetrader.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.Save;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;

import android.util.AttributeSet;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.gatech.astroworld.spacetrader.R;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class galaxyView_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout buttonContainer;
    private static RelativeLayout dimensions;
    private Galaxy_viewmodel galaxyViewmodel;
    private Configuration_viewmodel configuration_viewmodel;
    private Game game;
    private int viewCenterX;
    private int viewCenterY;
    private Point galaxyButtonSize = new Point(100, 100);
    private HashMap<ImageButton, SolarSystem> systemButtons = new HashMap<>();
    private HashMap.Entry destination;
    int count = 0;
    private AlertDialog.Builder travelAlertBuilder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaxy_view_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Galaxy Map");
        setSupportActionBar(toolbar);
        galaxyViewmodel = ViewModelProviders.of(this).get(Galaxy_viewmodel.class);
        configuration_viewmodel = ViewModelProviders.of(this).get(Configuration_viewmodel.class);
        //Generate buttons for galaxy view
        game = Game.getInstance();


        buttonContainer = findViewById(R.id.buttonContainer);
        dimensions = findViewById(R.id.buttonContainer);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        buttonContainer = findViewById(R.id.buttonContainer);
        View v = (View) buttonContainer;
//        galaxyViewmodel.generateGalaxy(v.getWidth(), v.getHeight());
        travelAlertBuilder = new AlertDialog.Builder(this);
        travelAlertBuilder.setPositiveButton("Travel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player player = Game.getInstance().getPlayer();
                SolarSystem s = (SolarSystem) destination.getValue();
                String message = "A fellow traveler grants you 100cr on your journey to "
                        + s.getName();
                if (player.getShip().randomEncounter()) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Traveled", Toast.LENGTH_LONG).show();
                }
                Player currPlayer = game.getPlayer();
                View v = (View) buttonContainer;
                double fuelUse = currPlayer.getShip().travelSolarSystem(currPlayer.getCurrentSystem(),
                        (SolarSystem) destination.getValue(), new Point(v.getWidth(), v.getHeight()));
                double shipFuel = currPlayer.getShip().getFuel();
                currPlayer.getShip().setFuel(shipFuel - fuelUse);

                //UPDATE CURRENT SYSTEM REFERENCE FOR FIREBASE
                SolarSystem ss = (SolarSystem) destination.getValue();
                player.setCurSystemReference(Game.getInstance().getSystemList().indexOf(ss));
                Save.savePlayerInformation();

                currPlayer.setCurrentSystem((SolarSystem) destination.getValue());
                currPlayer.setCurrentPlanet(null);
                Intent i = new Intent(getApplicationContext(), systemView_Activity.class);
                startActivity(i);
            }
        });
        travelAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Travel Canceled", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static int viewX() {
        return dimensions.getWidth();
    }

    public static int viewY() {
        return dimensions.getHeight();
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        View v = (View) buttonContainer;
        viewCenterX = v.getWidth() / 2;
        viewCenterY = v.getHeight() / 2;
        Player currPlayer = game.getPlayer();
        configuration_viewmodel.updatePlayer(currPlayer);
        if (count == 0) {
            for (SolarSystem system : game.getSystemList()) {
                double xPos = system.getSysLocation().getxPos();
                double yPos = system.getSysLocation().getyPos();
                generateSystemButton(xPos, yPos, buttonContainer, systemButtons, system);
            }
            count++;
        }
        for (HashMap.Entry b: systemButtons.entrySet()) {
            final HashMap.Entry entry = b;
            ImageButton button = (ImageButton) b.getKey();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    final Toast error = Toast.makeText(getApplicationContext(), "You do not" +
                            " have enough fuel to go to this location!", Toast.LENGTH_SHORT);
                    final Toast errorSameSys = Toast.makeText(getApplicationContext(), "Click Solar System" +
                            " view in drawer!", Toast.LENGTH_SHORT);
                    Player currPlayer = game.getPlayer();
                    double fuelUse = currPlayer.getShip().travelSolarSystem(currPlayer.getCurrentSystem(),
                            (SolarSystem) entry.getValue(), new Point(v.getWidth(), v.getHeight()));
                    if (fuelUse == -1) {
                        error.show();
                    } else if (fuelUse == 0){
                        errorSameSys.show();
                    } else {
                        destination = entry;
                        travelAlertBuilder.setMessage("Do you want to travel to " + entry.getValue().toString() + "?")
                                .setTitle("Travel?");
                        travelAlertBuilder.show();
                    }
                }
            });
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

        } else if (id == R.id.nav2_System) {
            Intent i = new Intent(getApplicationContext(), systemView_Activity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void generateSystemButton(double xPos, double yPos, RelativeLayout layout,
                                      HashMap<ImageButton, SolarSystem> buttonList, SolarSystem system) {

        ImageButton systemButton = new ImageButton(this);
        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem);
        image = Bitmap.createScaledBitmap(image, galaxyButtonSize.x, galaxyButtonSize.y, true);
        Bitmap curImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem_current);
        curImage = getResizedBitmap(curImage, 110);
        if (system.equals(Game.getInstance().getPlayer().getCurrentSystem())) {
            systemButton.setImageBitmap(image);
            systemButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.currentGreen), PorterDuff.Mode.MULTIPLY);
        } else {
            systemButton.setImageBitmap(image);

        }
        systemButton.setBackgroundResource(0);
        buttonList.put(systemButton, system);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((int)xPos + viewCenterX, (int)yPos + viewCenterY, 0, 0);
        layout.addView(systemButton, params);
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }





}
