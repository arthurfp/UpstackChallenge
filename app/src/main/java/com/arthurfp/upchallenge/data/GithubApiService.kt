package com.arthurfp.upchallenge.data

import com.arthurfp.upchallenge.models.GithubRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GithubApiService {

    // Apparently, even when username is not correct, Github API is returning the right results, if token is right (with status 200)
    // That behavior was tested and also noticed on Postman.

    @GET("/user/repos")
    @Headers("Accept: application/vnd.github.v3+json")
    suspend fun getGithubRepos(
        @Header("Authorization") authorization: String
    ) : Response<List<GithubRepo>>
}