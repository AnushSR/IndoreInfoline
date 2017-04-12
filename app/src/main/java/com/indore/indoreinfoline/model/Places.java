package com.indore.indoreinfoline.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Harshit on 4/12/2017.
 */
@IgnoreExtraProperties
public class Places {

    public String placeName;
    public String placeURL;
    public int distance;

    Places() {
    }

    public Places(String placeName, String placeURL, int distance) {
        this.placeName = placeName;
        this.placeURL = placeURL;
        this.distance = distance;
    }

}
