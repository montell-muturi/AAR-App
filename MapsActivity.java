package com.maicy.hospitalsmap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public  class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    double latitude;
    double longitude;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Check if Google Play Services Available or not
        if (!CheckGooglePlayServices()) {
            Log.d("onCreate", "Finishing test case since Google Play Services are not available");
            finish();
        } else {
            Log.d("onCreate", "Google Play Services available.");
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Logic to handle location object
                } else {
                    // Handle null case or Request periodic location update https://developer.android.com/training/location/receive-location-updates
                }
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    private boolean CheckGooglePlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(this, result,
                        0).show();
            }
            return false;
        }
        return true;
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


       /* mMap.setOnInfoWindowClickListener(this){
            @Override
            public void onInfoWindowClick(Marker marker) {
                final String description = marker.getSnippet();
                if (!TextUtils.isEmpty(description)) {
                    final Spannable span = Spannable.Factory.getInstance().newSpannable(description);
                    if (Linkify.addLinks(span, Linkify.PHONE_NUMBERS)) {
                        final URLSpan[] old = span.getSpans(0, span.length(), URLSpan.class);
                        if (old != null && old.length > 0) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(old[0].getURL()));
                            startActivity(intent);
                        }
                    }
                }


            }
        };*/



        Button btnHospital = findViewById(R.id.btnHospital);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            String Hospital = "hospital";


            @Override
            public void onClick(View v) {
                LatLng aarLangata = new LatLng(-1.305144, 36.825847);
                mMap.addMarker(new MarkerOptions()
                        .position(aarLangata)
                        .title("AAR Healthcare Karen/Langata Outpatient Centre")
                        .snippet("Phone: 0735963412"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarLangata));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarLavington = new LatLng(-1.290000, 36.770670);
                mMap.addMarker(new MarkerOptions()
                        .position(aarLavington)
                        .title("AAR Healthcare, Lavington Clinic")
                        .snippet("+254780200456"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarLavington));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarAdams = new LatLng(-1.293740, 36.811870);
                mMap.addMarker(new MarkerOptions()
                        .position(aarAdams)
                        .title("AAR Healthcare, Adams Clinic")
                        .snippet("Phone: +254731191077"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarAdams));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarNgong = new LatLng(-1.299300, 36.794370);
                mMap.addMarker(new MarkerOptions()
                        .position(aarNgong)
                        .title("Aar Healthcare-Greenhouse,Ngong Road")
                        .snippet("Phone: +254706595786"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarNgong));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarSouthC = new LatLng(-1.315540, 36.828030);
                mMap.addMarker(new MarkerOptions()
                        .position(aarSouthC)
                        .title("AAR Healthcare, South C Clinic\n")
                        .snippet("Phone: +254780888150"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarSouthC));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarSaritCentre = new LatLng(-1.307780, 36.732550);
                mMap.addMarker(new MarkerOptions()
                        .position(aarSaritCentre)
                        .title("AAR Healthcare, Sarit Centre Clinic\n")
                        .snippet("Phone: +254780888150"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarSaritCentre));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarBuru = new LatLng(-1.284050, 36.869030);
                mMap.addMarker(new MarkerOptions()
                        .position(aarBuru)
                        .title("AAR Healthcare, BuruBuru Clinic\n")
                        .snippet("Phone: +254780888234"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarBuru));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarDon = new LatLng(-1.283330, 36.816670);
                mMap.addMarker(new MarkerOptions()
                        .position(aarDon)
                        .title("AAR Healthcare, Donholm Clinic\n")
                        .snippet("Phone: +254733881420"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarDon));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarICEA = new LatLng(-1.294796, 36.812613);
                mMap.addMarker(new MarkerOptions()
                        .position(aarICEA)
                        .title("AAR Healthcare, ICEA Clinic\n")
                        .snippet("Phone: +254731191070"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarICEA));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarRuaka = new LatLng(-1.208360, 36.787500);
                mMap.addMarker(new MarkerOptions()
                        .position(aarRuaka)
                        .title("AAR Healthcare, Ruaka Clinic\n")
                        .snippet("Phone: +254731191079"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarRuaka));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();

                LatLng aarPark = new LatLng(-1.259870, 36.817880);
                mMap.addMarker(new MarkerOptions()
                        .position(aarPark)
                        .title("AAR Healthcare, Parklands Clinic\n")
                        .snippet("Phone: +254208025266"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(aarPark));
                Toast.makeText(MapsActivity.this, "Nearby Hospitals", Toast.LENGTH_LONG).show();



            }



        });


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }



    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("onLocationChanged", "entered");

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        Toast.makeText(MapsActivity.this, "Your Current Location", Toast.LENGTH_LONG).show();

        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f", latitude, longitude));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            Log.d("onLocationChanged", "Removing Location Updates");
        }
        Log.d("onLocationChanged", "Exit");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }


            @Override
            public void onInfoWindowClick(Marker marker) {
                final String description = marker.getSnippet();
                if (!TextUtils.isEmpty(description)) {
                    final Spannable span = Spannable.Factory.getInstance().newSpannable(description);
                    if (Linkify.addLinks(span, Linkify.PHONE_NUMBERS)) {
                        final URLSpan[] old = span.getSpans(0, span.length(), URLSpan.class);
                        if (old != null && old.length > 0) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(old[0].getURL()));
                            startActivity(intent);
                        }
                    }
                }



        }





}
