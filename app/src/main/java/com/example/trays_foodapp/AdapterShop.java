package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterShop extends RecyclerView.Adapter<AdapterShop.ViewHolderShop> {

    private Context mContext;
    private ArrayList<ShopClass> shopList;


    private OnItemClicklisnerShop mListener;

    public AdapterShop(Context mContext, ArrayList<ShopClass> shopList) {
        this.mContext = mContext;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public   ViewHolderShop onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shop_delete_list_row,viewGroup,false);
       return new ViewHolderShop(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderShop viewHolderShop, int i) {
        viewHolderShop.pname.setText(shopList.get(i).getName());
        viewHolderShop.plocation.setText(shopList.get(i).getLocation());
        viewHolderShop.ptelephone.setText(shopList.get(i).getTelephone());
        Picasso.get().load(shopList.get(i).getImage()).into(viewHolderShop.imageView);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }



    public class ViewHolderShop extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnClickListener ,MenuItem.OnMenuItemClickListener{
        ImageView imageView;
        TextView pname,plocation,ptelephone;

        public ViewHolderShop(View itemView)
        {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.retriewShopImage);
            pname = (TextView)itemView.findViewById(R.id.retriewShopName);
            plocation = (TextView)itemView.findViewById(R.id.retriewShopLocation);
            ptelephone = (TextView)itemView.findViewById(R.id.retriewShoptelephone);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem dowhatever = menu.add(Menu.NONE,1,1,"Do what Ever");
            MenuItem delete = menu.add(Menu.NONE,2,2,"delete");

            dowhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    mListener.onItemCilickShop(position);
                }
            }

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener!= null)
            {
                int position = getAdapterPosition();
                if (position!= RecyclerView.NO_POSITION)
                {
                    switch (item.getItemId()){
                        case 1:
                            mListener.onWhatEverShop(position);
                            return true;
                        case 2:
                            mListener.onDeleteClickShop(position);
                            return true;
                    }
                }
            }
                return false;
        }
    }

    public interface OnItemClicklisnerShop{
        void onItemCilickShop(int position);
        void onWhatEverShop(int position);
        void onDeleteClickShop(int position);

    }
    public void setOnItemClicklisnerShop(OnItemClicklisnerShop listener){
        mListener = listener;
    }

}
