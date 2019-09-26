package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_DES;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_IMAGE;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_NAME;
import static com.example.trays_foodapp.SearchBarFoodActivity.EXTRA_PRICE;

public class beforeCart_showdetailsOfFood extends AppCompatActivity {

    private TextView counterText;
    private TextView price;
    private TextView result;
    private Button minusBtn;
    private Button plusBtn;
    private int counter;
    private int total;
    private Button orderNow;
    private TextView des;

    public static final String EXTRA_COUNTER = "counter";
    public static final String EXTRA_name = "name";
    public static final String EXTRA_result = "result";
    public static final String EXTRA_image = "image";

    String pNames;
    int pPrices;
    TextView pn,pr;
    String saveCurrentDate,saveCurrentTime;
    private String downloadImageuRL,productRandomkEY;
    private StorageReference ProductImageRef;
    private DatabaseReference productRef;

    private Context mContext = beforeCart_showdetailsOfFood.this;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.decrement:
                    minusCounter();
                    caltot();
                    break;
                case R.id.increment:
                    plusCounter();
                    caltot();



            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_cart_showdetails_of_food);




        final Intent intent = getIntent();
        final String imageUrl = intent.getStringExtra(EXTRA_IMAGE);
        final String creatorName = intent.getStringExtra(EXTRA_NAME);
        String PRICE = intent.getStringExtra(EXTRA_PRICE);
        String desc = intent.getStringExtra(EXTRA_DES);

        ImageView imageView = findViewById(R.id.image_view_detail1);
        TextView textViewCreator = findViewById(R.id.text_view_creator_detail1);

        pn = (TextView)findViewById(R.id.text_view_creator_detail1);
        pr  = (TextView)findViewById(R.id.result);


        counterText = (TextView)findViewById(R.id.counter);
        price = (TextView)findViewById(R.id.price);
        minusBtn = (Button) findViewById(R.id.decrement);
        minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) findViewById(R.id.increment);
        plusBtn.setOnClickListener(clickListener);
        result = (TextView)findViewById(R.id.result);
        des = (TextView)findViewById(R.id.des);

        orderNow = (Button)findViewById(R.id.orderNow);


        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
        textViewCreator.setText(creatorName);
        price.setText(PRICE);
        des.setText(desc);

        initCounter();





        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // store();


                String counter = counterText.getText().toString();
                String name = pn.getText().toString();
                String results = result.getText().toString();

                Intent intent1 = new Intent(beforeCart_showdetailsOfFood.this,PlaceAOrderFinalActivity.class);

                intent1.putExtra(EXTRA_COUNTER,counter);
                intent1.putExtra(EXTRA_name,name);
                intent1.putExtra(EXTRA_result,results);
                intent1.putExtra(EXTRA_image,imageUrl);

                startActivity(intent1);

               // Toast.makeText(beforeCart_showdetailsOfFood.this, "REDIRECT SUCCESSFULL"+counter+name+results, Toast.LENGTH_SHORT).show();








            }
        });


    }


    private void initCounter() {
        counter = 1;
        counterText.setText(counter+"");
        int val = Integer.parseInt(price.getText().toString());
        result.setText(val+"");
    }

    private void plusCounter(){

        if(counter<20)
        {
            counter++;
            counterText.setText(counter+"");
        }

    }

    private void minusCounter()
    {
        if(counter>1)
        {
            counter--;
            counterText.setText(counter+"");

        }

    }

    private void caltot()
    {

        int value = Integer.parseInt(price.getText().toString());
        int quantity = counter;
        total = value * quantity;
        result.setText(total+"");

    }






   /* public void addtocart(View view) {
        Intent intent = new Intent(this,cart_list.class);
        startActivity(intent);
    }*/
}
