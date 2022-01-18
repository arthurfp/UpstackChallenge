package com.arthurfp.upchallenge.models


import com.google.gson.annotations.SerializedName

/* Generated with "JSON To Kotlin Class" plugin */
data class Permissions(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean
)