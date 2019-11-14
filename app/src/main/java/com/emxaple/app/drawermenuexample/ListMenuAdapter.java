package com.emxaple.app.drawermenuexample;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListMenuAdapter extends RecyclerView.Adapter<ListMenuAdapter.MyViewHolder> {

    ArrayList<String> items;
    Activity context;

    public ListMenuAdapter(Activity context, ArrayList<String> list) {
        items = new ArrayList<>();
        items.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        holder.txt_title.setText(items.get(position));
        holder.txt_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).closeDrawer();
                if(position == 0){
                    Intent intent = new Intent(view.getContext(), ProfileActivity.class);
                    view.getContext().startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(view.getContext(), DialActivity.class);
                    view.getContext().startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(view.getContext(), SendSmsActivity.class);
                    view.getContext().startActivity(intent);
                }else if (position == 3) {
                    Intent intent = new Intent(view.getContext(), SearchVideosActivity.class);
                    view.getContext().startActivity(intent);
                }else if (position == 4) {
                    Intent intent = new Intent(view.getContext(), CameraActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);

        }
    }

}
