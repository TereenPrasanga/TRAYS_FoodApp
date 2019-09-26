package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerOrdersAdapter extends RecyclerView.Adapter<RecyclerOrdersAdapter.ViewOrderHolder> {

    private Context mContext;
    private ArrayList<Orders> ordersList;

    public RecyclerOrdersAdapter(Context mContext, ArrayList<Orders> ordersList) {
        this.mContext = mContext;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public ViewOrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_order_foods,viewGroup,false);

        return new ViewOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOrderHolder viewOrderHolder, int i) {


        viewOrderHolder.uname.setText("Cus. Name : "+ordersList.get(i).getUname());
        viewOrderHolder.pname.setText("Product : "+ordersList.get(i).getPname());
        viewOrderHolder.price.setText("Price : "+"LKR."+ordersList.get(i).getPrice()+".00");
        viewOrderHolder.date.setText("Date : "+ordersList.get(i).getDate());
        viewOrderHolder.time.setText("Time : "+ordersList.get(i).getTime());

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewOrderHolder extends RecyclerView.ViewHolder {

        TextView uname,pname,price,date,time;



        public ViewOrderHolder(@NonNull View itemView) {
            super(itemView);


            uname = (TextView) itemView.findViewById(R.id.uname);
            pname = (TextView) itemView.findViewById(R.id.pname);
            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);
            time = (TextView) itemView.findViewById(R.id.time);




        }
    }
}
