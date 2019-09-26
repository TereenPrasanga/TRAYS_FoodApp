package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterPromoDelete extends RecyclerView.Adapter<RecyclerAdapterPromoDelete.ViewHolderDeleteX> {

    private OnPromoClickListnerX mListner;

    private Context mContex;

    private ArrayList<promotion> imagesLists;

    public RecyclerAdapterPromoDelete(Context mContex, ArrayList<promotion> imagesLists) {
        this.mContex = mContex;
        this.imagesLists = imagesLists;
    }

    @NonNull
    @Override
    public RecyclerAdapterPromoDelete.ViewHolderDeleteX onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recyclerview_promo,viewGroup,false);



        return new ViewHolderDeleteX(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterPromoDelete.ViewHolderDeleteX viewHolderDelete, int i) {

        viewHolderDelete.pname.setText(imagesLists.get(i).getName());
        viewHolderDelete.price.setText(imagesLists.get(i).getPrice());

        Picasso.get().load(imagesLists.get(i).getUrl())
                .into(viewHolderDelete.imageView);
    }

    @Override
    public int getItemCount() {
        return imagesLists.size();
    }

    public class ViewHolderDeleteX extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        ImageView imageView;
        TextView pname;
        TextView price;

        public ViewHolderDeleteX(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView11);
            pname = (TextView) itemView.findViewById(R.id.productName11);
            price = (TextView) itemView.findViewById(R.id.prices11);



            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListner != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mListner.onWhatEverClickx(position);
                            return true;
                        case 2:
                            mListner.onDeleteClickx(position);
                            return true;

                    }
                }
            }
            return false;
        }

        @Override
        public void onClick(View v) {

            if(mListner != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListner.onItemClickx(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE,1,1,"Do whatever");
            MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

    }
    public interface OnPromoClickListnerX{
        void onItemClickx(int position);

        void onWhatEverClickx(int position);

        void onDeleteClickx(int position);
    }
    public void setOnPromoClickListnerX(OnPromoClickListnerX listner){

        mListner = listner;
    }
}

