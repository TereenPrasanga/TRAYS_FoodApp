package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import static com.example.trays_foodapp.beforeCart_showdetailsOfFood.EXTRA_COUNTER;
import static com.example.trays_foodapp.beforeCart_showdetailsOfFood.EXTRA_image;
import static com.example.trays_foodapp.beforeCart_showdetailsOfFood.EXTRA_name;
import static com.example.trays_foodapp.beforeCart_showdetailsOfFood.EXTRA_result;

public class PlaceAOrderFinalActivity extends AppCompatActivity {


    int allprice;
    String con,uname,pname;

    private TextView itemname;
    private TextView total;
    private TextView quantity;
    private ImageView imageView;

    private EditText unames;
    private EditText contact;

    private Button placeAorder,cancelAorder;




    String saveCurrentDate,saveCurrentTime;
    private String downloadImageuRL,productRandomkEY;
    private StorageReference ProductImageRef;
    private DatabaseReference productRef;

    private Context mContext = PlaceAOrderFinalActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_aorder_final);

        productRef = FirebaseDatabase.getInstance().getReference().child("OrderProducts");



        imageView = (ImageView)findViewById(R.id.imageView33);
        itemname = (TextView)findViewById(R.id.itemenamee);
        total = (TextView)findViewById(R.id.totals);
        quantity = (TextView)findViewById(R.id.quantityy);

        unames = (EditText) findViewById(R.id.unames);
        contact = (EditText)findViewById(R.id.contact);
        placeAorder = (Button) findViewById(R.id.button22);
        cancelAorder = (Button) findViewById(R.id.cancel);




        Intent intent = getIntent();
        String COUNTER = intent.getStringExtra(EXTRA_COUNTER);
        String name = intent.getStringExtra(EXTRA_name);
        String result = intent.getStringExtra(EXTRA_result);
        String image = intent.getStringExtra(EXTRA_image);

        Picasso.get().load(image).fit().centerCrop().into(imageView);
        itemname.setText(name);
        total.setText(result);
        quantity.setText(COUNTER);




        placeAorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // store();

                ValidateData();
            }
        });

        cancelAorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(PlaceAOrderFinalActivity.this,SearchBarFoodActivity.class);

                startActivity(intent1);
            }
        });





    }

    private void ValidateData() {

        int c = 0;



        uname = unames.getText().toString();
        con = contact.getText().toString();

        for(int i=0;i<con.length();i++)
        {
            c++;
        }

        if(TextUtils.isEmpty(uname))
        {
            Toast.makeText(this, "Please Enter the Name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(con))
        {
            Toast.makeText(this, "Please Enter the contact no", Toast.LENGTH_SHORT).show();

        }

        else if(c>10 || c<10)
        {
            Toast.makeText(this, "Enter valid contact no", Toast.LENGTH_SHORT).show();
        }


        else
        {
            store();
        }






    }

    private void store() {



        pname = itemname.getText().toString();
        allprice = Integer.parseInt(total.getText().toString());

        uname = unames.getText().toString();
        con = contact.getText().toString();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");

        saveCurrentDate = currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");

        saveCurrentTime = currentTime.format(calendar.getTime());


        productRandomkEY = saveCurrentDate + saveCurrentTime;



        HashMap<String,Object> OrdersMap = new HashMap<>();

        OrdersMap.put("pid",productRandomkEY);
        OrdersMap.put("date",saveCurrentDate);
        OrdersMap.put("time",saveCurrentTime);

        OrdersMap.put("pname",pname);
        OrdersMap.put("price",allprice);
        OrdersMap.put("contactno",con);



        productRef.child(productRandomkEY).updateChildren(OrdersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(PlaceAOrderFinalActivity.this, "Product is added successfully", Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(PlaceAOrderFinalActivity.this,SearchBarFoodActivity.class);

                    startActivity(intent1);
                }
                else{

                    String message = task.getException().toString();
                    Toast.makeText(PlaceAOrderFinalActivity.this, "ERROR"+message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
