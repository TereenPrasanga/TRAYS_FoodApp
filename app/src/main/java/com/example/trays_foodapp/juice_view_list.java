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

public class juice_view_list extends AppCompatActivity {

    ListView listView;

    String mjuice_name[] = {"Ice Coffee","Milk Shake","Milo","Coca-Coala","Chocolate Shake","Leomanade","Dark Fudge Shake"};

    String mjuice_price[] = {"200 LKR","230 LKR","300 LKR","149.99 LKR","300.01 LKR","290.99 LKR","123.98 LKR"};

    int mjuice_image[] = {R.drawable.juice5,R.drawable.juice3,R.drawable.juice1,R.drawable.juice2,R.drawable.juice7,R.drawable.juice6,R.drawable.juice4};

    String mjuice_description[] = {"enjoy our new juices","we have great discounts","MILO is sugar free brand","join with us","lowest cost juices","we promote brands","we have our own reciepies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juice_view_list);

        listView = findViewById(R.id.juicelistViewID);
        Adapter_juice adapter_juice = new Adapter_juice(this,mjuice_image,mjuice_name,mjuice_price,mjuice_description);
        listView.setAdapter(adapter_juice);
    }
}

class Adapter_juice extends ArrayAdapter<String>
{

    Context context;
    String rjuice_name[];
    String rjuice_price[];
    String rjuice_description[];
    int rjuice_image[];

    Adapter_juice(Context c,int juice_image[],String juice_name[],String juice_price[],String juice_description[])
    {
        super(c,R.layout.juice_list_row,R.id.food_nameID,juice_name);
        this.context = c;
        this.rjuice_image = juice_image;
        this.rjuice_name = juice_name;
        this.rjuice_price = juice_price;
        this.rjuice_description = juice_description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @ NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.juice_list_row,parent,false);

        ImageView myjuice_image = row.findViewById(R.id.juice_imageID);
        TextView myjuice_name = row.findViewById(R.id.juice_nameID);
        TextView myjuice_price = row.findViewById(R.id.juice_priceID);
        TextView myjuice_description = row.findViewById(R.id.juice_descriptiionID);

        myjuice_image.setImageResource(rjuice_image[position]);
        myjuice_name.setText(rjuice_name[position]);
        myjuice_price.setText(rjuice_price[position]);
        myjuice_description.setText(rjuice_description[position]);
        Button order = row.findViewById(R.id.juiceorder);
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
