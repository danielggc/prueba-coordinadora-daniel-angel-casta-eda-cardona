package com.cursokotlin.mvvmexample.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cursokotlin.mvvmexample.databinding.LoginBinding
import com.cursokotlin.mvvmexample.databinding.ReorderLoadBinding
import com.cursokotlin.mvvmexample.ui.viewmodel.QuoteViewModel

class login : AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        }

}