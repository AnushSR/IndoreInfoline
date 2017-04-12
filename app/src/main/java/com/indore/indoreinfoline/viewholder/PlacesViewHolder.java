package com.indore.indoreinfoline.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.indore.indoreinfoline.R;
import com.indore.indoreinfoline.model.Places;

/**
 * Created by Harshit on 4/12/2017.
 */

public class PlacesViewHolder extends RecyclerView.ViewHolder {

    public TextView placeName;
    public TextView distance;
    public ImageView placePic;

    public PlacesViewHolder(View itemView) {
        super(itemView);

        placeName = (TextView) itemView.findViewById(R.id.place_name);
        distance = (TextView) itemView.findViewById(R.id.place_dist);
        placePic = (ImageView) itemView.findViewById(R.id.place_pic);
    }

    public void bindToPost(Places places) {
        placeName.setText(places.placeName);
        distance.setText(places.distance + "kms from city");
    }
}
