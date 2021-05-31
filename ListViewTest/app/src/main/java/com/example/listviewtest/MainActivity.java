package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();

    private String[] data = {"Apple", "Banana", "Orange","Watermelon",
            "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange","Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item,fruitList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits(){
        for(int i = 0; i < 10; i++){
            Fruit apple = new Fruit("Apple", R.drawable.img01);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.img02);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.img03);
            fruitList.add(orange);
        }
    }
}