package com.arthurfp.upchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arthurfp.upchallenge.databinding.RepoItemLayoutBinding
import com.arthurfp.upchallenge.models.GithubRepo

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    var reposList = emptyList<GithubRepo>()

    class ReposViewHolder(val binding: RepoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(
            RepoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.binding.nameRepoItem.text = reposList[position].name
    }

    override fun getItemCount(): Int {
        return reposList.size
    }

}