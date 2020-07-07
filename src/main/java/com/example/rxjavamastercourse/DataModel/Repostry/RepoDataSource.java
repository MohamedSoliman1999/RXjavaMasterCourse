package com.example.rxjavamastercourse.DataModel.Repostry;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RepoDataSource {
    public Observable<List<DataEntity>> fetchRepos(String userName);
    public void saveRepos(List<DataEntity>dataEntityList);
}
