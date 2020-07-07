package com.example.rxjavamastercourse.UI.SecondActivity.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import android.content.Context;

import com.example.rxjavamastercourse.R;
import com.example.rxjavamastercourse.DataModel.db.DataEntity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepoAdatpter extends RecyclerView.Adapter<RepoAdatpter.RepoHolder> {
    List<DataEntity> arrayList = new ArrayList<>();

    public RepoAdatpter(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;

    public RepoAdatpter(List<DataEntity> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    public void setList(List<DataEntity> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Display item cardView in parent layout in Recycle View
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_recycleview, parent, false);
        return new RepoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepoHolder holder, final int position) {
        holder.Name_txt.setText(arrayList.get(position).name);
        holder.lang_txt.setText(arrayList.get(position).Lang);
        holder.starsCount_txt.setText(arrayList.get(position).starCount);
        if(arrayList.get(position).desc!=null&&!arrayList.get(position).desc.isEmpty()){
            holder.desc_txt.setText(arrayList.get(position).desc);
        }else{holder.desc_txt.setText("No Desc");}
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder {
        TextView Name_txt;
        TextView desc_txt;
        TextView lang_txt;
        TextView starsCount_txt;
        public RepoHolder(@NonNull final View itemView) {
            super(itemView);
            Name_txt=itemView.findViewById(R.id.repoName_txt);
            desc_txt=itemView.findViewById(R.id.description_txt);
            lang_txt=itemView.findViewById(R.id.language_txt);
            starsCount_txt=itemView.findViewById(R.id.starsCount_txt);
        }
    }

    public DataEntity getWordItemAt(int id) {
        return arrayList.get(id);
    }
}
