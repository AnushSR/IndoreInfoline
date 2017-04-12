package com.indore.indoreinfoline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.indore.indoreinfoline.model.Places;
import com.indore.indoreinfoline.viewholder.PlacesViewHolder;
import com.squareup.picasso.Picasso;

public class PlacesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Places, PlacesViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        mDatabase = FirebaseDatabase.getInstance().getReference("places");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Places places = new Places("Rajwada", "dfsdf", 1);
        mDatabase.setValue(places);
        //getAllPlaces();
    }

    private void getAllPlaces() {
        mAdapter = new FirebaseRecyclerAdapter<Places, PlacesViewHolder>(Places.class, R.layout.item_places, PlacesViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(PlacesViewHolder viewHolder, Places model, int position) {
                viewHolder.bindToPost(model);
                Picasso.with(getApplicationContext()).load(model.placeURL).into(viewHolder.placePic);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }
}
