package com.example.rxjavamastercourse.DataModel.Network;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL="https://api.github.com/";
    private static ApiInterface apiInterface;
    private static ApiClient INSTANCE;

    private ApiClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //for RX java
                .build();
        apiInterface=retrofit.create(ApiInterface.class);
    }
    public static synchronized ApiClient getINSTANCE() {
        if(INSTANCE==null) {
            INSTANCE=new ApiClient();
        }
        return INSTANCE;
    }
    public Observable<List<DataEntity>> getRepo(String  userName) {
        return apiInterface.getStarredRepos(userName);
    }
}
