package com.emxaple.app.drawermenuexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btn_drawer;
    DrawerLayout drawer;
    RecyclerView list_menu;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_drawer = findViewById(R.id.btn_drawer);
        drawer=  findViewById(R.id.drawer);
        list_menu = findViewById(R.id.list_menu);


        btn_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });

        items = new ArrayList<>();
        items.add("Profile");
        items.add("Dial");
        items.add("Send SMS");
        items.add("Search Videos");
        items.add("Open Camera");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(list_menu.getContext(),((LinearLayoutManager) layoutManager).getOrientation());
        list_menu.addItemDecoration(dividerItemDecoration);

        list_menu.setLayoutManager(layoutManager);
        ListMenuAdapter adapter = new ListMenuAdapter(this, items);
        list_menu.setAdapter(adapter);


    }

    public void openDrawer() {
        drawer.openDrawer(Gravity.LEFT);
    }

    public void closeDrawer(){
        drawer.closeDrawer(Gravity.LEFT);
    }

}
