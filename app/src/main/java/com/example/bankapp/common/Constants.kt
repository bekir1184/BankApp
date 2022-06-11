package com.example.bankapp.common

import android.app.Activity
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

object Constants {

    const val BASE_URL = "https://raw.githubusercontent.com/fatiha380/mockjson/main/"

    fun String.getLocation(activity: Activity):LatLng{
        val geocoder = Geocoder(activity)
        var latLng = LatLng(0.0,0.0)
        var list : List<Address>
        try {
            list = geocoder.getFromLocationName(this,1)
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