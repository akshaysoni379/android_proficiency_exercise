package com.wipro.android.proficiencyexercise.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Row implements Serializable {

    @SerializedName("title")
    private String title = "";

    @SerializedName("description")
    private String description = "";

    @SerializedName("imageHref")
    private String imageHref = "";

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }
}
