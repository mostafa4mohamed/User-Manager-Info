package com.user.info.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.user.domain.entities.User
import com.user.info.databinding.FragmentHomeBinding
import com.user.info.state.NetworkState
import com.user.info.ui.add_user.AddUserDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val mFragmentManager: FragmentManager by lazy {
        parentFragmentManager
    }

    @Inject
    lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        actions()
        observing()
        getData()

    }

    private fun init() {
        binding.rvUsers.adapter = adapter
    }

    private fun actions() {
        adapter.setOnItemClickListener(::onUserClickListener)
        binding.addNewUser.setOnClickListener { showAddNewUserDialog() }
    }

    private fun onUserClickListener(user: User) {

        val destination = HomeFragmentDirections.actionHomeFragmentToUserDetailsFragment(user.id!!)
        findNavController().navigate(destination)

    }

    private fun showAddNewUserDialog() {

        val addUserDialog =
            AddUserDialog()

        addUserDialog.show(mFragmentManager, null)

        addUserDialog.setOnDoneClickListener {
            getData()
        }

    }

    private fun getData() {
        viewModel.getAllUsers()
    }

    private fun observing() {
        lifecycleScope.launch {
            viewModel.usersStateFlow.collect {
                when (it) {
                    is NetworkState.Idle -> return@collect
                    is NetworkState.Loading -> {
                        visViews(false)
                    }

                    is NetworkState.Result<*> -> {
                        val data = it.response as List<User>
                        visViews(true)
                        visEmptyView(data.isEmpty())
                        adapter.submitList(data)
                    }

                    is NetworkState.Error -> it.handleErrors(requireContext())
                }
            }
        }
    }

    private fun visEmptyView(empty: Boolean) {
        binding.apply {
            rvUsers.isVisible = !empty
            tvEmpty.isVisible = empty
        }
    }

    private fun visViews(state: Boolean) {
        binding.apply {
            rvUsers.isVisible = state
            progressBar.isVisible = !state
        }
    }

}