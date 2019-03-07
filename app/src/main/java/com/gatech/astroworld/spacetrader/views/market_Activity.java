package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.views.market.Buy_ItemFragment;
import com.gatech.astroworld.spacetrader.views.market.Cart_ItemFragment;
import com.gatech.astroworld.spacetrader.views.market.Sell_ItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class market_Activity extends AppCompatActivity implements Buy_ItemFragment.OnListFragmentInteractionListener,
        Cart_ItemFragment.OnListFragmentInteractionListener, Sell_ItemFragment.OnListFragmentInteractionListener {
    private BottomNavigationView bottomNav;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_activity);
        final NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavMenu);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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

    @Override
    public void onListFragmentInteraction(com.gatech.astroworld.spacetrader.views.market.dummy.DummyContent.DummyItem item) {

    }
}
