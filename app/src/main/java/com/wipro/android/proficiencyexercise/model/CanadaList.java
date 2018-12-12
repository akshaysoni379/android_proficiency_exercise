package com.wipro.android.proficiencyexercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CanadaList {
    @SerializedName("rows")
    private ArrayList<Row> rows;

    public ArrayList<Row> getRows() {
        return rows;
    }

}
