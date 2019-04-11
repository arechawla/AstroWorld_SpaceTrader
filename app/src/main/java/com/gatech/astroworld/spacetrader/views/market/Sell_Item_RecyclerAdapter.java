package com.gatech.astroworld.spacetrader.views.market;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.gatech.astroworld.spacetrader.R;

import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.gatech.astroworld.spacetrader.model.Store;
import com.gatech.astroworld.spacetrader.views.market.Sell_ItemFragment.OnListFragmentInteractionListener;
import com.gatech.astroworld.spacetrader.views.market_Activity;

import java.util.List;

/**
 *
 * TODO: Replace the implementation with code for your data type.
 */
public class Sell_Item_RecyclerAdapter extends RecyclerView.Adapter<Sell_Item_RecyclerAdapter.ViewHolder> {

    private final List<TradeGood> mValues;
    private final OnListFragmentInteractionListener mListener;
    public static int mSellTotal = 0;
    private Store store;

    public Sell_Item_RecyclerAdapter(List<TradeGood> items, OnListFragmentInteractionListener listener) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());
        String price = String.format("%11s", "Price: " + Integer.toString(mValues.get(position).getPrice()));
        String qty = String.format("%11s", "Qty: " + Integer.toString(mValues.get(position).getQuantity()));
        holder.mPriceView.setText(price + "\n" + qty);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final Button plusButton;
        public final Button minusButton;
        public TradeGood mItem;
        public TextView mPriceView;

        public ViewHolder(View view) {
            super(view);
            plusButton = view.findViewById(R.id.plusButton);
            minusButton = view.findViewById(R.id.minusButton);
            final TextView itemCountText = view.findViewById(R.id.countText);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView =  view.findViewById(R.id.itemName);
            mPriceView = view.findViewById(R.id.itemPrice);


            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = mItem.getSellCount();
                    store.incrementCountSell(mItem);
                    if (mItem.getSellCount() != i) {
                        mSellTotal += mItem.getPrice();
                        updateTotal();
                    }
                    itemCountText.setText(String.valueOf(mItem.getSellCount()));
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = mItem.getSellCount();
                    store.decrementCountSell(mItem);
                    if (mItem.getSellCount() != i) {
                        mSellTotal -= mItem.getPrice();
                        updateTotal();
                    }
                    itemCountText.setText(String.valueOf(mItem.getSellCount()));
                }
            });
        }

        public void updateTotal() {
            market_Activity.mShowTotal.setText(String.valueOf(mSellTotal));
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
