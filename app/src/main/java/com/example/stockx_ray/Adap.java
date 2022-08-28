package com.example.stockx_ray;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adap extends RecyclerView.Adapter<Adap.ViewHolder> {
    ArrayList<String> symbols;
    int amount;


    public Adap(ArrayList<String> symbols, int amount) {
        this.symbols= symbols;
        this.amount = amount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return amount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView dat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dat = itemView.findViewById(R.id.datee);

        }



    }
    String getItem(int id) {
        return symbols.get(id);
    }




}

