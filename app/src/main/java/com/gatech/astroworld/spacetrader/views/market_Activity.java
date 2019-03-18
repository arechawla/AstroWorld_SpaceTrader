package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.gatech.astroworld.spacetrader.model.Store;
import com.gatech.astroworld.spacetrader.views.market.Buy_ItemFragment;
import com.gatech.astroworld.spacetrader.views.market.Buy_Item_RecyclerAdapter;
import com.gatech.astroworld.spacetrader.views.market.Sell_ItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class market_Activity extends AppCompatActivity implements
        Buy_ItemFragment.OnListFragmentInteractionListener,
        Sell_ItemFragment.OnListFragmentInteractionListener {
    private BottomNavigationView bottomNav;
    private int selectedItem;
    public static TextView mBuyTotal;
//    public static boolean what = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_activity);
        final NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        final Toast error3 = Toast.makeText(getApplicationContext(), "You cannot" +
                " hold this many items in the ship!", Toast.LENGTH_LONG);
        final TextView remainingCredits = findViewById(R.id.yourCredits);
        Button buy = findViewById(R.id.buyButton);
        Button sell = findViewById(R.id.sellButton);
        mBuyTotal = findViewById(R.id.buyTotal);
        String credits = String.valueOf(Game.getInstance().getPlayer().getCredits());
        remainingCredits.setText("Remaining Credits: " +
                String.valueOf(Game.getInstance().getPlayer().getCredits()));
        bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavMenu);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.destination_buy:
                        if (nav.getCurrentDestination().getId() ==R.id.destination_buy) {
                            return false;
                        }
                        nav.navigate(R.id.toBuyFragment);
                        return true;
                    case R.id.destination_sell:
                        if (nav.getCurrentDestination().getId() ==R.id.destination_sell) {
                            return false;
                        }
                        nav.navigate(R.id.toSellFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int availSpace = Game.getInstance().getPlayer().getShip().getCapacity() -
                        Game.getInstance().getPlayer().getShip().cargoAmount();
                if (Buy_Item_RecyclerAdapter.mCountTot > availSpace) {
                    error3.show();
                } else {
                    Game.getInstance().getPlayer().getCurrentPlanet().getStore()
                            .buy(Game.getInstance().getPlayer());
                    remainingCredits.setText("Remaining Credits: " +
                            String.valueOf(Game.getInstance().getPlayer().getCredits()));
                    Buy_Item_RecyclerAdapter.mCountTot = 0;
                    nav.navigate(R.id.toBuyFragment);
                }
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Game.getInstance().getPlayer().getCurrentPlanet().getStore()
                        .sell(Game.getInstance().getPlayer());
                remainingCredits.setText("Remaining Credits: " +
                        String.valueOf(Game.getInstance().getPlayer().getCredits()));
                nav.navigate(R.id.toSellFragment);
            }
        });
    }

    @Override
    public void onListFragmentInteraction(MarketGood item) {

    }

    @Override
    public void onListFragmentInteraction(TradeGood item) {

    }

    public String updatePlayerCredits() {
        String cred = String.valueOf(Game.getInstance().getPlayer().getCredits());
        return  cred;
    }

//    @Override
//    public void onListFragmentInteraction(com.gatech.astroworld.spacetrader.views.market.dummy.DummyContent.DummyItem item) {
//
//    }
}
