package com.example.trays_foodapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class admin_promo_delete_update_Activity extends AppCompatActivity implements RecyclerAdapterPromoDelete.OnPromoClickListnerX{

    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private Context mContext = admin_promo_delete_update_Activity.this;

    private DatabaseReference mDatabaseRef;

    private FirebaseStorage mStorage;
    private ValueEventListener mDBListner;

    private ArrayList<promotion> imagesLists;

    private RecyclerAdapterPromoDelete recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_promo_delete_update_);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView11);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        reference = FirebaseDatabase.getInstance().getReference();

        mStorageRef = FirebaseStorage.getInstance().getReference(); // not wanted

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("AddPromotion"); //$$

        imagesLists = new ArrayList<>();



        recyclerAdapter = new RecyclerAdapterPromoDelete(mContext,imagesLists);
        recyclerView.setAdapter(recyclerAdapter);



        recyclerAdapter.setOnPromoClickListnerX(admin_promo_delete_update_Activity.this);

        //recyclerAdapter.setOnItemClickListnerX(AdminFoodsDeleteUpdateActivity.this);




        init();
    }
    private void init() {

        clearAll();

        mStorage = FirebaseStorage.getInstance(); // add

        Query query = reference.child("AddPromotion");



        mDBListner = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //NEW

                imagesLists.clear();
                //NEW
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {


                    promotion Promotion = new promotion();

                    Promotion.setUrl(snapshot.child("image").getValue().toString());

                    Promotion.setName(snapshot.child("name").getValue().toString());

                    Promotion.setPrice(snapshot.child("price").getValue().toString());


                    Promotion.setKey(snapshot.getKey());



                    imagesLists.add(Promotion);
                }





                recyclerAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void clearAll() {

        if(imagesLists != null)
        {
            imagesLists.clear();



            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClickx(int position) {

    }

    @Override
    public void onWhatEverClickx(int position) {

    }

    @Override
    public void onDeleteClickx(int position) {

        promotion selectedItem = imagesLists.get(position);

        final String selectedKey = selectedItem.getKey();


        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getUrl());


        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();



                Toast.makeText(admin_promo_delete_update_Activity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDatabaseRef.removeEventListener(mDBListner);
    }
}
