package com.example.trays_foodapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class promotion_list extends AppCompatActivity {

    ListView listView;

    int mimage[] = {R.drawable.promo1,R.drawable.promo2,R.drawable.promo3,R.drawable.promo4,R.drawable.promo5,R.drawable.promo6,R.drawable.promo7};
    String mname[] = {"Burger Combo","Crispy Bites","Shake It","Nazi Battle","Italiano","Happy Mom","Fusion"};
    String mdescription[] = {"Cheapest burger in town","Chicken crispy legs","Avacado Milkshakes","Nazi Goreng","Creamy Pastas","Happy Meal with Coffee","Grilled Beef"};
    String mprice[] = {"Rs.500","Rs.699","Rs.299","Rs.1200","Rs.799","Rs.899","Rs.999"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_list);

        listView = findViewById(R.id.promotion_list1);
        Myadapter adapter = new Myadapter(this,mimage,mname,mdescription,mprice);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if (position == 0){
                    Toast.makeText(promotion_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
class Myadapter extends ArrayAdapter<String>
{
    Context context;
    int rimage[];
    String rname[];
    String rdescription[];
    String rprice[];


    Myadapter(Context c,int image[],String name[],String description[],String price[]){
        super(c,R.layout.promotion_list_row,R.id.promo_name,name);
        this.context = c;
        this.rimage = image;
        this.rname = name;
        this.rdescription = description;
        this.rprice = price;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.promotion_list_row,parent,false);
        ImageView myimage = row.findViewById(R.id.promotion_list);
        TextView myname = row.findViewById(R.id.promo_name);
        TextView mydescription = row.findViewById(R.id.promo_description);
        TextView myprice = row.findViewById(R.id.promo_price);

        myimage.setImageResource(rimage[position]);
        myname.setText(rname[position]);
        mydescription.setText(rdescription[position]);
        myprice.setText(rprice[position]);

        Button order = row.findViewById(R.id.promo_orderbtn);
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

