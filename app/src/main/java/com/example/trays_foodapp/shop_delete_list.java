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

public class shop_delete_list extends AppCompatActivity {

    ListView listView;
    String mshopname[] = {"Shang Palace","Navratna","Lagoon","Nuga Gama","Curry Leaf","Sea Fish Restaurant","Kaema Sutra"};
    String mshopadderss[] = {"117 Colombo - Batticaloa Hwy","501/1/A New Kandy Rd, thalangama","174 A2, Galle Road, Mount Lavinia","51, Perahera Mawatha, Colombo 03","No. 170/A, Old Kesbewa Road, Delkanda","Wonder Hotel, Colombo 213 Galle Rd, Colombo 03","Manikpuragama, Situlpawwa road, Kataragama"};
    String memail[] = {"Shang@gmai.com","navarthana@gmail.com","Loonga_gmail.com","Nuga@yahoo.com","Curry_leaf@gmail.com","SeaFish@yahoo.com","karma@gmail.com"};
    String mtp[] = {"01124525369","01127515268","0715623569","0751525698","01153685963","01157896354","01128512405"};
    String mbutton[] = {"Delete","Delete","Delete","Delete","Delete","Delete","Delete"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_delete_list);


        listView = findViewById(R.id.ShopListView);
        Myadapter myadapter = new Myadapter(this,mshopname,mshopadderss,memail,mbutton,mtp);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Toast.makeText(shop_delete_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_delete_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_delete_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_delete_list.this,"wf",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


    class Myadapter extends ArrayAdapter<String>
    {
        Context context;
        String rshopname[];
        String rshopaddress[];
        String rmail[];
        String rtp[];
        String rbutton[];

        Myadapter(Context c , String shopname[], String shopaddress[], String email[],String button[],String tp[])
        {
            super(c,R.layout.shop_delete_list_row,R.id.nameID,shopname);
            this.context = c;
            this.rshopname = shopname;
            this.rshopaddress = shopaddress;
            this.rmail = email;
            this.rbutton = button;
            this.rtp = tp;
        }

        @NonNull
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.shop_delete_list_row,parent,false);
            TextView myshopname = row.findViewById(R.id.nameID);
            TextView myshopaddress = row.findViewById(R.id.addreessID);
            TextView myemail = row.findViewById(R.id.emailID);
            TextView mytp = row.findViewById(R.id.tpID);
            TextView mybutton = row.findViewById(R.id.btnID);



            myshopname.setText(rshopname[position]);
            myshopaddress.setText(rshopaddress[position]);
            myemail.setText(rmail[position]);
            mytp.setText(rtp[position]);
            mybutton.setText(rbutton[position]);





            return row;
        }
    }


}