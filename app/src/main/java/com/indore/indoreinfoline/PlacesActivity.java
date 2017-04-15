package com.indore.indoreinfoline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.indore.indoreinfoline.model.Places;
import com.indore.indoreinfoline.viewholder.PlacesViewHolder;

public class PlacesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Places, PlacesViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mDatabase = FirebaseDatabase.getInstance().getReference("places");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Places places = new Places("Rajwada", "http://tripsthan.com/wp-content/uploads/2015/04/rajwada-3.jpg", 1);
//        mDatabase.setValue(places);
        getAllPlaces();
    }

    private void getAllPlaces() {
        showProgressDialogue();
        mAdapter = new FirebaseRecyclerAdapter<Places, PlacesViewHolder>(Places.class, R.layout.item_places, PlacesViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(PlacesViewHolder viewHolder, Places model, int position) {
                viewHolder.bindToPost(model);
                Glide.with(getApplication()).load(model.placeURL).placeholder(R.mipmap.ic_launcher).into(viewHolder.placePic);
            }

            @Override
            protected void onDataChanged() {
                super.onDataChanged();
                hideProgressDialogue();
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    public void showProgressDialogue() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressDialogue() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
