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

public class FinalDrinksActivity extends AppCompatActivity implements RecyclerFoodsFinalAdapter.OnItemClickFinalListner{

    private RecyclerView recyclerView3;
    private DatabaseReference reference;
    private DatabaseReference mDatabaseRef;

    private Context mContext = FinalDrinksActivity.this;

    private ArrayList<Uploads> itemsList;

    private RecyclerFoodsFinalAdapter recyclerAdapterFinal;

    private ValueEventListener mDBListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_drinks);


        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerViewFood);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView3.setLayoutManager(layoutManager);
        recyclerView3.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Products");

        //  itemsList = new ArrayList<>();

        itemsList = new ArrayList<>();


        init();
    }

    private void init() {


        clearAll();

        Query query = reference.child("Products");

        query.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemsList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    Uploads uploads = new Uploads();

                    String name = snapshot.child("name").getValue().toString();
                    if(name.contains("Drinks"))
                    {
                        uploads.setUrl(snapshot.child("image").getValue().toString());

                        uploads.setName(snapshot.child("name").getValue().toString());

                        uploads.setPrice(snapshot.child("price").getValue().toString());

                        uploads.setDes(snapshot.child("des").getValue().toString());

                        itemsList.add(uploads);

                    }

                    recyclerAdapterFinal = new RecyclerFoodsFinalAdapter(mContext,itemsList); //pass parameters
                    recyclerView3.setAdapter(recyclerAdapterFinal);

                    // recyclerAdapter.setOnItemClickListner(MainActivity.this);

                    recyclerAdapterFinal.setOnItemClickFinalListner(FinalDrinksActivity.this); // add finally

                    recyclerAdapterFinal.notifyDataSetChanged();



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void clearAll() {
        if(itemsList != null)
        {
            itemsList.clear();
            if(recyclerAdapterFinal != null)
            {
                recyclerAdapterFinal.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent detailIntent = new Intent(this,beforeCart_showdetailsOfFood.class);
        Uploads clickedItem = itemsList.get(position);

        detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getUrl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getName());
        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        detailIntent.putExtra(EXTRA_DES,clickedItem.getDes());


        startActivity(detailIntent);

    }
}
