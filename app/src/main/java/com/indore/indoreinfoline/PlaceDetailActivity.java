package com.indore.indoreinfoline;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PlaceDetailActivity extends AppCompatActivity {

    public static final String PLACE_NAME = "place_name";
    public static final String PLACE_TEXT = "place_text";
    public static final String PLACE_PIC = "place_pic";
    public static final String PLACE_LOC = "place_loc";
    private String placeName;
    private String placeText;
    private String placePic;
    private TextView placeTextView;
    private ImageView placeImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private String placeLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        placeName = getIntent().getStringExtra(PLACE_NAME);
        placeText = getIntent().getStringExtra(PLACE_TEXT);
        placePic = getIntent().getStringExtra(PLACE_PIC);
        placeLoc = getIntent().getStringExtra(PLACE_LOC);
        if (placeName == null && placeText == null && placePic == null) {
            throw new IllegalArgumentException("Must pass EXTRA_PLACE_KEY");
        }

        placeTextView = (TextView) findViewById(R.id.place_detail);
        placeImageView = (ImageView) findViewById(R.id.place_banner);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Glide.with(this).load(placePic).placeholder(R.mipmap.ic_launcher).into(placeImageView);
        placeTextView.setText(placeText);
        collapsingToolbar.setTitle(placeName);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + placeLoc + "(" + Uri.encode(placeName) + ")");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
