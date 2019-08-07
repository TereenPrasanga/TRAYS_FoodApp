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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class shopView_list extends AppCompatActivity {
    ListView listView;

    String mshopname[] = {"Shang Palace","Navratna","Lagoon","Nuga Gama","Curry Leaf","Sea Fish Restaurant","Kaema Sutra"};
    int mimage[] = {R.drawable.shop1,R.drawable.shop2,R.drawable.shop3,R.drawable.shop4,R.drawable.shop5,R.drawable.shop6,R.drawable.shop7};
    String mshopadderss[] = {"117 Colombo - Batticaloa Hwy","501/1/A New Kandy Rd, thalangama","174 A2, Galle Road, Mount Lavinia","51, Perahera Mawatha, Colombo 03","No. 170/A, Old Kesbewa Road, Delkanda","Wonder Hotel, Colombo 213 Galle Rd, Colombo 03","Manikpuragama, Situlpawwa road, Kataragama"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_view_list);


        listView = findViewById(R.id.Shopview);
        Myadapter adapter =new  Myadapter(this,mshopname,mshopadderss,mimage);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Toast.makeText(shopView_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shopView_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shopView_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shopView_list.this,"wf",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }




    class Myadapter extends ArrayAdapter<String>
    {
        Context context;
        String rshopname[];
        String rshopaddress[];
        int rimage[];

        Myadapter(Context c , String shopname[], String shopaddress[], int image[])
        {
            super(c,R.layout.shopview_list_row,R.id.shopnameID,shopname);
            this.context = c;
            this.rshopname = shopname;
            this.rshopaddress = shopaddress;
            this.rimage = image;
        }

        @NonNull
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.shopview_list_row,parent,false);
            TextView myshopname = row.findViewById(R.id.shopnameID);
            TextView myshopaddress = row.findViewById(R.id.shopaddressID);
            ImageView myimage = row.findViewById(R.id.imageID);

            myshopname.setText(rshopname[position]);
            myshopaddress.setText(rshopaddress[position]);
            myimage.setImageResource(rimage[position]);

            return row;
        }
    }

}
