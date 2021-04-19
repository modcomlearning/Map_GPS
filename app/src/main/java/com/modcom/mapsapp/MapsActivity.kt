package com.modcom.mapsapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    private lateinit var fusedLocationClient:  FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //above can laod a map of the whole world
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }//end oncreate

    fun setupGPS(){
        if (ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                   ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                       1)
                    return

                }//end if

           //GET user location if they allowed the permisions
           mMap.isMyLocationEnabled = true    //user need to activate GPS to ON on their settings
           fusedLocationClient.lastLocation.addOnSuccessListener(this) {
               location ->

               if (location!=null){

               }//end if

               else {
                   Toast.makeText(applicationContext, "We can't retrieve your location", Toast.LENGTH_LONG).show()
               }//end else

           }//end fused successlistener


    }//end gps set up



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
        val uon = LatLng(-1.2234583,36.7393568)
        mMap.addMarker(MarkerOptions().position(uon).title("UON - Kabete Campus"))

        val ku = LatLng(-1.1803952,36.9290712)
        mMap.addMarker(MarkerOptions().position(ku).title("KU - Main Campus"))

        val jkuat = LatLng(-1.0913925,37.0094659)
        mMap.addMarker(MarkerOptions().position(jkuat).title("JKUAT - Main Campus"))

        val uon_main = LatLng(-1.2794987,36.813756)
        mMap.addMarker(MarkerOptions().position(uon_main).title("UON - Main Campus"))


        //we will add where your are - your current location  - GPS
        //we need to update below code to show the current location
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uon, 15f))  //0-17
    }
}