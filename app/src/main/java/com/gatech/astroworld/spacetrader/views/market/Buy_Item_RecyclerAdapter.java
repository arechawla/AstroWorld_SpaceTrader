package com.gatech.astroworld.spacetrader.views.market;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.gatech.astroworld.spacetrader.R;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Store;
import com.gatech.astroworld.spacetrader.views.market.Buy_ItemFragment.OnListFragmentInteractionListener;
import com.gatech.astroworld.spacetrader.views.market_Activity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class Buy_Item_RecyclerAdapter extends RecyclerView.Adapter<Buy_Item_RecyclerAdapter.ViewHolder> {
    private final List<Store.MarketGood> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Store store;
    int mTotal = 0;


    public Buy_Item_RecyclerAdapter(List<Store.MarketGood> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        store = Game.getInstance().getPlayer().getCurrentPlanet().getStore();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_market_buy_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
        holder.mPriceView.setText(String.valueOf(mValues.get(position).getPrice()));
//        market_Activity.mTotal.setText("testing");

    }
    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mIdView;
        public TextView mContentView;
        public TextView mPriceView;
        public Store.MarketGood mItem;



        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.itemName);
            mPriceView = view.findViewById(R.id.itemPrice);
            final TextView itemCountText = view.findViewById(R.id.countText);
            Button plusButton = mView.findViewById(R.id.plusButton);
            Button minusButton = mView.findViewById(R.id.minusButton);


            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    store.incrementCountBuy(mItem);
                    itemCountText.setText(String.valueOf(mItem.getCount()));

                    mListener.onListFragmentInteraction(mItem);
                    mTotal += mItem.getPrice();
                    updateTotal();
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    store.decrementCountBuy(mItem);
                    itemCountText.setText(String.valueOf(mItem.getCount()));
                    int i = mTotal - mItem.getPrice();
                    if (i >= 0) {
                        mTotal = i;
                        updateTotal();
                    }
                }
            });
        }

        public int getMTotal() {
            return mTotal;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        public void updateTotal() {
            market_Activity.mBuyTotal.setText(String.valueOf(mTotal));
        }
    }

}
