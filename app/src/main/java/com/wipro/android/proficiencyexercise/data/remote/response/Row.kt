package com.wipro.android.proficiencyexercise.data.remote.response

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Row : Serializable {

    @SerializedName("title")
    var title = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("imageHref")
    var imageHref = ""
}
