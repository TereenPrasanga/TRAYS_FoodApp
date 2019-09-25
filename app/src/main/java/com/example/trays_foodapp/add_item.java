package com.example.trays_foodapp;



import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable; // error
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class add_item extends AppCompatActivity {

    String  Pname;
    String Ddes;
    int Price;

    String saveCurrentDate,saveCurrentTime;

    Button addProduct;
    ImageView i;
    EditText name,price,des;



    private Uri ImageUri;

    private StorageReference ProductImageRef;

    private DatabaseReference productRef;

    private String downloadImageuRL,productRandomkEY;

    private static  final int GalleryPick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addProduct = (Button)findViewById(R.id.b1);
        i = (ImageView)findViewById(R.id.imageView);
        name = (EditText) findViewById(R.id.t1);
        price = (EditText) findViewById(R.id.t2);
        des = (EditText)findViewById(R.id.t3);

        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");

        productRef = FirebaseDatabase.getInstance().getReference().child("Products");

        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openGallary();
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();


            }
        });





    }



    private void openGallary() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode == RESULT_OK && data!= null)
        {
            ImageUri = data.getData();
            i.setImageURI(ImageUri);


        }
    }


    public void ValidateProductData()
    {


        Pname = name.getText().toString();

        Price = Integer.parseInt(price.getText().toString());

        Ddes = des.getText().toString();

        if(ImageUri == null)
        {
            Toast.makeText(this, "Product image is mandatory", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(Pname))
        {
            Toast.makeText(this, "Name is mandatory", Toast.LENGTH_SHORT).show();
        }
        else {
            store();
        }


    }

    private void store()
    {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        saveCurrentDate = currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");

        saveCurrentTime = currentTime.format(calendar.getTime());


        productRandomkEY = saveCurrentDate + saveCurrentTime;


        final StorageReference filepath = ProductImageRef.child(ImageUri.getLastPathSegment()+productRandomkEY+".jpg");

        final UploadTask uploadTask = filepath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String message = e.toString();

                Toast.makeText(add_item.this, "error"+message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(add_item.this, "Image uploaded Successfully", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageuRL = filepath.getDownloadUrl().toString();

                        return filepath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {

                        if(task.isSuccessful())
                        {
                            downloadImageuRL = task.getResult().toString();

                            Toast.makeText(add_item.this, "GOT THE product image URL SUCESSFULLY", Toast.LENGTH_SHORT).show();

                            saveProductInfoDatabase();


                        }
                    }
                });
            }
        });





    }

    private void saveProductInfoDatabase()
    {
        HashMap<String,Object> productMap = new HashMap<>();

        productMap.put("pid",productRandomkEY);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("price",Price);
        productMap.put("name",Pname);
        productMap.put("image",downloadImageuRL);
        productMap.put("des",Ddes);

        productRef.child(productRandomkEY).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(add_item.this, "Product is added successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    String message = task.getException().toString();
                    Toast.makeText(add_item.this, "ERROR"+message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
