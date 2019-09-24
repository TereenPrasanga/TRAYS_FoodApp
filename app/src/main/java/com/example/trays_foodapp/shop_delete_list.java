package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trays_foodapp.AdapterShop.OnItemClicklisnerShop;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class shop_delete_list extends AppCompatActivity implements OnItemClicklisnerShop{
    private RecyclerView recyclerView;
    private DatabaseReference reference;
    private StorageReference mstoStorageReference;
    private Context mContext = shop_delete_list.this;

    private DatabaseReference mDatabaseReference;
    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;
    private ArrayList<ShopClass> shopList;
    private AdapterShop adapterShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_delete_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewShop);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("ShopRegistration");
        shopList = new ArrayList<>();

            adapterShop = new AdapterShop(mContext,shopList);
            recyclerView.setAdapter(adapterShop);

            adapterShop.setOnItemClicklisnerShop(shop_delete_list.this);


        init();

    }

    private void init() {
        clearAll();
        mStorage = FirebaseStorage.getInstance();
        Query query = reference.child("ShopRegistration");

        mDBListener = mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shopList.clear();

                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    ShopClass shop = new ShopClass();
                    shop.setName(snapshot.child("name").getValue().toString());
                    shop.setLocation(snapshot.child("location").getValue().toString());
                    shop.setTelephone(snapshot.child("telephone").getValue().toString());
                    shop.setImage(snapshot.child("image").getValue().toString());
                    shop.setmKey(snapshot.getKey());

                    shopList.add(shop);


                }
                adapterShop.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void clearAll() {
        if (shopList!= null)
        {
            shopList.clear();

            if(adapterShop != null)
            {
                adapterShop.notifyDataSetChanged();
            }
        }

    }


    @Override
    public void onItemCilickShop(int position) {

        Toast.makeText(mContext, "Normal Click at position"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWhatEverShop(int position) {
        Toast.makeText(mContext, "WHATEVER Click at position"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteClickShop(int position) {
        ShopClass selectedItem = shopList.get(position);
        final String selectedKey = selectedItem.getmKey();

        StorageReference  imgeRef = mStorage.getReferenceFromUrl(selectedItem.getImage());

        imgeRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            mDatabaseReference.child(selectedKey).removeValue();
                Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseReference.removeEventListener(mDBListener);
    }
}
