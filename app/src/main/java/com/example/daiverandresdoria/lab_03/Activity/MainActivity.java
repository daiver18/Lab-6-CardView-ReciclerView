package com.example.daiverandresdoria.lab_03.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.daiverandresdoria.lab_03.Model.Fruit;
import com.example.daiverandresdoria.lab_03.MyAdapter.MyAdapter;
import com.example.daiverandresdoria.lab_03.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mlayoutManager;
    private MyAdapter myAdapter;
    private List<Fruit> fruits;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        fruits=this.allFruits();

        mlayoutManager = new LinearLayoutManager(this);
        myAdapter = new MyAdapter(fruits, R.layout.reclycler_view_item,this, new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                fruit.Limit(1);
                myAdapter.notifyItemChanged(position);
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                addFruit();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public List<Fruit> allFruits(){
        return  new ArrayList<Fruit>(){{
            add(new Fruit("apple","big apple",0,R.drawable.new_fruit,R.mipmap.apple));
            add(new Fruit("banana","large banana",0,R.drawable.banana_bg,R.mipmap.banana));
            add(new Fruit("cherry","red cherry",0,R.drawable.cherry_bg,R.mipmap.cherry));
            add(new Fruit("orange","delicious orange",0,R.drawable.orange_bg,R.mipmap.orange));
            add(new Fruit("pear","green pear",0,R.drawable.pear_bg,R.mipmap.pear));
            add(new Fruit("plum","blue plum",0,R.drawable.plum_bg,R.mipmap.plum));
            add(new Fruit("raspberry","violet raspberry",0,R.drawable.raspberry_bg,R.mipmap.raspberry));
            add(new Fruit("strawberry","litter strawberry",0,R.drawable.strawberry_bg,R.mipmap.strawberry));
        }};
    }
    private void addFruit() {
        int position = fruits.size();
        fruits.add(position,new Fruit("new fruit "+(++count),"Unkown",0,R.drawable.new_fruit,R.mipmap.new_fruit));
        myAdapter.notifyItemInserted(position);
        mlayoutManager.scrollToPosition(position);
    }



}
