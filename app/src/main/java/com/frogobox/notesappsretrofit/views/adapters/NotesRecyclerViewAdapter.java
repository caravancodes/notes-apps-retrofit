package com.frogobox.notesappsretrofit.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frogobox.notesappsretrofit.R;
import com.frogobox.notesappsretrofit.models.Note;
import com.frogobox.notesappsretrofit.views.interfaces.ItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * NotesAppsRetrofit
 * Copyright (C) 19/02/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;

    public NotesRecyclerViewAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_notes, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note data = notes.get(position);
        holder.title.setText(data.getTitle());
        holder.date.setText(data.getDate());
        holder.notes.setText(data.getNote());
        holder.cardView.setCardBackgroundColor(data.getColor());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, date, notes;
        CardView cardView;
        ItemClickListener itemClickListenerItemView;

        public RecyclerViewAdapter(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.list_title);
            date = itemView.findViewById(R.id.list_dates);
            notes = itemView.findViewById(R.id.list_notes);
            cardView = itemView.findViewById(R.id.card_view);


            this.itemClickListenerItemView = listener;
            cardView.setOnClickListener(this::onClick);

        }

        @Override
        public void onClick(View v) {
            itemClickListenerItemView.onItemClick(v, getAdapterPosition());
        }
    }





}
