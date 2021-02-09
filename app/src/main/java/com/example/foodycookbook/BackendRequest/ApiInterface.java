package com.example.foodycookbook.BackendRequest;




import com.example.foodycookbook.Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/json/v1/1/random.php")
    Call<ResponseBody> getRandomFoodList();

    @GET("/api/json/v1/1/search.php")
    Call<ResponseBody> searchFoodList(@Query("s") String secretKey);

}