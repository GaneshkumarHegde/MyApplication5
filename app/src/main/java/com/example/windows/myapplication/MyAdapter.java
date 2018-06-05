package com.example.windows.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
     Context context;
    private List<Movie> uploads;
    public MyAdapter(Context context, List<Movie> uploads) {
        this.uploads = uploads;
        this.context = context;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,final int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_images, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Movie upload = uploads.get(position);

        holder.textViewName.setText(upload.getTitle());

        Glide.with(context).load(upload.getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);


            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Movie movie = HomeFragment.uploads.get(position);
            Log.d("MYADAPTER","Hello "+movie.getTitle());
            Intent intent = new Intent(context,BookMovie.class);
            intent.putExtra("ID",movie.getID());
            context.startActivity(intent);
        }
    }

}
