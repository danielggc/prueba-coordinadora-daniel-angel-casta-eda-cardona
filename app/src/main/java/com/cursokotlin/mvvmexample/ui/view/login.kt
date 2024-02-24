package com.cursokotlin.mvvmexample.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cursokotlin.mvvmexample.databinding.LoginBinding
import com.cursokotlin.mvvmexample.databinding.ReorderLoadBinding
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel
import com.cursokotlin.mvvmexample.ui.viewmodel.UserViewModel
import com.cursokotlin.mvvmexample.ui.viewmodel.ViewModelFactory
import com.example.loginpage.util.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.cursokotlin.mvvmexample.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    private lateinit var binding: LoginBinding
    private lateinit var userVM: UserViewModel
    private lateinit var  auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        userVM =
            ViewModelProvider(this, ViewModelFactory(application)).get(UserViewModel::class.java)
        binding.userVM = userVM
        binding.executePendingBindings()
        observeDataValidation()
        observeLoginResult()
        }



    private fun observeDataValidation() {
        // Reacting to password validation result
        userVM.passwordValidation.observe(this, Observer {
            when (it) {
                LoginResult.EMPTY_PASSWORD.value -> setPasswordError(getString(R.string.password_error_no_password))
                LoginResult.SHORT_PASSWORD.value -> setPasswordError(getString(R.string.password_error_short_password))
                else -> binding.editPassword.error = null
            }
        })

        userVM.usernameValidation.observe(this, Observer { newValue: Int ->
            when (newValue) {
                LoginResult.EMPTY_USERNAME.value -> setUserNameError(getString((R.string.username_error_no_username)))
                LoginResult.LONG_USERNAME.value -> setUserNameError(getString((R.string.username_error_long_username)))
                else -> binding.editUsername.error = null
            }
        })
    }

    private fun observeLoginResult() {
        userVM.loginResult.observe(this, Observer {
            when (it) {
                LoginResult.LOGIN_ERROR.value -> showSnackbar()
                LoginResult.SUCCESSFUL.value -> startMainActivity();
            }
        })
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, R.string.login_error_incorrect_input, Snackbar.LENGTH_LONG)
            .setAction("Sign up") {
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
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}