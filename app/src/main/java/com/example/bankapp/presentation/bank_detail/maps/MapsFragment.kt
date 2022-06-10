package com.example.bankapp.presentation.bank_detail.maps

import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bankapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.lang.Exception

class MapsFragment(val location: String) : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        //38.67817816086229, 39.20205574557179

        val loc = getLatLng(location)
        googleMap.addMarker(MarkerOptions().position(loc).title(""))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        val cameraPosition = CameraPosition.Builder()
            .target(loc) // Sets the center of the map to Mountain View
            .zoom(17f)            // Sets the zoom
            .bearing(90f)         // Sets the orientation of the camera to east
            .tilt(10f)            // Sets the tilt of the camera to 30 degrees
            .build()              // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun getLatLng(s: String): LatLng {
        val geocoder = Geocoder(activity)
        var latLng = LatLng(0.0,0.0)
        var list : List<Address>
        try {
            list = geocoder.getFromLocationName(s,1)
            if(list!=null){
                val lat = list[0].latitude
                val lng = list[0].longitude
                latLng = LatLng(lat,lng)
            }
        }catch (e : IOException){
            println(e.message)
        }
        return latLng
    }
}