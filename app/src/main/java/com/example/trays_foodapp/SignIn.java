package com.example.trays_foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignIn extends AppCompatActivity {

   ProgressDialog loadingbar;
    Button btnsignup;
    EditText txtnumber,txtpassword;
    String parentDBName = "userRegistration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtnumber = (EditText)findViewById(R.id.updateShopName);
        txtpassword = (EditText)findViewById(R.id.loginpassword);
        btnsignup = (Button) findViewById(R.id.btnsignUpUser);
        loadingbar = new ProgressDialog(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = txtnumber.getText().toString();
                String password = txtpassword.getText().toString();
                if (phone.equals("000") && password.equals("admin"))
                {
                    Toast.makeText(SignIn.this, "Admin Mode", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this,admin.class);
                    startActivity(intent);
                }
                else
                {
                    loginUser();

                }

            }
        });

    }

    private void loginUser() {
        String phone = txtnumber.getText().toString();
        String password = txtpassword.getText().toString();
        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingbar.setTitle("login");
            loadingbar.setMessage("Please Wait");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            AllowAccesstoAccount(phone,password);
        }
    }

    private void AllowAccesstoAccount(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDBName).child(phone).exists())
                {
                    user_registrationClass usersData = dataSnapshot.child(parentDBName).child(phone).getValue(user_registrationClass.class);
                    if(usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            Toast.makeText(SignIn.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            Intent intent = new Intent(SignIn.this,home.class);
                            startActivity(intent);
                        }
                    }
                }
                else
                {
                    Toast.makeText(SignIn.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
