package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterdUserList extends AppCompatActivity {


    ListView listView;
    String muname[] ={"Nuwana Thilakasiri","Tereen Prasanga","Roshani Thamara","Kasun Kalhara","Kusal Mendus","Gihan Thilakerathna","Keshana Rajapaksha"};
    String memail[] = {"nuwana24@gmail.com","tereen1997@gmail.com","roshani@gmail.com","Kasun95@gmail.com","Kusul@yahoo.com","Gigan123@gmail.com","hasitha97@gmail.com"};
    String mtelephone[] ={"0764567893","0712648367","0758726359","0784523097","0773640892","070248972","0777784357"};
    String mbutton[] = {"Delete","Delete","Delete","Delete","Delete","Delete","Delete"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerd_user_list);



        listView = findViewById(R.id.registredUserList);
        Myadapter adapter =new  Myadapter(this,muname,memail,mbutton,mtelephone);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Toast.makeText(RegisterdUserList.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(RegisterdUserList.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(RegisterdUserList.this,"wf",Toast.LENGTH_SHORT).show();
                }
                if(position == 0)
                {
                    Toast.makeText(RegisterdUserList.this,"wf",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    class Myadapter extends ArrayAdapter<String>
    {
        Context context;
        String runame[];
        String remail[];
        String rbutton[];
        String rtelephone[];



        Myadapter(Context c ,String uname[],String emial[],String button[],String telephone[])
        {
            super(c,R.layout.user_list_row,R.id.unameID,uname);
            this.context = c;
            this.runame = uname;
            this.remail = emial;
            this.rtelephone = telephone;
            this.rbutton = button;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.user_list_row,parent,false);
            TextView myuname = row.findViewById(R.id.unameID);
            TextView myemail = row.findViewById(R.id.emailID);
            TextView mytelephone = row.findViewById(R.id.tpID);
            Button mybutton = row.findViewById(R.id.deleteBtnID);


            myuname.setText(runame[position]);
            myemail.setText(remail[position]);
            mytelephone.setText(rtelephone[position]);
            mybutton.setText(rbutton[position]);

            return row;
        }
    }

}
