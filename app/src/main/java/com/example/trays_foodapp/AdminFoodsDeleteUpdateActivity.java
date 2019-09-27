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

public class AdminFoodsDeleteUpdateActivity extends AppCompatActivity implements RecyclerAdapterItemsDelete.OnItemClickListner,
RecyclerAdapterItemsDelete.OnItemClickListnerX{


    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private StorageReference mStorageRef;
    private Context mContext = AdminFoodsDeleteUpdateActivity.this;

    private DatabaseReference mDatabaseRef;

    private FirebaseStorage mStorage;
    private ValueEventListener mDBListner;

    private ArrayList<Images> imageList;

    private RecyclerAdapterItemsDelete recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_foods_delete_update);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        reference = FirebaseDatabase.getInstance().getReference();

        mStorageRef = FirebaseStorage.getInstance().getReference(); // not wanted

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Products"); //$$

        imageList = new ArrayList<>();



        recyclerAdapter = new RecyclerAdapterItemsDelete(mContext,imageList);
        recyclerView.setAdapter(recyclerAdapter);



        recyclerAdapter.setOnItemClickListner(AdminFoodsDeleteUpdateActivity.this);

        recyclerAdapter.setOnItemClickListnerX(AdminFoodsDeleteUpdateActivity.this);




        init();

    }

    private void init() {

        clearAll();

        mStorage = FirebaseStorage.getInstance(); // add

        Query query = reference.child("Products");



        mDBListner = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //NEW

                imageList.clear();
                //NEW
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {


                    Images images = new Images();

                    images.setUrl(snapshot.child("image").getValue().toString());

                    images.setName(snapshot.child("name").getValue().toString());

                    images.setPrice(snapshot.child("price").getValue().toString());


                    images.setkey(snapshot.getKey());



                    imageList.add(images);
                }





                recyclerAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void clearAll() {

        if(imageList != null)
        {
            imageList.clear();



            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClickX(int position) {
       // Toast.makeText(this, "Normal click at position"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteClick(int position) {

        Images selectedItem = imageList.get(position);

        final String selectedKey = selectedItem.getkey();


        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getUrl());


        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();



                Toast.makeText(AdminFoodsDeleteUpdateActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDatabaseRef.removeEventListener(mDBListner);
    }
}
