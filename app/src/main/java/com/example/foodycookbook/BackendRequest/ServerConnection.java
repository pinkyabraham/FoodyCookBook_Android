package com.example.foodycookbook.BackendRequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerConnection {
    public static void getRandomFoodList(Context context,GenericJobCompletionWithFailureListener listener){
        ServerInstance.getApiInterfaceService(ApiInterface.class).getRandomFoodList().
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200 && listener!=null) {
                            try {
                                listener.onJobSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                onFailure(null,null);
                            }
                        }
                        else
                        {
                            onFailure(null,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(listener!=null)
                            listener.onJobFailed(t);
                    }
                });
    }


    public static void searchFoodList(Context context,String searchText,GenericJobCompletionWithFailureListener listener){
        ServerInstance.getApiInterfaceService(ApiInterface.class).searchFoodList(searchText).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()==200 && listener!=null) {
                            try {
                                listener.onJobSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                                onFailure(null,null);
                            }
                        }
                        else
                        {
                            onFailure(null,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(listener!=null)
                            listener.onJobFailed(t);
                    }
                });
    }

}
