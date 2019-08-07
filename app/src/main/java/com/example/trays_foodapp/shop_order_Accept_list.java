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

public class shop_order_Accept_list extends AppCompatActivity {


    ListView listView;

    String mfood_name[] = {"Cheese Kottu","Chicken pizza","Ice coffee","Egg fried rice","Sausage delight pizza","Mongolian rice","Milk Shake"};
    String mfood_price[] = {"LKR 500.00","LKR 1990.00","LKR 300.00","LKR 450.00","LKR 2200.00","LKR 350.00","LKR 320.00"};
    String mcus_name[] ={"Nuwana Thilakasiri","Tereen Prasanga","Roshani Thamara","Kasun Kalhara","Kusal Mendus","Gihan Thilakerathna","Keshana Rajapaksha"};
    String mcus_tp[] = {"01124525369","01127515268","0715623569","0751525698","01153685963","01157896354","01128512405"};
    String mcus_address[] = {"117 Colombo - Batticaloa Hwy","501/1/A New Kandy Rd, thalangama","174 A2, Galle Road, Mount Lavinia","51, Perahera Mawatha, Colombo 03","No. 170/A, Old Kesbewa Road, Delkanda","Wonder Hotel, Colombo 213 Galle Rd, Colombo 03","Manikpuragama, Situlpawwa road, Kataragama"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_order__accept_list);


        listView = findViewById(R.id.Shop_order_listViewID);
        Myadapter1 myadapter = new Myadapter1(this,mfood_name,mfood_price,mcus_name,mcus_tp,mcus_address);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Toast.makeText(shop_order_Accept_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_order_Accept_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_order_Accept_list.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(shop_order_Accept_list.this,"wf",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}

class Myadapter1  extends ArrayAdapter<String>
{
    Context context;
    String rfood_name[];
    String rfood_price[];
    String rcus_name[];
    String rcus_tp[];
    String rcus_address[];


    Myadapter1(Context c,String food_name[],String food_price[],String cus_name[],String cus_tp[],String cus_address[])
    {
        super(c,R.layout.shop_order_accept_list_row,R.id.food_nameID,food_name);
        this.context = c;
        this.rfood_name = food_name;
        this.rfood_price = food_price;
        this.rcus_name = cus_name;
        this.rcus_address = cus_address;
        this.rcus_tp = cus_tp;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.shop_order_accept_list_row,parent,false);

        TextView myfood_name = row.findViewById(R.id.food_nameID);
        TextView myfood_price = row.findViewById(R.id.food_priceID);
        TextView mycus_name = row.findViewById(R.id.cus_nameID);
        TextView mycus_address = row.findViewById(R.id.cus_addressID);
        TextView mycus_tp = row.findViewById(R.id.cus_tpID);

        myfood_name.setText(rfood_name[position]);
        myfood_price.setText(""+rfood_price[position]);
        mycus_name.setText(rcus_name[position]);
        mycus_address.setText(rcus_address[position]);
        mycus_tp.setText(rcus_tp[position]);







        return row;
    }
}


