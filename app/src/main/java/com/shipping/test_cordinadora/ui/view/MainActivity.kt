package com.shipping.test_cordinadora.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.room.Room
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import dagger.hilt.android.AndroidEntryPoint
import com.shipping.test_cordinadora.R
import com.shipping.test_cordinadora.data.database.RemissionDatabase
import com.google.firebase.FirebaseApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var navController: NavController
    lateinit var database: RemissionDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        database = Room.databaseBuilder(applicationContext, RemissionDatabase::class.java, "database-name")
            .fallbackToDestructiveMigration() // Permite migraciones destructivas
            .build()
        createMapFragment()

    }




    private lateinit var map: GoogleMap
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


}