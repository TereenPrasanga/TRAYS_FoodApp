package com.example.trays_foodapp;

import android.support.annotation.NonNull;
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

public class user_update extends AppCompatActivity {
    EditText name,address,phone,email,password;
    Button update,delete,search;
    DatabaseReference dbRef;
    user_registrationClass user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        user = new user_registrationClass();

        name = (EditText)findViewById(R.id.updateShopName);
        address = (EditText)findViewById(R.id.updateShopEmail);
        phone = (EditText)findViewById(R.id.updateShopPhone);
        password = (EditText)findViewById(R.id.updateUserPassword);
        email = (EditText)findViewById(R.id.updateShopAddress);
        update = (Button)findViewById(R.id.btnUpdateUser);
        delete = (Button)findViewById(R.id.btndeleteUser);
        search = (Button)findViewById(R.id.btnsearchUser);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("userRegistration").child(phone.getText().toString());
                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            if(dataSnapshot.hasChildren()){
                                name.setText((dataSnapshot.child("name").getValue().toString().trim()));
                                address.setText((dataSnapshot.child("address").getValue().toString()));
                                email.setText((dataSnapshot.child("email").getValue().toString()));
                                password.setText((dataSnapshot.child("password").getValue().toString()));
                                phone.setText((dataSnapshot.child("phone").getValue().toString()));


                            }else{
                                Toast.makeText(user_update.this,"No show one",Toast.LENGTH_SHORT).show();
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
                dbRef = FirebaseDatabase.getInstance().getReference().child("userRegistration").child(phone.getText().toString());
                dbRef.removeValue();
                clear();

                Toast.makeText(user_update.this,"Delete success",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //register_2.setNIC(NIC.getText().toString().trim());
                    user.setName(name.getText().toString().trim());
                    user.setAddress(address.getText().toString().trim());
                    user.setPhone(phone.getText().toString().trim());
                    user.setPassword(password.getText().toString().trim());
                    user.setEmail(email.getText().toString().trim());

                    dbRef = FirebaseDatabase.getInstance().getReference().child("userRegistration").child(phone.getText().toString());
                    dbRef.setValue(user);
                    clear();

                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();

                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void clear() {
        name.setText("");
        address.setText("");
        email.setText("");
        phone.setText("");
        password.setText("");

    }

}
