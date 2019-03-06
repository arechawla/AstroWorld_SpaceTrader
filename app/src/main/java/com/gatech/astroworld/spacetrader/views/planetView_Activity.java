package com.gatech.astroworld.spacetrader.views;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.gatech.astroworld.spacetrader.R;

public class planetView_Activity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_view_);
        Button marketButton = findViewById(R.id.accessMarket_button);

        marketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), market_Activity.class);
                startActivity(i);
            }
        });

    }
}
