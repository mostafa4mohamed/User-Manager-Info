package com.user.info.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.user.domain.entities.User
import com.user.info.R
import com.user.info.databinding.FragmentUserDetailsBinding
import com.user.info.state.NetworkState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {


    private lateinit var binding: FragmentUserDetailsBinding
    private val viewModel: UserDetailsViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        actions()
        observing()
        getData()

    }

    private fun actions() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun init() {

    }

    private fun getData() {
        viewModel.userDetails(args.userId)
    }

    private fun observing() {
        lifecycleScope.launch {
            viewModel.userDetailsStateFlow.collect {
                when (it) {
                    is NetworkState.Idle -> return@collect
                    is NetworkState.Loading -> {
                        visViews(false)
                    }

                    is NetworkState.Result<*> -> {
                        val data = it.response as User
                        visViews(true)
                        fillUI(data)
                    }

                    is NetworkState.Error -> it.handleErrors(requireContext())
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillUI(data: User) {

        binding.apply {

            tvNameValue.text = data.name
            tvJobTitleValue.text = data.jobTitle
            tvAgeValue.text = "${data.age} ${getString(R.string.years)}"
            tvGenderValue.text =
                if (data.gender == 1) getString(R.string.male) else getString(R.string.female)

        }

    }

    private fun visViews(state: Boolean) {
        binding.apply {

            tvNameKey.isVisible = state
            tvNameValue.isVisible = state
            tvJobTitleKey.isVisible = state
            tvJobTitleValue.isVisible = state
            tvAgeKey.isVisible = state
            tvAgeValue.isVisible = state
            tvGenderKey.isVisible = state
            tvGenderValue.isVisible = state

            progressBar.isVisible = !state
        }
    }

}