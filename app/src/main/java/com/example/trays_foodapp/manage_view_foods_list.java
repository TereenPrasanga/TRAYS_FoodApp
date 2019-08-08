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
import android.widget.ListView;
import android.widget.TextView;

public class manage_view_foods_list extends AppCompatActivity {

    ListView listView;

    String mfoodname[] = {"Cheese Kottu","Ice Coffee","Lemonade Juice","Mixed Rice","Chicken Rice","Pork Pizza","Vege Pizza"};
    String mfoodprice[] = {"260.89 LKR","345.99 LKR","230.89 LKR","350.00 LKR","456.90 LKR","800.67 LKR","234.00 LKR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_view_foods_list);

        listView = findViewById(R.id.managefoodslistViewID);
        Adapter_managefood adapter_managefood = new Adapter_managefood(this,mfoodname,mfoodprice);
        listView.setAdapter(adapter_managefood);
    }
}

class Adapter_managefood extends ArrayAdapter<String>{

    Context context;
    String rfoodname[];
    String rfoodprice[];

    Adapter_managefood(Context c ,String foodname[],String foodprice[])
    {
        super(c,R.layout.managefoods_list_row,R.id.managefood_nameID,foodname);
        this.context = c;
        this.rfoodname = foodname;
        this.rfoodprice = foodprice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.managefoods_list_row,parent,false);

        TextView myfoodname = row.findViewById(R.id.managefood_nameID);
        TextView myfoodprice = row.findViewById(R.id.managefood_priceID);

        myfoodname.setText(rfoodname[position]);
        myfoodprice.setText(rfoodprice[position]);
        return row;
    }
}

