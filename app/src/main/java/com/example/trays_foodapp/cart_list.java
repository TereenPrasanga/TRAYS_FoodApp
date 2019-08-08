package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class cart_list extends AppCompatActivity {

    ListView listView;
    String mfood_name[] = {"Cheese Pizza","Chicken kottu"};
    String mfood_price[] = {"LKR 1900.00","LKR 450.00"};
    int mfood_image[] = {R.drawable.pizza4,R.drawable.kottu7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);


        listView = findViewById(R.id.cartlist);
        Adapter_cart adapter_cart = new Adapter_cart(this,mfood_name,mfood_price,mfood_image);
        listView.setAdapter(adapter_cart);




    }
}

class Adapter_cart extends ArrayAdapter<String>
{
    Context context;
    String rfood_name[];
    String rfood_price[];
    int rfood_image[];

    Adapter_cart(Context c,String food_name[],String food_price[],int food_image[])
    {
        super(c,R.layout.cart_list_row,R.id.food_nameID,food_name);
        this.context = c;
        this.rfood_name = food_name;
        this.rfood_price = food_price;
        this.rfood_image = food_image;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.cart_list_row,parent,false);
        TextView myfood_name = row.findViewById(R.id.food_nameID);
        TextView myfood_price = row.findViewById(R.id.food_priceID);
        ImageView myfood_image = row.findViewById(R.id.cartfood_imageID);

        myfood_name.setText(rfood_name[position]);
        myfood_price.setText(rfood_price[position]);
        myfood_image.setImageResource(rfood_image[position]);
        return row;
    }
}
