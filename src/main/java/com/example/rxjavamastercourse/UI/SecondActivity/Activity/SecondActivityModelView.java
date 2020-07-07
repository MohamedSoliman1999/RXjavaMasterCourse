package com.example.rxjavamastercourse.UI.SecondActivity.Activity;

import com.example.rxjavamastercourse.DataModel.Repostry.RemoteSource;
import com.example.rxjavamastercourse.DataModel.Repostry.Repositry;
import com.example.rxjavamastercourse.DataModel.Repostry.RoomLocalSource;
import com.example.rxjavamastercourse.DataModel.db.DataEntity;
import com.example.rxjavamastercourse.DataModel.Network.ApiClient;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class SecondActivityModelView extends ViewModel {

    public MutableLiveData<List<DataEntity>>liveData=new MutableLiveData<List<DataEntity>>();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RoomLocalSource roomLocalSource=new RoomLocalSource();
    RemoteSource remoteSource=new RemoteSource();
    Repositry dataRepositry=new Repositry(roomLocalSource,remoteSource);
    public void getAllRepo(String userName)
    {
         Disposable disposable=dataRepositry.fetchRepos(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t->liveData.setValue(t));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
