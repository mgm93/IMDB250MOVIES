package com.mgm.imdb250movies.models.register

import com.google.gson.annotations.SerializedName

/**
 * Created by Majid-Golmoradi on 9/6/2022.
 * Email: golmoradi.majid@gmail.com
 */
data class BodyRegister(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("name")
    var name: String = "",
)
