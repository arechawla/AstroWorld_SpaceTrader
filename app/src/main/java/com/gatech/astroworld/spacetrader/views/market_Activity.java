package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.views.dummy.DummyContent;
import com.gatech.astroworld.spacetrader.views.market.Buy_ItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class market_Activity extends AppCompatActivity implements Buy_ItemFragment.OnListFragmentInteractionListener {
    private BottomNavigationView bottomNav;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_activity);
        NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavMenu);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return true;
            }
        });

        setupBottomMenu(nav);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    private void setupBottomMenu (NavController controller) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavMenu);
        NavigationUI.setupWithNavController(bottomNavigationView, controller);
        final NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.destination_buy:
                        nav.navigate(R.id.toBuyFragment);
                        return true;
                    case R.id.destination_sell:
                        nav.navigate(R.id.toSellFragment);
                        return true;
                    case R.id.destination_cart:
                        nav.navigate(R.id.toCartFragment);
                        return true;
                    default: return false;
                }
            }
        });
    }
}
