package com.arthurfp.upchallenge.models


import com.google.gson.annotations.SerializedName

/* Generated with "JSON To Kotlin Class" plugin */
data class Plan(
    @SerializedName("collaborators")
    val collaborators: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("private_repos")
    val privateRepos: Int,
    @SerializedName("space")
    val space: Int
)