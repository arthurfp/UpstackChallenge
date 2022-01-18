package com.arthurfp.upchallenge.ui.fragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.arthurfp.upchallenge.R
import com.arthurfp.upchallenge.databinding.AuthFragmentBinding
import com.arthurfp.upchallenge.utils.NetworkResult
import com.arthurfp.upchallenge.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private var _binding: AuthFragmentBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.main_nav) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        // Clean previous results on shared view model (in case the user is returning on authentication page)
        mainViewModel.reposResponse.value = null

        mainViewModel.reposResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Error -> {
                    binding.layoutAuthError.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    if (response.data != null) {
                        // I could send the token by safeArgs to mainFragment for other usages,
                        // but since it's not need on this challenge, I decided to don't send something that won't be used.
                        findNavController().navigate(R.id.action_authFragment_to_mainFragment)
                    } else {
                        binding.layoutAuthError.visibility = View.VISIBLE
                    }
                }
                is NetworkResult.Loading -> {
                    //TODO: Show Loading Animation
                }
            }
        })

        binding.btnAuth.setOnClickListener { authListener() }

        return binding.root
    }

    private fun authListener() {
        // reset error message about wrong credentials
        binding.layoutAuthError.visibility = View.GONE

        val strUser = binding.inputAuthUser.text.toString().trim()
        val strToken = binding.inputAuthToken.text.toString().trim()

        if (strToken.isBlank() || strUser.isBlank()) {
            // TODO: Handle scenarios where user left the input blank, showing the proper error message on screen instead of a toast message
            Toast.makeText(
                requireContext(),
                Html.fromHtml("<font color='#ff0000'><b>${getString(R.string.blank_token_error)}</b></font>", Html.FROM_HTML_MODE_COMPACT),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            getRepos(strUser, strToken)
        }
    }

    private fun getRepos(user: String, token: String) {
        lifecycleScope.launch {
            mainViewModel.getRepos(user, token)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks
        _binding = null
    }

}