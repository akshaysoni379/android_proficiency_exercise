package com.wipro.android.proficiencyexercise.data.remote.response

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class Row : Serializable {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("imageHref")
    var imageHref: String? = null
}
