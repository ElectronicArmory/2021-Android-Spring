package com.electronicarmory.gpsmaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            startReportingLocations()
        } else {
            askForPermission()
        }
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 123)
    }

    private fun startReportingLocations() {

        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.isCostAllowed = false

        val provider = locationManager.getBestProvider(criteria, true)
        provider.let {
            val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            if(permission != PackageManager.PERMISSION_GRANTED){
                askForPermission()
            }
            else{
                locationManager.requestLocationUpdates(it!!, 10, 10.0f, locationListener)
            }
        }
    }

    private var locationListener:LocationListener = object: LocationListener{
        override fun onLocationChanged(p0: Location) {
            Log.d("BSU", "${p0.latitude}")

            val ourLocation = LatLng(p0.latitude, p0.longitude)
            mMap.addMarker(MarkerOptions().position(ourLocation).title("Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ourLocation))

            val sydney = LatLng(-34.0, 151.0)

            val polyline:PolylineOptions = PolylineOptions()
                .add(sydney)
                .color(Color.BLACK)
                .width(3f)
                .add(ourLocation)
            mMap.addPolyline(polyline)
        }


//        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//            super.onStatusChanged(provider, status, extras)
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startReportingLocations()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}