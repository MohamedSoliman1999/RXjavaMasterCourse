package com.example.rxjavamastercourse.DataModel.db;

import android.content.Context;
import android.os.AsyncTask;

import com.example.rxjavamastercourse.RXApp;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Check Error here if there
@Database(entities = {DataEntity.class/*,another entity,another Eintity*/}, version = 1)
public abstract class RoomRepo extends RoomDatabase {
    private static RoomRepo INSTANCE;
    public static int RoomSize;
    public abstract repoDAO getRepo_DAO();

    //Singleton
    public static synchronized RoomRepo getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomRepo.class, "repo-Database")
                    .fallbackToDestructiveMigration()
                    //.addCallback(RoomCallback)
                    .build();
        }
        return INSTANCE;
    }
    //-----------------------------------------------------
    //Get count
    public  int getRoomSize() {
        new LengthAsyncTask(getRepo_DAO()).execute();
        return RoomSize;
    }
    //Async Tasks for thread unMain****************************************************
    public static class LengthAsyncTask extends AsyncTask<Integer, Void, Void> {
        public repoDAO repo_Dao;

        public LengthAsyncTask(repoDAO DAO) {
            repo_Dao = DAO;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            RoomSize=RoomRepo.getINSTANCE(RXApp.getAppINSTANCE()).getRepo_DAO().getLengthRoom();
            return null;
        }
    }
}
