package com.example.foodycookbook.BackendRequest;

import android.content.Context;


import com.example.foodycookbook.BuildConfig;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServerInstance {


    public static String SERVER_ADDRESS="https://www.themealdb.com/";


    public static Retrofit getInstance(){
        return getInstance(null);
    }

    public static String getSERVER_ADDRESS(){

        return SERVER_ADDRESS;
    }

    public static Retrofit getInstance(String serverAddress){

        if(retrofit==null) {

            new ServerInstance(null,serverAddress);
        }
        return retrofit;
    }

   static Retrofit retrofit;
    public ServerInstance(Context context, String serverAddress){
        retrofit = new Retrofit.Builder()
                .client(getHttpClient())

                .baseUrl(serverAddress!=null?serverAddress:getSERVER_ADDRESS())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    


    public static OkHttpClient getHttpClient(){

        final OkHttpClient.Builder client;
        client = new OkHttpClient.Builder();
        client.readTimeout(40, TimeUnit.SECONDS);
        client.writeTimeout(40, TimeUnit.SECONDS);
        client.connectTimeout(10, TimeUnit.SECONDS);

        try {


            SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, trustAllCerts, null);

            client.sslSocketFactory(sslcontext.getSocketFactory(),(X509TrustManager)trustAllCerts[0]);

            return client .build();
        } catch (Exception e) {
            return client .build();
        }
    }

    public static final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
    };

    public static  <T> T getApiInterfaceService(Class<T> apiClass){
        return ServerInstance.getInstance().create(apiClass);
    }

}
