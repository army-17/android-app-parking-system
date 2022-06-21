package com.example.parking_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class CurrentMapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    private MapView mapView = null;
    LatLng latlng;

    public static CurrentMapsFragment newInstance(List<ParkingData> parkingData) {

        CurrentMapsFragment fragment = new CurrentMapsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) parkingData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_maps, null);
        mapView = (MapView)view.findViewById(R.id.map);
        mapView.getMapAsync(this);

        //Button reserveButton = (Button) view.findViewById(R.id.reserveButton);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //adding markers from ParkingDB

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //ui settings
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.340389, 126.733500), 13));

        Bundle bundle = getArguments();



        try {
            ArrayList<? extends com.example.parking_system.ParkingData> list = bundle.getParcelableArrayList("list");

            for (com.example.parking_system.ParkingData data : list) {

                latlng = new LatLng(data.getLatitude(), data.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions()
                        //.snippet(data.getImg_save_path()) //disabled until image is figured out
                        .snippet(data.getAddress()) //TODO: show Time maybe?
                        .position(latlng)
                        .title(data.getParking_name())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_resize)));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }









    }


}