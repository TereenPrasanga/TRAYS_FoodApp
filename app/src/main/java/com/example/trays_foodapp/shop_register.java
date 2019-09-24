package com.example.trays_foodapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class shop_register extends AppCompatActivity {
    String downloadImageURi,productRandomKey;
    String pname,plocation,pemail,ptelephone;
    String saveCurrentDate,saveCurrentTime;
    EditText txtname,txtlocation,txtemail,txtphone;
    ImageView imageView;
    Button btnsignupShop;
    private static  final int GaLLeryPick = 1;
    private Uri ImageUri;

    private StorageReference productImageRef;
    private DatabaseReference productRef;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_register);

        txtname = findViewById(R.id.txtshopname);
        txtlocation = findViewById(R.id.txtshoplocation);
        imageView = (ImageView) findViewById(R.id.imageShop);
        txtemail = findViewById(R.id.txtshopemail);
        txtphone = findViewById(R.id.txtshopphone);
        btnsignupShop = findViewById(R.id.btnsignUpShop);

        productImageRef = FirebaseStorage.getInstance().getReference().child("ShopRegistration");
        productRef = FirebaseDatabase.getInstance().getReference().child("ShopRegistration");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallary();
            }
        });
        btnsignupShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
                Intent intent = new Intent(getApplicationContext(),admin.class);
                startActivity(intent);
            }
        });


    }

    private void ValidateProductData() {

        pname = txtname.getText().toString();
        plocation = txtlocation.getText().toString();
        pemail = txtemail.getText().toString();
        ptelephone = txtphone.getText().toString();

        if(ImageUri.equals(null)) //c
        {
            Toast.makeText(this, "Image is Mandatory", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pname)) {
            Toast.makeText(this, "Shop Name Required!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(plocation))
        {
            Toast.makeText(this, "Location Required!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pemail))
        {
            Toast.makeText(this, "email Required!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ptelephone))
        {
            Toast.makeText(this, "Telephone Number Required!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Store();
        }


    }

    private void Store() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate  = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filepath = productImageRef.child(ImageUri.getLastPathSegment()+productRandomKey+".jpg");
        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(shop_register.this, "error"+message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(shop_register.this, "Image Upload Successfully", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw  task.getException();
                        }
                        downloadImageURi = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageURi = task.getResult().toString();
                            Toast.makeText(shop_register.this, "Got the image URI successfully", Toast.LENGTH_SHORT).show();

                            saveProductInfoDatabase();
                        }
                    }
                });

            }


        });




    }

    private void saveProductInfoDatabase() {
        HashMap<String ,Object> productMap = new HashMap<>();
        productMap.put("pid",productRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("name",pname);
        productMap.put("location",plocation);
        productMap.put("email",pemail);
        productMap.put("telephone",ptelephone);
        productMap.put("image",downloadImageURi);

        productRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(shop_register.this, "Added Succesfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                  String msg = task.getException().toString();
                    Toast.makeText(shop_register.this, "ERROR"+msg, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    private void openGallary () {
            Intent galleryIntent = new Intent();
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/*");
            startActivityForResult(galleryIntent, GaLLeryPick);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GaLLeryPick && resultCode == RESULT_OK && data != null)
        {
            ImageUri = data.getData();
            imageView.setImageURI(ImageUri);
        }
    }
}
