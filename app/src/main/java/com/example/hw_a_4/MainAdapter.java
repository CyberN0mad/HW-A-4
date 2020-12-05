package com.example.hw_a_4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    public List<String> list;
    private Context context;
    private OnClick onClick;


    public MainAdapter(List<String> list, Context context, OnClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.textView.setText(list.get(position));

        Log.d("Adapter", "onBindViewHolder" + position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("Adapter", "onCreateView ");
            textView = itemView.findViewById(R.id.txtView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.openActivity(getAdapterPosition());
                }
            });
        }
    }

}



