package com.shipping.test_cordinadora.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.shipping.test_cordinadora.databinding.LoginBinding
import com.shipping.test_cordinadora.ui.viewmodel.UserViewModel
import com.shipping.test_cordinadora.ui.viewmodel.ViewModelFactory
import com.example.loginpage.util.LoginResult
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shipping.test_cordinadora.R

class Login : Fragment() {


    private lateinit var userVM: UserViewModel
    private  var _binding: LoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val context = requireContext()
        userVM =  ViewModelProvider(this, ViewModelFactory(context)).get(UserViewModel::class.java)

        _binding = LoginBinding.inflate(inflater, container, false)
        _binding?.let { binding ->
            binding.userVM = userVM
        }


        observeDataValidation()
        observeUsernameValidation()
        observeLoginResult()

        val view = binding.root

        val buttonIdLogin = view.findViewById<Button>(R.id.button_login)
        buttonIdLogin.setOnClickListener {
            userVM.login()
        }
        return  view


    }

    private fun observeDataValidation() {
        // Reacting to password validation result
        userVM.passwordValidation.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                LoginResult.EMPTY_PASSWORD.value -> setPasswordError(getString(R.string.password_error_no_password))
                LoginResult.SHORT_PASSWORD.value -> setPasswordError(getString(R.string.password_error_short_password))
                else -> binding.editPassword.error = null
            }
        })
    }

    private fun observeUsernameValidation() {
        userVM.usernameValidation.observe(viewLifecycleOwner, Observer { newValue ->
            when (newValue) {
                LoginResult.EMPTY_USERNAME.value -> setUserNameError(getString((R.string.username_error_no_username)))
                LoginResult.LONG_USERNAME.value -> setUserNameError(getString((R.string.username_error_long_username)))
                else -> binding.editUsername.error = null
            }
        })
    }

    private fun observeLoginResult() {
        userVM.loginResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                LoginResult.LOGIN_ERROR.value -> showSnackbar()
                LoginResult.SUCCESSFUL.value -> startMainActivity()
            }
        })
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, R.string.login_error_incorrect_input, Snackbar.LENGTH_LONG)
            .setAction("Sign up") {
                // Handle action click if needed
            }.show()
    }

    private fun setUserNameError(errorMsg: String) {
        binding.editUsername.error = errorMsg
        binding.editUsername.requestFocus()
    }

    private fun setPasswordError(errorMsg: String) {
        binding.editPassword.error = errorMsg
        binding.editPassword.requestFocus()
    }

    private fun startMainActivity() {

        findNavController().navigate(R.id.action_blankFragment22_to_blankFragment2)
    }


}