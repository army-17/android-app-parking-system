package com.example.parking_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    private MapView mapView = null;

    //TODO: ParkingData
    public static MapsFragment newInstance(List<com.example.parking_system.ParkingData> parkingData) {

        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        //TODO: ParkingData
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
        View view = inflater.inflate(R.layout.fragment_maps, null);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.340389, 126.733500), 13));

        //ui settings
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);

        Bundle bundle = getArguments();

        try {
            ArrayList<? extends com.example.parking_system.ParkingData> list = bundle.getParcelableArrayList("list");

            for (com.example.parking_system.ParkingData data : list) {

                LatLng latlng = new LatLng(data.getLatitude(), data.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions()
                        //.snippet(data.getImg_save_path()) //disabled until image is figured out
                        .snippet("예약하시려면 누르십시오")
                        .position(latlng)
                        .title(data.getParking_name())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.parking_resize)));
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                alertDialogBuilder
                        .setTitle(R.string.reserve_dialog_title)
                        .setMessage(R.string.reserve_dialog_box)
                        .setPositiveButton(R.string.reserve_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                Bundle bundle = new Bundle();
//                                bundle.putString("parking_name", marker.getTitle().toString());
//                                bundle.putString("latlng", marker.getPosition().toString());
                                //bundle.putString("img_save_path", marker.getSnippet());
                                //이 두개를 예약화면에서 주차장DB에 대조해서 주차장 정보를 새로 불러냅니다
                                //데이터를 마커에 저장할 수 있는 방법을 아시면 마커에 번들 저장 등의 방법으로 재구축

                               /* Intent intent = new Intent(getActivity(), ReserveActivity.class);
                                intent.putExtra("parking_name", marker.getTitle().toString());
                                intent.putExtra("latlng", marker.getPosition().toString());
                                startActivity(intent); */
                            }
                        })
                        .setNegativeButton(R.string.reserve_no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog reserveDialog = alertDialogBuilder.create();

                reserveDialog.show();

            }
        });

    }



}