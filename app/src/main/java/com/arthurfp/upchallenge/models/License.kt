package com.arthurfp.upchallenge.models


import com.google.gson.annotations.SerializedName

/* Generated with "JSON To Kotlin Class" plugin */
data class License(
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    @SerializedName("url")
    val url: String
)