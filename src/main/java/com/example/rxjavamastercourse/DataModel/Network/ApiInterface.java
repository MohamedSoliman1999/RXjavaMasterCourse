package com.example.rxjavamastercourse.DataModel.Network;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("users/{user}/starred")
    public Observable<List<DataEntity>> getStarredRepos(@Path("user")String username);
}
