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

public class rice_view_list extends AppCompatActivity {

    ListView listView;

    String mrice_name[] = {"Mixed Rice","Vege Rice","Sinhala Rice","Italian Rice","Noodless Rice","Chicken Rice","Butter Rice"};
    String mrice_price[] ={"150.00 LKR","200 LKR","340 LKR","567 LKR","456.89 LKR","456 LKR","567 LKR"};
    int mrice_image[] ={R.drawable.rice1,R.drawable.rice2,R.drawable.rice3,R.drawable.rice4,R.drawable.rice5,R.drawable.rice6,R.drawable.rice7};
    String mrice_description[]= {"sri lankan special Rice","take away foods available","healthy Rice offer","sri lankan special kottu","come and enjoy","latest offers","sri lankan special kottu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_view_list);

        listView = findViewById(R.id.ricelistViewID);
        Adapter_rice adapter_rice = new Adapter_rice(this,mrice_image,mrice_name,mrice_price,mrice_description);
        listView.setAdapter(adapter_rice);
    }
}

class Adapter_rice extends ArrayAdapter<String>
{
    Context context;
    String rrice_name[];
    String rrice_price[];
    String rrice_description[];
    int rrice_image[];

    Adapter_rice(Context c,int rice_image[],String rice_name[],String rice_price[],String rice_description[])
    {
        super(c,R.layout.rice_list_row,R.id.rice_nameID,rice_name);
        this.context = c;
        this.rrice_image = rice_image;
        this.rrice_name = rice_name;
        this.rrice_price = rice_price;
        this.rrice_description = rice_description;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.rice_list_row,parent,false);

        ImageView myrice_image = row.findViewById(R.id.rice_imageID);
        TextView myrice_name = row.findViewById(R.id.rice_nameID);
        TextView myrice_price = row.findViewById(R.id.rice_priceID);
        TextView myrice_description = row.findViewById(R.id.rice_descriptiionID);

        myrice_image.setImageResource(rrice_image[position]);
        myrice_name.setText(rrice_name[position]);
        myrice_price.setText(rrice_price[position]);
        myrice_description.setText(rrice_description[position]);

        Button order = row.findViewById(R.id.riceorder);
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