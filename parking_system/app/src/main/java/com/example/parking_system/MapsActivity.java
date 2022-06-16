package com.example.parking_system;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FragmentManager fm;
    FragmentTransaction ft;
    MapFragment mapFragment;
    List<com.example.parking_system.ParkingData> parkingData;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);




        List<ParkingData> parkingData = new ArrayList<ParkingData>();
        try {
            String rst = String.valueOf(new Task().execute().get());
            JSONArray jArr = new JSONArray(rst);

            int parking_seq = 0;
            String parking_name = "";
            String address = "";
            int fee = 0;
            double latitude = 0;
            double longitude = 0;
            String img_save_path = "";
            int remain_cnt = 0;
            String is_res_yn = "";

            JSONObject json = null;

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                parking_seq = Integer.parseInt(json.getString("parking_seq"));
                parking_name = json.getString("parking_name");
                address = json.getString("address");
                fee = Integer.parseInt(json.getString("fee"));
                latitude = Double.parseDouble(json.getString("latitude"));
                longitude = Double.parseDouble(json.getString("longitude"));
                img_save_path = json.getString("img_save_path");
                remain_cnt = Integer.parseInt(json.getString("remain_cnt"));
                is_res_yn = json.getString("is_res_yn");
                parkingData.add(new ParkingData(
                        parking_seq,
                        parking_name,
                        address,
                        fee,
                        latitude,
                        longitude,
                        img_save_path,
                        remain_cnt,
                        is_res_yn));
            }
        }

        catch (Exception e) {
            Toast.makeText(this, "Failed to load ParkingData", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }



        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(
                R.id.fragmentContainerView,
                MapsFragment.newInstance(parkingData))
                .commit();

    }

    public class Task extends AsyncTask<Void, Void, String> {

        String sendMsg="", receiveMsg;
        String serverIp = "https://android-parking-system.toast.paas-ta.com/parking/list"; // 연결할 jsp주소

        @Override
        protected String doInBackground(Void ... voids) {
            try {
                String str;
                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.568256, 126.897240), 15));
        gMap.getUiSettings().setZoomControlsEnabled(true);
    }
}