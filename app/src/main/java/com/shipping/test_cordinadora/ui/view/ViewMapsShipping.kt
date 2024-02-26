package com.shipping.test_cordinadora.ui.view

import android.content.Context
import android.location.Address
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.shipping.test_cordinadora.R
import com.shipping.test_cordinadora.databinding.FragmentViewMapsShippingBinding

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shipping.test_cordinadora.ui.viewmodel.RemisionViewModel
import android.location.Geocoder
import android.util.Log
import java.io.IOException
import java.lang.Error

class ViewMapsShipping : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var remisionViewModel: RemisionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        remisionViewModel = ViewModelProvider(requireActivity()).get(RemisionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_view_maps_shipping, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createMapFragment()
    }



    private fun createMapFragment() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    fun getLocationFromAddress(address: String, geocoder: Geocoder): LatLng? {
        val location: LatLng?
        val addresses: List<Address>?
        try {
            addresses = geocoder.getFromLocationName(address, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                location = LatLng(latitude, longitude)
                return location
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    fun crearEtiqueta( place :LatLng ,title:String){
        map.addMarker(MarkerOptions().position(place).title(title))
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(place, 18f),
            4000,
            null
        )
    }

    fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {
        val geocoder = Geocoder(context)
        val addressList: List<android.location.Address>?
        var latLng: LatLng? = null

        try {
            addressList = geocoder.getFromLocationName(strAddress, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address = addressList[0]
                latLng = LatLng(address.latitude, address.longitude)
            }
        } catch (e: IOException ) {
            e.printStackTrace()
        }
        return latLng
    }
    private fun createMarker() {
        remisionViewModel.remisionLiveData.observe(this, Observer { remision ->
            Log.d("PAN", "createMarker:${remision} ")
            val addressString = " Colombia, Antioqua, Medellin ,"
            val data =  getLocationFromAddress(requireContext(), addressString + remision.direccionDestinatario )
            if ( data != null ){
                Log.d("TAG", "createMarker: ${data}")
                crearEtiqueta(data ,remision.nombreTerminalDestino )
            }

        })
    }
}