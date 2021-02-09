package com.example.foodycookbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;


import com.example.foodycookbook.BackendRequest.GenericJobCompletionWithFailureListener;
import com.example.foodycookbook.BackendRequest.ServerConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        foodList = new ArrayList<>();
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
                searchFood(newText);
                return false;
            }
        });
    }

    private void getproducts() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FoodItemsAdapter(this,foodList);
        recyclerView.setAdapter(adapter);
        fetchRandomFoods();
        fetchRandomFoods();
        fetchRandomFoods();
        fetchRandomFoods();
        fetchRandomFoods();
        fetchRandomFoods();
    }

    void searchFood(String foodName){
        ServerConnection.searchFoodList(this,foodName, new GenericJobCompletionWithFailureListener() {
            @Override
            public void onJobFailed(Object obj) {
            }

            @Override
            public void onJobSuccess(Object obj) {

                JSONObject randomFoods = null;
                try {
                    randomFoods = new JSONObject((String)obj);
                    JSONArray array=randomFoods.getJSONArray("meals");
                    for (int i=0; i<array.length(); i++) {
                        Food newFood=Food.getFoodFromJson((JSONObject) array.get(i));
                        foodList.add(newFood);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    void fetchRandomFoods(){
        ServerConnection.getRandomFoodList(this, new GenericJobCompletionWithFailureListener() {
            @Override
            public void onJobFailed(Object obj) {

            }
            @Override
            public void onJobSuccess(Object obj) {

                JSONObject randomFoods = null;
                try {
                    randomFoods = new JSONObject((String)obj);
                    JSONArray array=randomFoods.getJSONArray("meals");
                    for (int i=0; i<array.length(); i++) {
                        Food newFood=Food.getFoodFromJson((JSONObject) array.get(i));
                        foodList.add(newFood);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }



}