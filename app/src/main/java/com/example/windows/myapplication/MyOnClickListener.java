package com.example.windows.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class MyOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(final View view) {
        RecyclerView mRecyclerView = new HomeFragment().recyclerView;
        List<Movie> mList = new HomeFragment().uploads;
        int itemPosition = mRecyclerView.getChildAdapterPosition(view);
        //Movie item = mList.get(itemPosition);

    }
}

