package com.example.windows.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.example.windows.myapplication.HomeFragment.uploads;

public class BookMovie extends AppCompatActivity {
    int id;
    DatabaseReference myRef;
    private DatabaseReference mDatabase;
    ImageView imageViewMovie;
    TextView textViewTitle;
    TextView textViewLang;
    TextView textViewAbout;
    private ListView listView;
    private ArrayList<String> list = new ArrayList<>();
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_movie);

        Intent i = getIntent();
        list.clear();
        listView = (ListView)findViewById(R.id.listViewTheatres);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        //arrayAdapter.notifyDataSetChanged();
        id = i.getIntExtra("ID",0);

        imageViewMovie = (ImageView)findViewById(R.id.movieImage);
        textViewTitle = (TextView)findViewById(R.id.movieName);
        textViewLang = (TextView)findViewById(R.id.movieLang);
        textViewAbout = (TextView)findViewById(R.id.movieAbout);
        myRef = FirebaseDatabase.getInstance().getReference().child("Movies");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Movie upload = dataSnapshot.getValue(Movie.class);
                if(upload.getID() == id){
                    Glide.with(getApplicationContext()).load(upload.getUrl()).into(imageViewMovie);
                    textViewAbout.setText(upload.getAbout());
                    textViewLang.setText(upload.getLanguage());
                    textViewTitle.setText(upload.getTitle());

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Theatres");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSnapshot dataSnapshot1 = dataSnapshot.child("Movies");
                for(DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){

                    Theatre theatre = dataSnapshot2.getValue(Theatre.class);
                    if(theatre.getID() == id){
                        Toast.makeText(getApplicationContext(),dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                        value = dataSnapshot.getKey().trim().toString();
                        Log.d("Datasnapshot",dataSnapshot.getKey());
                        if(value!=null)
                            list.add(value);
                    }


                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
