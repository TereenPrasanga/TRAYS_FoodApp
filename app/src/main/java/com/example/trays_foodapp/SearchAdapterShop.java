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

public class SearchAdapterShop extends RecyclerView.Adapter<SearchAdapterShop.SearchViewHolder>{
    private Context mContext;
    private ArrayList<ShopClass> shopList;

    private OnItemClick mListener;

    public SearchAdapterShop(Context mContext, ArrayList<ShopClass> shopList) {
        this.mContext = mContext;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_view_list_row,viewGroup,false);
        return new SearchAdapterShop.SearchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

        searchViewHolder.name.setText(shopList.get(i).getName());
        searchViewHolder.location.setText(shopList.get(i).getLocation());
        searchViewHolder.telephone.setText(shopList.get(i).getTelephone());
        Picasso.get().load(shopList.get(i).getImage()).into(searchViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }




    public interface OnItemClick{
        void onproductClick(int position);

    }

    public void setOnItemClick(OnItemClick listener)
    {
        mListener = listener;
    }
    class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,location,telephone;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.SearchShopImage);
            name = (TextView)itemView.findViewById(R.id.SearchShopName);
            location = (TextView)itemView.findViewById(R.id.SearchShopLocation);
            telephone = (TextView)itemView.findViewById(R.id.SearchShoptelephone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!= null)
                    {
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION)
                        {
                            mListener.onproductClick(position);
                        }
                    }
                }
            });
        }
    }


}
