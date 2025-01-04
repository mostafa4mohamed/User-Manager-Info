package com.user.info.ui.add_user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.user.domain.entities.User
import com.user.info.R
import com.user.info.base.BaseDialogFragment
import com.user.info.databinding.DialogAddUserBinding
import com.user.info.state.NetworkState
import com.user.info.utils.validateValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddUserDialog : BaseDialogFragment() {

    private lateinit var binding: DialogAddUserBinding
    private val viewModel: AddUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialAndActions()
        observing()

    }

    private fun initialAndActions() {

        binding.apply {

            btnSave.setOnClickListener { onSaveClick() }
        }

    }

    private fun observing() {
        lifecycleScope.launch {
            viewModel.addUserStateFlow.collect {
                when (it) {
                    is NetworkState.Idle -> return@collect
                    is NetworkState.Loading -> {
                        visViews(false)
                    }

                    is NetworkState.Result<*> -> {
                        visViews(true)
                        successAction()
                    }

                    is NetworkState.Error -> it.handleErrors(requireContext())
                }
            }
        }
    }

    private fun visViews(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.btnSave.isInvisible = state
    }

    private fun successAction() {

        Toast.makeText(
            requireContext(),
            getString(R.string.user_added_successfully),
            Toast.LENGTH_SHORT
        ).show()

        dismiss()
        onDoneClickListener?.let { it1 -> it1() }

    }

    private fun onSaveClick() {

        if (validateInputs()) {

            val user = User(
                name = binding.etName.text.toString(),
                age = binding.etAge.text.toString().toInt(),
                jobTitle = binding.etJobTitle.text.toString(),
                gender = if (binding.rbMale.isSelected) 1 else 0
            )

            viewModel.addUser(user)
        }
    }

    private fun validateInputs(): Boolean {

        var isValidate = true

        if (!binding.etName.validateValue()) isValidate = false
        if (!binding.etAge.validateValue()) isValidate = false
        if (!binding.etJobTitle.validateValue()) isValidate = false
        if (!binding.rgGender.validateValue()) isValidate = false

        return isValidate

    }

    private var onDoneClickListener: (() -> Unit)? =
        null

    fun setOnDoneClickListener(listener: () -> Unit) {
        onDoneClickListener = listener
    }

}