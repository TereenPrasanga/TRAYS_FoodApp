package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class manage_promotion_list extends AppCompatActivity {

    ListView listView;
    String mfood_name[] = {"Burger Combo","Crispy Bites","Shake It","Nazi Battle","Italiano","Happy Mom","Fusion"};
    String mfood_price[] = {"Rs.500","Rs.699","Rs.299","Rs.1200","Rs.799","Rs.899","Rs.999"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_promotion_list);

        listView = findViewById(R.id.manage_promoID);
        Adapter_managePromo adapter_managePromo = new Adapter_managePromo(this,mfood_name,mfood_price);
        listView.setAdapter(adapter_managePromo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if (position == 0){
                    Toast.makeText(manage_promotion_list.this,"hii",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
class Adapter_managePromo extends ArrayAdapter<String >
{
    Context context;
    String rfood_name[];
    String rfood_price[];

    Adapter_managePromo(Context c,String food_name[],String food_price[])
    {
       super(c,R.layout.manage_promo_row,R.id.promo_food_priceID,food_price);
       this.context = c;
       this.rfood_name = food_name;
       this.rfood_price = food_price;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.manage_promo_row,parent,false);

        TextView myfood_name = row.findViewById(R.id.promo_food_nameID);
        TextView myfood_price = row.findViewById(R.id.promo_food_priceID);

        myfood_name.setText(rfood_name[position]);
        myfood_price.setText(rfood_price[position]);

        return row;
    }
}
