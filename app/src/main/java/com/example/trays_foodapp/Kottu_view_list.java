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

public class Kottu_view_list extends AppCompatActivity {

    ListView listView;
    String mkottu_name[] = {"Egg kottu","Cheese kottu","Mix kottu","Ice kottu","Noodles kottu","Rice kottu","Pork kottu"};
    String mkottu_price[] = {"180 LKR","500 LKR","769 LKR","800 LKR","250 LKR","350 LKR","390 LKR"};
    int mkottu_image[] = {R.drawable.kottu1,R.drawable.kottu2,R.drawable.kottu3,R.drawable.kottu4,R.drawable.kottu5,R.drawable.kottu6,R.drawable.kottu7};
    String mkottu_description[] = {"sri lankan special kottu","indroducing new flavoured kottu","Rice+chesse Mix kottu","Noodles special kottu with spicy","Amazing Pizza..","Rice with chicken kottu","Fresh Pork - our latest kottu "};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kottu_view_list);

        listView = findViewById(R.id.kottulistViewID);
        Adapter_kottu adapter_kottu = new Adapter_kottu(this,mkottu_image,mkottu_name,mkottu_price,mkottu_description);
        listView.setAdapter(adapter_kottu);

    }
}

class Adapter_kottu extends ArrayAdapter<String>
{

    Context context;
    String rkottu_name[];
    String rkottu_price[];
    String rkottu_description[];
    int rkottu_image[];

    Adapter_kottu(Context c,int kottu_image[],String kottu_name[],String kottu_price[],String kottu_description[])
    {
        super(c,R.layout.kottu_list_row,R.id.kottu_nameID,kottu_name);
        this.context = c;
        this.rkottu_image = kottu_image;
        this.rkottu_name = kottu_name;
        this.rkottu_price = kottu_price;
        this.rkottu_description = kottu_description;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.kottu_list_row,parent,false);
        ImageView mykottu_image = row.findViewById(R.id.kottu_imageID);
        TextView mykottu_name = row.findViewById(R.id.kottu_nameID);
        TextView mykottu_price = row.findViewById(R.id.kottu_priceID);
        TextView mykottu_description = row.findViewById(R.id.kottu_descriptiionID);

        mykottu_image.setImageResource(rkottu_image[position]);
        mykottu_name.setText(rkottu_name[position]);
        mykottu_price.setText(rkottu_price[position]);
        mykottu_description.setText(rkottu_description[position]);

        return row;
    }
}