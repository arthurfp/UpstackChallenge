package com.arthurfp.upchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arthurfp.upchallenge.R
import com.arthurfp.upchallenge.databinding.MainFragmentBinding
import com.arthurfp.upchallenge.ui.adapters.ReposAdapter
import com.arthurfp.upchallenge.utils.NetworkResult
import com.arthurfp.upchallenge.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val mReposAdapter: ReposAdapter by lazy { ReposAdapter() }

    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.main_nav) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        handleResponseData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewRepos.adapter = mReposAdapter
        binding.recyclerViewRepos.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun handleResponseData() {
        when (val response = mainViewModel.reposResponse.value) {
            is NetworkResult.Error -> {
                Toast.makeText(requireContext(),
                    response.message.toString(),
                    Toast.LENGTH_SHORT).show()

                // Return to AuthFragment
                findNavController().navigate(R.id.action_mainFragment_to_authFragment)
            }
            is NetworkResult.Success -> {
                response.data?.let {
                    setupRecyclerView()
                    mReposAdapter.reposList = it
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks
        _binding = null
    }

}