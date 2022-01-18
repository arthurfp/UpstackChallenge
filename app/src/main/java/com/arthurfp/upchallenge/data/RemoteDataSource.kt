package com.arthurfp.upchallenge.data

import android.util.Base64
import com.arthurfp.upchallenge.models.GithubRepo
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val githubApiService: GithubApiService
) {

    suspend fun getGithubRepos(user: String, token: String): Response<List<GithubRepo>> {
        val authPayload = "$user:$token"
        val data = authPayload.toByteArray()
        val base64 = Base64.encodeToString(data, Base64.NO_WRAP)

        return githubApiService.getGithubRepos("Basic $base64".trim())
    }
}