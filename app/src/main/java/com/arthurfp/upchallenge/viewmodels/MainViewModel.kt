package com.arthurfp.upchallenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arthurfp.upchallenge.data.Repository
import com.arthurfp.upchallenge.models.GithubRepo
import com.arthurfp.upchallenge.utils.NetworkResult
import com.arthurfp.upchallenge.utils.handleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val reposResponse: MutableLiveData<NetworkResult<List<GithubRepo>>> = MutableLiveData()

    fun getRepos(user: String, token : String) = viewModelScope.launch {
        getReposCall(user, token)
    }

    private suspend fun getReposCall(user: String, token: String){
        reposResponse.value = NetworkResult.Loading()

        //TODO: check internet connection

        try {
            val response = repository.remote.getGithubRepos(user, token)
            reposResponse.value = response.handleResponse()
        } catch (e: Exception) {
            reposResponse.value = NetworkResult.Error("Repositories not found!")
        }
    }
}