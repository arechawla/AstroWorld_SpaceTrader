package com.gatech.astroworld.spacetrader.views;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.Player;
import com.gatech.astroworld.spacetrader.model.Save;
import com.gatech.astroworld.spacetrader.viewmodels.Configuration_viewmodel;
import com.gatech.astroworld.spacetrader.viewmodels.System_viewmodel;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
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
    private AlertDialog.Builder travelAlertBuilder;
    private HashMap.Entry destination;
    private AlertDialog.Builder leaveSystemBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_view_);
        game = Game.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle(game.getPlayer().getCurrentSystem().toString() + " Solar System");
        setSupportActionBar(toolbar);
        systemViewmodel = ViewModelProviders.of(this).get(System_viewmodel.class);
        configuration_viewmodel = ViewModelProviders.of(this).get(Configuration_viewmodel.class);
        //Generate buttons for galaxy view


        buttonContainer = findViewById(R.id.buttonContainer2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navPlanets_view);
        navigationView.setNavigationItemSelectedListener(this);
        travelAlertBuilder = new AlertDialog.Builder(this);

        travelAlertBuilder.setPositiveButton("Travel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Player player = Game.getInstance().getPlayer();
                Planet p = (Planet) destination.getValue();
                String message = "Congrats! The citizens of " + p.getName()
                        + " gift you 100 credits to show their hospitality.";
                if (player.getShip().randomEncounter()) {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Traveled", Toast.LENGTH_SHORT).show();
                }
                Player currPlayer = game.getPlayer();
                View v = buttonContainer;
                double fuelUse = currPlayer.getShip().travelPlanet(currPlayer, (Planet) destination.getValue(), new Point(v.getWidth(), v.getHeight()));
                double shipFuel = currPlayer.getShip().getFuel();
                currPlayer.getShip().setFuel(shipFuel - fuelUse);


                //UPDATE CURRENT PLANET REF FOR DATABSE
                Planet curr = (Planet) destination.getValue();
                player.setCurPlanetReference(player.getCurrentSystem().getListOfPlanets().indexOf(curr));
                Save.savePlayerInformation();


                currPlayer.setCurrentPlanet((Planet) destination.getValue());
                Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
                startActivity(i);
            }
        });
        travelAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Travel Canceled", Toast.LENGTH_LONG).show();
            }
        });



        leaveSystemBuilder = new AlertDialog.Builder(this);
        leaveSystemBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Out of the Solar System",
                        Toast.LENGTH_LONG).show();
                Player currPlayer = game.getPlayer();
                currPlayer.setCurrentPlanet(null);
                currPlayer.setCurPlanetReference(-1);
                Save.savePlayerInformation();
                Intent i = new Intent(getApplicationContext(), galaxyView_Activity.class);
                startActivity(i);
            }
        });
        leaveSystemBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
    }


    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        View v = (View) findViewById(R.id.buttonContainer2);
        viewCenterX = v.getWidth() / 2;
        viewCenterY = v.getHeight() / 2;
        Random rand = new Random();
        Player currPlayer = game.getPlayer();
        //Assign the player a random planet in that system
        //Update the player
        configuration_viewmodel.updatePlayer(currPlayer);
        HashMap<ImageButton, Planet> planetButtons = new HashMap<>();

        for (Planet planet : systemViewmodel.getPlanetList()) {
            System.out.println("Before generate system button");
            double xPos = planet.getPlanLocation().getxPos();
            double yPos = planet.getPlanLocation().getyPos();
            generatePlanetButton(xPos, yPos, buttonContainer, planetButtons, planet);
        }
        for (HashMap.Entry b: planetButtons.entrySet()) {
            final HashMap.Entry entry = b;
            ImageButton button = (ImageButton) b.getKey();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    final Toast error = Toast.makeText(getApplicationContext(), "You do not" +
                            " have enough fuel to go to this location!", Toast.LENGTH_LONG);
                    Player currPlayer = game.getPlayer();
                    double fuelUse = currPlayer.getShip().travelPlanet(currPlayer, (Planet) entry.getValue(), new Point(v.getWidth(), v.getHeight()));
                    if (fuelUse == -1) {
                        error.show();
                    } else {
                        destination = entry;
                        travelAlertBuilder.setMessage("Do you want to travel to " + entry.getValue().toString() + "?")
                                .setTitle("Travel?");
                        travelAlertBuilder.show();
//                        currPlayer.setCurrentPlanet((Planet) entry.getValue());
//                        double shipFuel = currPlayer.getShip().getFuel();
//                        currPlayer.getShip().setFuel(shipFuel - fuelUse);
//                        Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
//                        startActivity(i);
                    }
                }
            });
        }
        configuration_viewmodel.updatePlayer(currPlayer);
    }

    @Override
    public void onBackPressed() {
        return;

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

        if (id == R.id.nav2_player) {
            Intent i = new Intent(getApplicationContext(), PlayerInfo.class);
            startActivity(i);
        } else if (id == R.id.nav2_Galaxy) {
            leaveSystemBuilder.setMessage("Are you sure you want to leave the Solar System?")
                    .setTitle("Travel?");
            leaveSystemBuilder.show();
//            Intent i = new Intent(getApplicationContext(), galaxyView_Activity.class);
//            startActivity(i);
        } else if (id == R.id.nav2_ship) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void generatePlanetButton(double xPos, double yPos, RelativeLayout layout,
                                      HashMap<ImageButton, Planet> buttonList, Planet planet) {

        ImageButton planetButton = new ImageButton(this);
        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem);
        image = Bitmap.createScaledBitmap(image, systemButtonSize.x, systemButtonSize.y, true);
        Bitmap curImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.system_emblem_current);
        curImage = getResizedBitmap(curImage, 110);


        if (Game.getInstance().getPlayer().getCurrentPlanet() != null) {
            if (planet.equals(Game.getInstance().getPlayer().getCurrentPlanet())) {
                planetButton.setImageBitmap(image);
                planetButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.currentGreen), PorterDuff.Mode.MULTIPLY);
            }
            else {
                planetButton.setImageBitmap(image);
                planetButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.rockBrown), PorterDuff.Mode.MULTIPLY);
            }
        }

         else {
            planetButton.setImageBitmap(image);
            planetButton.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.rockBrown), PorterDuff.Mode.MULTIPLY);
        }


        planetButton.setBackgroundResource(0);
        buttonList.put(planetButton, planet);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins((int)xPos + viewCenterX, (int)yPos + viewCenterY, 0, 0);
        layout.addView(planetButton, params);
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
