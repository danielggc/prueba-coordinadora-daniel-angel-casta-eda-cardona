package com.cursokotlin.mvvmexample.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import com.cursokotlin.mvvmexample.R
import com.cursokotlin.mvvmexample.data.database.RemissionDatabase
import com.google.firebase.FirebaseApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var database: RemissionDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        database = Room.databaseBuilder(applicationContext, RemissionDatabase::class.java, "database-name")
            .fallbackToDestructiveMigration() // Permite migraciones destructivas
            .build()

    }


}