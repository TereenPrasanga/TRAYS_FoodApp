package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerPromoFinalAdapter extends RecyclerView.Adapter<RecyclerPromoFinalAdapter.ViewHolderFinalPromo> {
    private Context mContext;
    private ArrayList<promotion> itemsList;

    OnPromoClickFinalListner mListner;

    public interface OnPromoClickFinalListner{   // interface
        void onPromoClick(int position);
    }
    public void setOnPromoClickFinalListner(OnPromoClickFinalListner listner){  //setter for interface attribute
        mListner = listner;
    }

    public RecyclerPromoFinalAdapter(Context mContext, ArrayList<promotion> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }


    @NonNull
    @Override
    public RecyclerPromoFinalAdapter.ViewHolderFinalPromo onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.final_promo_recycler_view,viewGroup,false);

        return new ViewHolderFinalPromo(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerPromoFinalAdapter.ViewHolderFinalPromo viewHolderFinalPromo, int i) {
        viewHolderFinalPromo.productName.setText(itemsList.get(i).getName());
        viewHolderFinalPromo.prices.setText("LKR. "+itemsList.get(i).getPrice()+".00");
        Picasso.get().load(itemsList.get(i).getUrl())
                .into(viewHolderFinalPromo.imageView);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ViewHolderFinalPromo extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView productName,prices;

        public ViewHolderFinalPromo(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageviews1);
            productName = (TextView) itemView.findViewById(R.id.productName1);
            prices = (TextView) itemView.findViewById(R.id.prices1);

            itemView.setOnClickListener(new View.OnClickListener() {   // redirect the data using onclick method
                @Override
                public void onClick(View view) {
                    if (mListner != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onPromoClick(position);
                        }
                    }
                }
            });
        }
    }



}
