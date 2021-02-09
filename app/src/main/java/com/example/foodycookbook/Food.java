package com.example.foodycookbook;

import org.json.JSONException;
import org.json.JSONObject;

public class Food {
    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    private String imageResource;

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    private String strMeal;
    private String strMealThumb;

    public Food() {

    }
    public Food(String imageResource, String strMeal) {
        this.imageResource = imageResource;
        this.strMeal = strMeal;
        this.strMealThumb = strMeal;

    }
    public String getImageResource() {
        return imageResource;
    }
    public String getText1() {
        return strMeal;
}

public static Food getFoodFromJson(JSONObject jsonObject){
        Food foodItem=new Food();
    try {
        foodItem.setImageResource(jsonObject.getString("strMealThumb"));
        foodItem.setStrMeal(jsonObject.getString("strMeal"));
    } catch (JSONException e) {
        e.printStackTrace();
    }
return foodItem;
}
}
