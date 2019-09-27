package com.example.trays_foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapterItemsDelete extends RecyclerView.Adapter<RecyclerAdapterItemsDelete.ViewHolderDelete> {

    private static final String TAG = "RecyclerAdapter";

    private Context mContext;

    private ArrayList<Images> imagesList;

    private OnItemClickListner mListner; //Interface

    private OnItemClickListnerX mListners;

    //Interface

    public interface OnItemClickListner{  //I
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){  //I
        mListner = listner;
    }


    public RecyclerAdapterItemsDelete(Context context,ArrayList<Images> imagesList)
    {
        this.mContext = context;
        this.imagesList = imagesList;
    }




    @NonNull
    @Override
    public ViewHolderDelete onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recyclerview_item,viewGroup,false);



        return new ViewHolderDelete(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDelete viewHolderDelete, int i) {

        viewHolderDelete.pname.setText(imagesList.get(i).getName());
        viewHolderDelete.price.setText("LKR. "+imagesList.get(i).getPrice()+".00");

        Picasso.get().load(imagesList.get(i).getUrl())
                .into(viewHolderDelete.imageView);

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolderDelete extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,
            MenuItem.OnMenuItemClickListener {

        ImageView imageView;
        TextView pname;
        TextView price;


        public ViewHolderDelete(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            pname = (TextView) itemView.findViewById(R.id.productName);
            price = (TextView) itemView.findViewById(R.id.prices);



            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if(mListners != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    switch (menuItem.getItemId()){
                        case 1:
                            mListners.onWhatEverClick(position);
                            return true;
                        case 2:
                            mListners.onDeleteClick(position);
                            return true;

                    }
                }
            }
            return false;

        }

        @Override
        public void onClick(View view) {

            if(mListners != null)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListners.onItemClickX(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {


            contextMenu.setHeaderTitle("Select Action");
          //  MenuItem doWhatever = contextMenu.add(Menu.NONE,1,1,"Do whatever");
            MenuItem delete = contextMenu.add(Menu.NONE,2,2,"Delete");

          //  doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }
    }

    public interface OnItemClickListnerX{
        void onItemClickX(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);

    }

    public void setOnItemClickListnerX(OnItemClickListnerX listner){

        mListners = listner;
    }
}
