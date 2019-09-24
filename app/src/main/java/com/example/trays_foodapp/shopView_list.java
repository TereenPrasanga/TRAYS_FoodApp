package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class shopView_list extends AppCompatActivity implements SearchAdapterShop.OnItemClick{


    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    ArrayList<ShopClass> IList;
    SearchAdapterShop searchAdapterShop;
    private Context mContext = shopView_list.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_view_list);
        search_edit_text = findViewById(R.id.search_edit_txt_shop);
        recyclerView = findViewById(R.id.searchShop);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        IList = new ArrayList<>();
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty())
                {
                    setAdapter(s.toString());
                }
                else
                {
                    IList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
    }


    @Override
    public void onproductClick(int position) {

    }
    private void setAdapter(final String searchString)
    {
        databaseReference.child("ShopRegistration").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recyclerView.removeAllViews();
                IList.clear();
                int counter = 0;

                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String uid = snapshot.getKey();
                    String name = snapshot.child("name").getValue().toString();
                    String location = snapshot.child("location").getValue().toString();
                    String telephone = snapshot.child("telephone").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();

                    if (name.contains(searchString )){
                        ShopClass shopClass = new ShopClass();
                        shopClass.setName(name);
                        shopClass.setLocation(location);
                        shopClass.setImage(image);
                        shopClass.setTelephone(telephone);
                        IList.add(shopClass);
                    }
                    if (counter == 15)
                    {
                        break;
                    }
                }
                searchAdapterShop = new SearchAdapterShop(shopView_list.this,IList);
                recyclerView.setAdapter(searchAdapterShop);
                searchAdapterShop.setOnItemClick(shopView_list.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
