package com.example.stockx_ray;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    ArrayList<String> date;
    int amount;
    private ItemClickListener mClickListener;


    public Adapter(ArrayList<String> date, int amount) {
        this.date= date;
        this.amount = amount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dat.setText(date.get(position));
    }


    @Override
    public int getItemCount() {
        return amount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView dat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dat = itemView.findViewById(R.id.dat);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }
    String getItem(int id) {
        return date.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }



}

