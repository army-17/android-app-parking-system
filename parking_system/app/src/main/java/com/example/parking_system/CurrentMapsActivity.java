package com.example.parking_system;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CurrentMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    FragmentManager fm;
    FragmentTransaction ft;
    MapFragment mapFragment;
    List<ParkingData> parkingData;
    String intentParking_seq;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_maps);

        Intent intent = getIntent();
        intentParking_seq = intent.getStringExtra("parking_seq");
        try {
            Log.d("intentParking_seq", intentParking_seq);
        }
        catch (Exception e) {
            Log.d("intentParking_seq", "not working");
            e.printStackTrace();
        }

        // GET JSON ParkingDB
        List<ParkingData> parkingData = new ArrayList<ParkingData>();
        try {
            String rst = String.valueOf(new CurrentMapsActivity.Task().execute().get());
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

                if(Integer.toString(parking_seq).equals(intentParking_seq)) {

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

                    break;
                }
            }
        }

        catch (Exception e) {
            Toast.makeText(this, "Failed to load ParkingData", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(
                R.id.fragmentContainerViewCurrent,
                CurrentMapsFragment.newInstance(parkingData))
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
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.340389, 126.733500), 13));
        gMap.getUiSettings().setZoomControlsEnabled(true);
    }
}