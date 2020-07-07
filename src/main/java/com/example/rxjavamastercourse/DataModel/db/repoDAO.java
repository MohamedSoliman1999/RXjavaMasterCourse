package com.example.rxjavamastercourse.DataModel.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Observable;

@Dao
public interface repoDAO {
    @Query("SELECT * FROM repoTable")
    public Observable<List<DataEntity>> fetchAllMyStarsRepos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveAllMyStarsRepos(List<DataEntity>data);
    @Query("Select count(*) from repoTable")
    public int getLengthRoom();
}
