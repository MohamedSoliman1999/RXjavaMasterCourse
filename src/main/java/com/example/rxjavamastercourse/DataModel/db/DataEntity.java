package com.example.rxjavamastercourse.DataModel.db;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="repoTable")
public class DataEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    public String id;
    @ColumnInfo(name = "name")
    @SerializedName("name")
    public String name;
    @Nullable
    @ColumnInfo(name = "description")
    @SerializedName("description")
    public String desc;
    @Nullable
    @ColumnInfo(name = "language")
    @SerializedName("language")
    public String Lang;
    @ColumnInfo(name = "stargazers_count")
    @SerializedName("stargazers_count")
    public String starCount;
    public DataEntity(String name, String desc, String lang,String starCount) {
        this.name = name;
        this.desc = desc;
        this.starCount=starCount;
        this.Lang = lang;
    }
    public DataEntity() {

    }



}
