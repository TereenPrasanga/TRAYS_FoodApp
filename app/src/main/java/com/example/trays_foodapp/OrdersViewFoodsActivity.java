package com.example.trays_foodapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrdersViewFoodsActivity extends AppCompatActivity {


    private RecyclerView recyclerView1;
    private DatabaseReference reference;

    private Context mContext = OrdersViewFoodsActivity.this;

    private ArrayList<Orders> orderList;

    private RecyclerOrdersAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_view_foods);


        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerViewOrders);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setHasFixedSize(true);


        reference = FirebaseDatabase.getInstance().getReference();



        orderList = new ArrayList<>();

        init();
    }

    private void init() {

        clearAll();

        Query query = reference.child("OrderProducts");

        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    // Images images = new Images();
                    // images.setUrl(snapshot.child("image").getValue().toString());

                    // images.setName(snapshot.child("name").getValue().toString());

                    // imageList.add(images);

                    Orders orders = new Orders();
                    orders.setUname(snapshot.child("uname").getValue().toString());
                    orders.setPname(snapshot.child("pname").getValue().toString());
                    orders.setPrice(snapshot.child("price").getValue().toString());
                    orders.setDate(snapshot.child("date").getValue().toString());
                    orders.setTime(snapshot.child("time").getValue().toString());

                    orderList.add(orders);

                    recyclerAdapter = new RecyclerOrdersAdapter(mContext,orderList);
                    recyclerView1.setAdapter(recyclerAdapter);

                    recyclerAdapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void clearAll() {

        if(orderList != null)
        {
            orderList.clear();



            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    }


}
