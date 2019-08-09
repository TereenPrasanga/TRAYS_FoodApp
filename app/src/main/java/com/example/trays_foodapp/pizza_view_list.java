package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class pizza_view_list extends AppCompatActivity {

    ListView listView;

    String mpizza_name[] = {"Cheese Pizza","Mixed Pizza","Dhayyya Pizza","Chicken Pizza","Vege Pizza","Indian Pizza","Pork Pizza"};
    String mpizza_price[] = {"1256.99 LKR","2300.98 LKR","1459.99 LKR","3000.90 LKR","1290.99 LKR","1234.90 LKR","1234.98 LKR"};
    int mpizza_image[] = {R.drawable.pizza1,R.drawable.pizza2,R.drawable.pizza3,R.drawable.pizza4,R.drawable.pizza5,R.drawable.pizza6,R.drawable.pizza7};
    String mpizza_description[] = {"SPECIAL DEAL COME AND ENJOY","ENJOY WITH US","TASTY PIZZA","TAKE AWAY EASY","ENJOY WEEKEND","BUY 3 AND 1 FREE","SPECIAL OFFER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_view_list);

        listView = findViewById(R.id.pizzalistViewID);
        Adapter_pizza adapter_pizza = new Adapter_pizza(this,mpizza_image,mpizza_name,mpizza_price,mpizza_description);
        listView.setAdapter(adapter_pizza);
    }
}

class  Adapter_pizza extends ArrayAdapter<String>
{
    Context context;
    String rpizza_name[];
    String rpizza_price[];
    String rpizza_description[];
    int rpizza_image[];

    Adapter_pizza(Context c,int pizza_image[],String pizza_name[],String pizza_price[],String pizza_description[])
    {
        super(c,R.layout.pizza_list_row,R.id.pizza_nameID,pizza_name);
        this.context = c;
        this.rpizza_image = pizza_image;
        this.rpizza_name = pizza_name;
        this.rpizza_price = pizza_price;
        this.rpizza_description = pizza_description;



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.pizza_list_row,parent,false);
        ImageView mypizza_image = row.findViewById(R.id.pizza_imageID);
        TextView mypizza_name = row.findViewById(R.id.pizza_nameID);
        TextView mypizza_price = row.findViewById(R.id.pizza_priceID);
        TextView mypizza_description = row.findViewById(R.id.pizza_descriptiionID);

        mypizza_image.setImageResource(rpizza_image[position]);
        mypizza_name.setText(rpizza_name[position]);
        mypizza_price.setText(rpizza_price[position]);
        mypizza_description.setText(rpizza_description[position]);

        Button order = row.findViewById(R.id.pizzaorder);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),beforeCart_showdetailsOfFood.class);
                context.startActivity(intent);
            }
        });


        return row;
    }
}
