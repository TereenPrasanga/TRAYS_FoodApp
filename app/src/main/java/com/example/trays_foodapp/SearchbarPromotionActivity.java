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

import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_DES;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_IMAGE;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_NAME;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_PRICE;

public class SearchbarPromotionActivity extends AppCompatActivity implements SearchPromoAdapter.onPromoclick{

    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;

    ArrayList<promotion> IList;

    SearchPromoAdapter searchAdapter;

    private Context mContext = SearchbarPromotionActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar_promotion);

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);

        recyclerView = findViewById(R.id.promoRecycleView);

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
                if (!editable.toString().isEmpty()) {
                   setAdapter (editable.toString());

                } else {
                    IList.clear();
                   // recyclerView.removeAllViews();
                  //  SearchPromoAdapter.notifyDataSetChanged(); // addnew
                }
            }
        });
    }
        private void setAdapter(final String searchedString) {



            databaseReference.child("AddPromotion").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    recyclerView.removeAllViews();
                    IList.clear();
                    int counter = 0;

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){




                        String uid = snapshot.getKey();
                        String name = snapshot.child("name").getValue().toString();
                        String price = snapshot.child("price").getValue().toString();
                        String image = snapshot.child("image").getValue().toString();
                        String des = snapshot.child("description").getValue().toString();
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
                            promotion promo = new promotion();
                            promo.setName(name);
                            promo.setPrice(price);
                            promo.setUrl(image);
                            promo.setDes(des);
                        /*namesList.add(name);
                        pricesList.add(price);
                        imagesList.add(image);
                        counter++;*/
                            IList.add(promo);

                        }


                        if (counter == 15)
                            break;


                    }

                    searchAdapter = new SearchPromoAdapter(SearchbarPromotionActivity.this,IList);
                    recyclerView.setAdapter(searchAdapter);
                    searchAdapter.setonPromoclick(SearchbarPromotionActivity.this);
                    //   searchAdapter.notifyDataSetChanged(); //add new

                }



                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



    }

    @Override
    public void onPromoClick(int position) {
        Intent detail = new Intent(SearchbarPromotionActivity.this,beforeCart_showdetailsOfFood.class);

        promotion clickedItem = IList.get(position);

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
