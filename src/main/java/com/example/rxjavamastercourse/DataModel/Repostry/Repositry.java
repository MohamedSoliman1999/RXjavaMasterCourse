package com.example.rxjavamastercourse.DataModel.Repostry;

import android.util.Log;
import android.widget.Toast;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;
import com.example.rxjavamastercourse.DataModel.db.RoomRepo;
import com.example.rxjavamastercourse.RXApp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Repositry implements RepoDataSource{
    RoomLocalSource roomLocalSource;
    RemoteSource remoteSource;
    public Repositry(RoomLocalSource roomLocalSource, RemoteSource remoteSource) {
        this.roomLocalSource = roomLocalSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<DataEntity>> fetchRepos(String userName) {
        int size=RoomRepo.getINSTANCE(RXApp.getAppINSTANCE()).getRoomSize();
        if(size<1)
        {
            Toast.makeText(RXApp.getAppINSTANCE(),"from api only "+ size,Toast.LENGTH_LONG).show();
            return remoteSource.fetchRepos(userName)
                    .doOnNext(repo->saveRepos(repo))
                    .onErrorResumeNext(Observable.empty());

        }
        else{
            Toast.makeText(RXApp.getAppINSTANCE(),"from api and Room "+ size,Toast.LENGTH_LONG).show();
            Log.e("Ali","From api and Room###########################");
            return Observable.concat(roomLocalSource.fetchRepos(userName),remoteSource.fetchRepos(userName))
                    .doOnNext(repo->saveRepos(repo))
                    .onErrorResumeNext(Observable.empty());
        }

    }

    @Override
    public void saveRepos(List<DataEntity> dataEntityList) {
        //check here error
        Log.e("Ali","Saved Room###########################");
        roomLocalSource.saveRepos(dataEntityList);
    }
}
