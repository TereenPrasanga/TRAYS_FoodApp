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

public class SearchFoodsAdapter extends RecyclerView.Adapter<SearchFoodsAdapter.SearchFoodsViewHolder> {

    Context context;
    ArrayList<Uploads> IList;

    public SearchFoodsAdapter(Context context, ArrayList<Uploads> IList) {
        this.context = context;
        this.IList = IList;
    }



    private OnItemClick mListner;


    public interface OnItemClick{
        void onProductClick(int position);
    }



    public  void setOnItemClick(OnItemClick listner){
        mListner = listner;
    }

    public class SearchFoodsViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView Name;
        TextView Price;


        public SearchFoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.Image);
            Name = (TextView) itemView.findViewById(R.id.Name);
            Price = (TextView) itemView.findViewById(R.id.Price);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            mListner.onProductClick(position);
                        }
                    }
                }
            });



        }





    }

    @NonNull
    @Override
    public SearchFoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items,viewGroup,false);

        return new SearchFoodsAdapter.SearchFoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchFoodsViewHolder searchFoodsViewHolder, int i) {

        searchFoodsViewHolder.Name.setText(IList.get(i).getName());
        searchFoodsViewHolder.Price.setText("LKR.  "+IList.get(i).getPrice()+".00");

        Picasso.get().load(IList.get(i).getUrl())
                .into(searchFoodsViewHolder.image);

    }

    @Override
    public int getItemCount() {
        return IList.size();
    }





}
