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


public class SearchPromoAdapter extends RecyclerView.Adapter<SearchPromoAdapter.searchPromoViewHolder> {

    Context context;
    ArrayList<promotion> IList;

    public SearchPromoAdapter(Context context, ArrayList<promotion> IList) {
        this.context = context;
        this.IList = IList;
    }
    private onPromoclick mListner;

    public interface onPromoclick{
        void onPromoClick(int position);
    }
    public void setonPromoclick(onPromoclick Listner){
        mListner = Listner;
    }

    public class searchPromoViewHolder extends RecyclerView.ViewHolder{
        ImageView Image;
        TextView Name;
        TextView Price;

        public searchPromoViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = (ImageView) itemView.findViewById(R.id.Image);
            Name = (TextView) itemView.findViewById(R.id.Name);
            Price = (TextView) itemView.findViewById(R.id.Price);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mListner.onPromoClick(position);
                        }
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public SearchPromoAdapter.searchPromoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchlist_promotion,viewGroup,false);

        return new SearchPromoAdapter.searchPromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPromoAdapter.searchPromoViewHolder searchPromoViewHolder, int i) {
        searchPromoViewHolder.Name.setText(IList.get(i).getName());
        searchPromoViewHolder.Price.setText(IList.get(i).getPrice());

        Picasso.get().load(IList.get(i).getUrl())
                .into(searchPromoViewHolder.Image);


    }

    @Override
    public int getItemCount() {
        return IList.size();
    }
}
