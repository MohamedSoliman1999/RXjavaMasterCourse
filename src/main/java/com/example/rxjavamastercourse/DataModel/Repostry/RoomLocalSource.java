package com.example.rxjavamastercourse.DataModel.Repostry;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;
import com.example.rxjavamastercourse.DataModel.db.RoomRepo;
import com.example.rxjavamastercourse.RXApp;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class RoomLocalSource implements RepoDataSource {
    @Override
    public Observable<List<DataEntity>> fetchRepos(String userName) {
        return RoomRepo.getINSTANCE(RXApp.getAppINSTANCE()).getRepo_DAO().fetchAllMyStarsRepos();
    }

    @Override
    public void saveRepos(List<DataEntity> dataEntityList) {
        RoomRepo.getINSTANCE(RXApp.getAppINSTANCE()).getRepo_DAO().saveAllMyStarsRepos(dataEntityList);
    }
}
