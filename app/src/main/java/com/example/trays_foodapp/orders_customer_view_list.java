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

public class orders_customer_view_list extends AppCompatActivity {

    ListView listView;

    String mordersname[] = {"Cheese Kottu","Loving Pizza","Pork Pizza","Chicken Pizza","Lemonade Drink","Ice Coffee","Milk Shake"};
    String mordersprice[] = {"LKR 678.00","LKR 6785.00","LKR 6758.00","LKR 6748.00","LKR 6748.00","LKR 6978.00","LKR 978.00"};
    String mordercustomername[] = {"Nuwana Thilakasiri","Tereen Prasanga","Keshana Hiran","Sachin Wijekoon","Aruna Silva","Kumar Silva","Nilaksha Mendis"};
    String morderaddress[] = {"NO23,Galle,Unawatuna","NO23,Matara,Godagama","NO23,Galle,Unawatuna","NO23,Mirissa,Matara","NO23,Galle,Unawatuna","NO23,Kudawella,Tangalle","NO23,,Unawatuna"};
    String mmobile[] = {"0778292929","0778292929","0778272929","0778298929","0770292929","0778292929","0778292989"};

    int morderimage[] = {R.drawable.kottu7,R.drawable.pizza1,R.drawable.pizza7,R.drawable.pizza4,R.drawable.juice4,R.drawable.juice2,R.drawable.juice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_customer_view_list);

        listView = findViewById(R.id.orderslistViewID);

        Adapter_orders adapter_orders = new Adapter_orders(this,morderimage,mordersname,mordersprice,mordercustomername,morderaddress,mmobile);
        listView.setAdapter(adapter_orders);
    }
}

class Adapter_orders extends ArrayAdapter<String>
{
    Context context;
    String rordersname[];
    String rordersprice[];
    String rordercustomername[];
    String rorderaddress[];
    String rmobile[];
    int rorder_image[];

    Adapter_orders(Context c,int order_image[],String ordersname[],String ordersprice[],String ordercustomername[],String orderaddress[],String ordermobile[] )
    {
        super(c,R.layout.orders_customer_list_row,R.id.order_nameID,ordersname);
        this.context = c;
        this.rorder_image = order_image;
        this.rordersname = ordersname;
        this.rordersprice = ordersprice;
        this.rordercustomername = ordercustomername;
        this.rorderaddress = orderaddress;
        this.rmobile = ordermobile;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.orders_customer_list_row,parent,false);
        ImageView myorder_image = row.findViewById(R.id.order_imageID);

        TextView myordersname = row.findViewById(R.id.order_nameID);
        TextView myordersprice = row.findViewById(R.id.order_priceID);
        TextView myordercustomername = row.findViewById(R.id.order_customerID);
        TextView myorderaddress = row.findViewById(R.id.order_addressID);
        TextView mymobile = row.findViewById(R.id.order_mobileID);

        myorder_image.setImageResource(rorder_image[position]);
        myordersname.setText(rordersname[position]);
        myordersprice.setText(rordersprice[position]);
        myordercustomername.setText(rordercustomername[position]);
        myorderaddress.setText(rorderaddress[position]);
        mymobile.setText(rmobile[position]);

        return row;
    }
}

