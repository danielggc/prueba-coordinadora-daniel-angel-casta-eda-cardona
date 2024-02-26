package com.shipping.test_cordinadora.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shipping.test_cordinadora.R
import com.shipping.test_cordinadora.databinding.FragmentViewMapsShippingBinding
import com.shipping.test_cordinadora.databinding.LoginBinding
import com.shipping.test_cordinadora.ui.viewmodel.UserViewModel
import com.shipping.test_cordinadora.ui.viewmodel.ViewModelFactory

class MapsFragment : Fragment() {


    private  var _binding: FragmentViewMapsShippingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireContext()
        _binding = FragmentViewMapsShippingBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
    }

}