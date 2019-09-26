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

public class RecyclerFoodsFinalAdapter extends RecyclerView.Adapter<RecyclerFoodsFinalAdapter.ViewHolderFinalFoods> {

    private Context mContext;
    private ArrayList<Uploads> itemsList;

    OnItemClickFinalListner mListner;

    public interface OnItemClickFinalListner{   // interface
        void onItemClick(int position);
    }

    public void setOnItemClickFinalListner(OnItemClickFinalListner listner){  //setter for interface attribute
        mListner = listner;
    }

    public RecyclerFoodsFinalAdapter(Context mContext, ArrayList<Uploads> itemsList) {
        this.mContext = mContext;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ViewHolderFinalFoods onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.final_foods_recyclerview,viewGroup,false);

        return new ViewHolderFinalFoods(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFinalFoods viewHolderFinalFoods, int i) {


        viewHolderFinalFoods.productName.setText(itemsList.get(i).getName());
        viewHolderFinalFoods.prices.setText("LKR. "+itemsList.get(i).getPrice()+".00");
        Picasso.get().load(itemsList.get(i).getUrl())
                .into(viewHolderFinalFoods.imageView);
    }

    @Override
    public int getItemCount() {
        return  itemsList.size();
    }


    public class ViewHolderFinalFoods extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView productName,prices;


        public ViewHolderFinalFoods(@NonNull View itemView) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.imageviews);
            productName = (TextView) itemView.findViewById(R.id.productName);
            prices = (TextView) itemView.findViewById(R.id.prices);

            itemView.setOnClickListener(new View.OnClickListener() {   // redirect the data using onclick method
                @Override
                public void onClick(View view) {
                    if (mListner != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
