package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchBarFoodActivity extends AppCompatActivity implements SearchFoodsAdapter.OnItemClick {

    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    ArrayList<Uploads> IList;

    SearchFoodsAdapter searchAdapter;

    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_DES = "des";

    private Context mContext = SearchBarFoodActivity.this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar_food);


        search_edit_text = findViewById(R.id.search_edit_text);

        recyclerView = findViewById(R.id.recyclerView4);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        IList = new ArrayList<>();




        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
                    setAdapter(editable.toString());

                }else {
                    IList.clear();
                    recyclerView.removeAllViews();
                    // searchAdapter.notifyDataSetChanged(); // addnew
                }
            }
        });



    }

    private void setAdapter(final String searchedString) {



        databaseReference.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                recyclerView.removeAllViews();
                IList.clear();
                int counter = 0;

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    //  Images images = new Images();
                    //                    images.setUrl(snapshot.child("image").getValue().toString());
                    //
                    //                    images.setName(snapshot.child("name").getValue().toString());

                  /*  Uploads uploads = new Uploads();
                    uploads.setUrl(snapshot.child("image").getValue().toString());
                    uploads.setName(snapshot.child("name").getValue().toString());
                    uploads.setPrice(snapshot.child("price").getValue().toString());*/
                    // imageList.add(images);

                    //  IList.add(uploads);


                    String uid = snapshot.getKey();
                    String name = snapshot.child("name").getValue().toString();
                    String price = snapshot.child("price").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();
                    String des = snapshot.child("des").getValue().toString();
/*
                    if(searchedString.equals(null))
                    {
                        Uploads uploads = new Uploads();
                        uploads.setName(name);
                        uploads.setPrice(price);
                        uploads.setUrl(image);
                        uploads.setDes(des);
                        *//*namesList.add(name);
                        pricesList.add(price);
                        imagesList.add(image);
                        counter++;*//*
                      //  IList.add(uploads);
                    }*/

                    if(name.contains(searchedString)){
                        Uploads uploads = new Uploads();
                        uploads.setName(name);
                        uploads.setPrice(price);
                        uploads.setUrl(image);
                        uploads.setDes(des);
                        /*namesList.add(name);
                        pricesList.add(price);
                        imagesList.add(image);
                        counter++;*/
                        IList.add(uploads);

                    }



                    if (counter == 15)
                        break;


                }

                searchAdapter = new SearchFoodsAdapter(SearchBarFoodActivity.this,IList);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.setOnItemClick(SearchBarFoodActivity.this);
                //   searchAdapter.notifyDataSetChanged(); //add new

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }







    @Override
    public void onProductClick(int position) {

        Intent detail = new Intent(SearchBarFoodActivity.this,beforeCart_showdetailsOfFood.class);

        Uploads clickedItem = IList.get(position);

        // detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getUrl());
        //        detailIntent.putExtra(EXTRA_NAME,clickedItem.getName());
        //        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        //        detailIntent.putExtra(EXTRA_DES,clickedItem.getDes());

        detail.putExtra(EXTRA_IMAGE,clickedItem.getUrl());
        detail.putExtra(EXTRA_NAME,clickedItem.getName());
        detail.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        detail.putExtra(EXTRA_DES,clickedItem.getDes());

        startActivity(detail);

    }
}
