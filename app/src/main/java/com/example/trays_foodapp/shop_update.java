package com.example.trays_foodapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class shop_update extends AppCompatActivity {
    EditText name,address,phone,email,owner;
    Button update,search,delete;
    DatabaseReference dbRef;
    ShopClass shop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_update);
        shop = new ShopClass();
        name = (EditText)findViewById(R.id.updateShopName);
        email = (EditText)findViewById(R.id.updateShopEmail);
        address = (EditText)findViewById(R.id.updateShopAddress);
        owner = (EditText)findViewById(R.id.updateShopOwner);
        phone = (EditText)findViewById(R.id.updateShopPhone);
        update = (Button)findViewById(R.id.btnUpdateShop);
        search = (Button)findViewById(R.id.btnSearchShop);
        delete = (Button)findViewById(R.id.btndeleteShop);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Shops").child(phone.getText().toString());
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            if(dataSnapshot.hasChildren()){
                                name.setText((dataSnapshot.child("name").getValue().toString().trim()));
                                address.setText((dataSnapshot.child("location").getValue().toString().trim()));
                                email.setText((dataSnapshot.child("email").getValue().toString().trim()));
                                phone.setText((dataSnapshot.child("telephone").getValue().toString().trim()));
                                owner.setText((dataSnapshot.child("owner").getValue().toString().trim()));



                            }else{
                                Toast.makeText(shop_update.this,"No Valid Shop",Toast.LENGTH_SHORT).show();
                            }


                        }catch (Exception e)
                        {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Shops").child(phone.getText().toString());
                openDialog();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //register_2.setNIC(NIC.getText().toString().trim());
                    shop.setName(name.getText().toString().trim());
                    shop.setLocation(address.getText().toString().trim());
                    shop.setTelephone(phone.getText().toString().trim());
                    shop.setEmail(email.getText().toString().trim());
                    shop.setOwner(owner.getText().toString().trim());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("Shops").child(phone.getText().toString());
                    dbRef.setValue(shop);
                    clear();

                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void openDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Conformation Box").setMessage("Do you want to delete ?")
                .setPositiveButton("Ok",null).setNegativeButton("Cancel",null).show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),shop_update.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbRef.removeValue();
                        clear();
                        dialog.dismiss();

                        Toast.makeText(shop_update.this,"Delete success",Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void clear() {
        name.setText("");
        address.setText("");
        email.setText("");
        phone.setText("");
        owner.setText("");


    }


}
