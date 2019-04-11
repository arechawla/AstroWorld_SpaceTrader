package com.gatech.astroworld.spacetrader.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gatech.astroworld.spacetrader.R;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.gatech.astroworld.spacetrader.model.Save;
import com.gatech.astroworld.spacetrader.views.market.Buy_ItemFragment;
import com.gatech.astroworld.spacetrader.views.market.Buy_Item_RecyclerAdapter;
import com.gatech.astroworld.spacetrader.views.market.Sell_ItemFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.gatech.astroworld.spacetrader.views.market.Buy_Item_RecyclerAdapter.mBuyTotal;
import static com.gatech.astroworld.spacetrader.views.market.Sell_Item_RecyclerAdapter.mSellTotal;


public class market_Activity extends AppCompatActivity implements
        Buy_ItemFragment.OnListFragmentInteractionListener,
        Sell_ItemFragment.OnListFragmentInteractionListener {
    private int selectedItem;
    public static TextView mShowTotal;
//    public static boolean what = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_activity);
        final NavController nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        final Toast error3 = Toast.makeText(getApplicationContext(), "You cannot" +
                " hold this many items in the ship!", Toast.LENGTH_LONG);
        final TextView remainingCredits = findViewById(R.id.yourCredits);
        final Button buy = findViewById(R.id.buyButton);
        final Button sell = findViewById(R.id.sellButton);
        mShowTotal = findViewById(R.id.buyTotal);
        String credits = String.valueOf(Game.getInstance().getPlayer().getCredits());
        remainingCredits.setText("Remaining Credits: " +
                String.valueOf(Game.getInstance().getPlayer().getCredits()));
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottomNavMenu);
        sell.setEnabled(false);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.destination_buy:
                        if (nav.getCurrentDestination().getId() ==R.id.destination_buy) {
                            Game.getInstance().getPlayer().
                                    getCurrentPlanet().getStore().zeroMarketCounts();
                            Game.getInstance().getPlayer().
                                    getShip().zeroSellCounts();
                            mBuyTotal = 0;
                            market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));
                            mSellTotal = 0;
                            market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
                            return false;
                        }
                        Game.getInstance().getPlayer().
                                getCurrentPlanet().getStore().zeroMarketCounts();
                        Game.getInstance().getPlayer().
                                getShip().zeroSellCounts();

                        buy.setEnabled(true);
                        sell.setEnabled(false);
                        mBuyTotal = 0;
                        market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));
                        mSellTotal = 0;
                        market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
                        nav.navigate(R.id.toBuyFragment);
                        return true;
                    case R.id.destination_sell:
                        if (nav.getCurrentDestination().getId() ==R.id.destination_sell) {
                            Game.getInstance().getPlayer().
                                    getCurrentPlanet().getStore().zeroMarketCounts();
                            Game.getInstance().getPlayer().
                                    getShip().zeroSellCounts();
                            mBuyTotal = 0;
                            market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));
                            mSellTotal = 0;
                            market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
                            return false;
                        }
                        Game.getInstance().getPlayer().
                                getCurrentPlanet().getStore().zeroMarketCounts();
                        Game.getInstance().getPlayer().
                                getShip().zeroSellCounts();

                        buy.setEnabled(false);
                        sell.setEnabled(true);
                        mBuyTotal = 0;
                        market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));
                        mSellTotal = 0;
                        market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
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
                    mBuyTotal = 0;
                    market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));


                    nav.navigate(R.id.toBuyFragment);
                }

                Save.saveCreditsInformation();
                Save.saveSpaceShipInformation();

            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Game.getInstance().getPlayer().getCurrentPlanet().getStore()
                        .sell(Game.getInstance().getPlayer());
                remainingCredits.setText("Remaining Credits: " +
                        String.valueOf(Game.getInstance().getPlayer().getCredits()));
                mSellTotal = 0;
                market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));

                nav.navigate(R.id.toSellFragment);
//                Save.saveSolarSystemList();
                Save.saveCreditsInformation();
                Save.saveSpaceShipInformation();
            }
        });


    }


    /**
     * Overriding the back button. Need it to return to the planet view, instead
     * of flipping between fragments.
     */
    @Override
    public void onBackPressed() {
        Game.getInstance().getPlayer().
                getCurrentPlanet().getStore().zeroMarketCounts();
        Game.getInstance().getPlayer().
                getShip().zeroSellCounts();
        mBuyTotal = 0;
        market_Activity.mShowTotal.setText(String.valueOf(mBuyTotal));
        mSellTotal = 0;
        market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
        Intent i = new Intent(getApplicationContext(), planetView_Activity.class);
        startActivity(i);
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
