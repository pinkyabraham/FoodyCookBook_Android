package com.example.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;

public class FoodItems extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoodItemsAdapter adapter;
    private List<Food> foodList;
   SearchView searchET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_items);

            searchET = findViewById(R.id.search);
            fillExampleList();
            getproducts();

        searchET.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchET.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        }
    private void fillExampleList() {
        foodList = new ArrayList<>();
        foodList.add(new Food(R.drawable.ic_action_food, "One"));
        foodList.add(new Food(R.drawable.ic_action_food, "Two"));
        foodList.add(new Food(R.drawable.ic_action_food, "Three"));
        foodList.add(new Food(R.drawable.ic_action_food, "Four"));
        foodList.add(new Food(R.drawable.ic_action_food, "Five"));
        foodList.add(new Food(R.drawable.ic_action_food, "Six"));
        foodList.add(new Food(R.drawable.ic_action_food, "Seven"));
        foodList.add(new Food(R.drawable.ic_action_food, "Eight"));
        foodList.add(new Food(R.drawable.ic_action_food, "Nine"));
    }
        private void getproducts() {
            recyclerView = findViewById(R.id.recyclerview);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
           adapter = new FoodItemsAdapter(foodList);
          recyclerView.setAdapter(adapter);
        }



}