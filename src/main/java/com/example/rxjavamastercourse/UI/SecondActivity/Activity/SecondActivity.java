package com.example.rxjavamastercourse.UI.SecondActivity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.view.View;

import com.example.rxjavamastercourse.DataModel.db.DataEntity;
import com.example.rxjavamastercourse.DataModel.Network.ApiClient;
import com.example.rxjavamastercourse.RXApp;
import com.example.rxjavamastercourse.databinding.ActivitySecondBinding;

import java.util.List;

public class SecondActivity extends AppCompatActivity {
ActivitySecondBinding binding;
    RepoAdatpter adapter;
    SecondActivityModelView viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySecondBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_second);
        adapter=new RepoAdatpter(getBaseContext());
        InitRecylceView();
        //getStarRepo("mrabelwahed");
        //********************************************************ViewModel
        getStarRepoWithMVVM();
    }
    public void InitRecylceView(){
        binding.ryclceView.setAdapter(adapter);
        binding.ryclceView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // adapter.notifyDataSetChanged();
        DividerItemDecoration divider = new DividerItemDecoration(binding.ryclceView.getContext(), DividerItemDecoration.VERTICAL);
        binding.ryclceView.addItemDecoration(divider);
        //Swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            }
        }).attachToRecyclerView(binding.ryclceView);
    }
    public void getStarRepoWithMVVM(){
        viewModel= ViewModelProviders.of(this).get(SecondActivityModelView.class);
        viewModel.getAllRepo("mrabelwahed");
        viewModel.liveData.observe(this, new Observer<List<DataEntity>>() {
            @Override
            public void onChanged(List<DataEntity> dataEntities) {
                adapter.setList(dataEntities);
            }
        });
    }
    public void getStarRepo(String userName){
        ApiClient.getINSTANCE().getRepo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t->adapter.setList(t));
    }
}