package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectedReservationActivity extends AppCompatActivity {

    String reserve_seq,
            member_seq,
            parking_seq,
            total_fee,
            parking_name,
            lotcode,
            reserve_start_date,
            reserve_start_time,
            reserve_end_date,
            reserve_end_time;

    Intent intent;

    //                        parking_seq,
    //                        parking_name,
    //                        address,
    //                        fee,
    //                        latitude,
    //                        longitude,
    //                        img_save_path,
    //                        remain_cnt,
    //                        is_res_yn

    String parking_seqDB, parking_nameDB, addressDB;
    Double lat, lng;
    LatLng latlng;

    TextView selectedParkingNameText,
            selectedAddressText,
            selectedLotcodeText,
            selectedStartDateText,
            selectedStartTimeText,
            selectedEndDateText,
            selectedEndTimeText,
            selectedTotalFeeText;

    Button buttonSeeMap, buttonCancelReservation;

    ArrayList<ParkingData> listParkingData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_reservation);

        Intent intent = getIntent();

        reserve_seq = intent.getStringExtra("intentReserve_seq");
        member_seq = intent.getStringExtra("intentMember_seq");
        parking_seq = intent.getStringExtra("intentParking_seq");
        total_fee = intent.getStringExtra("intentTotal_fee");
        parking_name = intent.getStringExtra("intentParking_name");
        lotcode = intent.getStringExtra("intentLotcode");
        reserve_start_date = intent.getStringExtra("intentReserve_start_date");
        reserve_start_time = intent.getStringExtra("intentReserve_start_time");
        reserve_end_date = intent.getStringExtra("intentReserve_end_date");
        reserve_end_time = intent.getStringExtra("intentReserve_end_time");

        selectedParkingNameText = findViewById(R.id.selectedParkingNameText);
        selectedAddressText = findViewById(R.id.selectedAddressText);
        selectedLotcodeText = findViewById(R.id.selectedLotcodeText);
        selectedStartDateText = findViewById(R.id.selectedStartDateText);
        selectedStartTimeText = findViewById(R.id.selectedStartTimeText);
        selectedEndDateText = findViewById(R.id.selectedEndDateText);
        selectedEndTimeText = findViewById(R.id.selectedEndTimeText);
        selectedTotalFeeText = findViewById(R.id.selectedTotalFeeText);

        buttonSeeMap = findViewById(R.id.buttonSeeMap);
        buttonCancelReservation = findViewById(R.id.buttonCancelReservation);

        // GET JSON ParkingDB

        List<ParkingData> parkingData = new ArrayList<ParkingData>();
        listParkingData = new ArrayList<ParkingData>();
        try {
            String rst = String.valueOf(new SelectedReservationActivity.Task().execute().get());
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
                Log.d("address",address);
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

                listParkingData.add(new ParkingData(
                        parking_seq,
                        parking_name,
                        address,
                        fee,
                        latitude,
                        longitude,
                        img_save_path,
                        remain_cnt,
                        is_res_yn
                ));
            }


        }

        catch (Exception e) {
            Toast.makeText(this, "Failed to load ParkingData", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // End GET JSON ParkingDB

        // Extract correct ParkingData

        try {

            for (int i = 0; i < listParkingData.size(); i++ ) {

                parking_seqDB = Integer.toString(listParkingData.get(i).getParking_seq());

                if (parking_seqDB.equals(parking_seq)) {

                    Log.d("parking_seq check", parking_seqDB);
                    Log.d("parking_seq check", parking_seq);

                    parking_nameDB = listParkingData.get(i).getParking_name();
                    addressDB = listParkingData.get(i).getAddress();
                    Log.d("address", listParkingData.get(i).getAddress());
                    lat = listParkingData.get(i).getLatitude();
                    lng = listParkingData.get(i).getLongitude();
                    latlng = new LatLng(lat, lng);

                    intent = new Intent(getApplicationContext(),  CurrentMapsActivity.class);
                    String sendParking_seq = parking_seqDB;
                    Log.d("intent", sendParking_seq);
                    intent.putExtra("parking_seq", sendParking_seq);
                    Log.d("intent", intent.getStringExtra(sendParking_seq));

                    break;

                }

            }

        }

        catch (Exception e) {
            Toast.makeText(this,"for loop failure", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // End Extract correct ParkingData

        // Insert ParkingData to TextViews

        selectedParkingNameText.setText(parking_nameDB);
        selectedAddressText.setText(addressDB);
        selectedLotcodeText.setText(lotcode);
        selectedStartDateText.setText("시작 날짜: " + reserve_start_date);
        selectedStartTimeText.setText("시작 시간: " + reserve_start_time);
        selectedEndDateText.setText("종료 날짜: " + reserve_end_date);
        selectedEndTimeText.setText("종료 시간: " +reserve_end_time);
        selectedTotalFeeText.setText(total_fee + " 원");

        Intent finalIntent = intent;
        buttonSeeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(finalIntent);


            }
        });

    }

    //TODO: buttons: cancel






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
    //예약취소
    public class Task2 extends AsyncTask<String, Void, String> {

        String sendMsg="", receiveMsg;
        String serverIp = "https://android-parking-system.toast.paas-ta.com/reserve/cancel"; // 연결할 jsp주소

        @Override
        protected String doInBackground(String... param) {
            try {

                String str;
                serverIp += "?reserve_seq=" + param;

                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("PUT");  //수정하고자 하면 POST, PUT-> 데이터를 DB 올리겠다.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(sendMsg);
                osw.flush();

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
}