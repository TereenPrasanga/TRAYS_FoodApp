package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
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

import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_DES;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_IMAGE;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_NAME;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_PRICE;

public class Final_promo_Activity extends AppCompatActivity implements RecyclerPromoFinalAdapter.OnPromoClickFinalListner{
    private RecyclerView recyclerView3;
    private DatabaseReference reference;
    private DatabaseReference mDatabaseRef;

    private Context mContext = Final_promo_Activity.this;

    private ArrayList<promotion> itemsLists;

    private RecyclerPromoFinalAdapter recyclerAdapterFinal;

    private ValueEventListener mDBListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_promo_);

        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerviewpromo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("AddPromotion");

        //  itemsList = new ArrayList<>();

        itemsLists = new ArrayList<>();


        init();
    }

        private void init() {


            clearAll();

            Query query = reference.child("AddPromotion");

            query.addListenerForSingleValueEvent(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    itemsLists.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        promotion uploads1 = new promotion();


                            uploads1.setUrl(snapshot.child("image").getValue().toString());

                            uploads1.setName(snapshot.child("name").getValue().toString());

                            uploads1.setPrice(snapshot.child("price").getValue().toString());

                            uploads1.setDes(snapshot.child("description").getValue().toString());

                            itemsLists.add(uploads1);


                        recyclerAdapterFinal = new RecyclerPromoFinalAdapter(mContext,itemsLists); //pass parameters
                        recyclerView3.setAdapter(recyclerAdapterFinal);

                        // recyclerAdapter.setOnItemClickListner(MainActivity.this);

                        recyclerAdapterFinal.setOnPromoClickFinalListner(Final_promo_Activity.this);

                        //recyclerAdapterFinal.setOnPromoClickFinalListner((RecyclerPromoFinalAdapter.OnPromoClickFinalListner) Final_promo_Activity.this);

                        recyclerAdapterFinal.notifyDataSetChanged();



                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        private void clearAll() {
        if(itemsLists != null)
        {
            itemsLists.clear();
            if(recyclerAdapterFinal != null)
            {
                recyclerAdapterFinal.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onPromoClick(int position) {
        Intent detailIntent = new Intent(this,beforeCart_showdetailsOfFood.class);
        promotion clickedItem = itemsLists.get(position);

        detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getUrl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getName());
        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        detailIntent.putExtra(EXTRA_DES,clickedItem.getDes());


        startActivity(detailIntent);
    }
}
