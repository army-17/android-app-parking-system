package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReservationCurrentMenuActivity extends AppCompatActivity {

    List<ReservationData> reservationData = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    //별도 클래스에서 ReservationData 생성 필요, CookMap22, MapData.java
    //MapData에 예약 날짜, 시간, 주차공간코드, 바코드를 추가한 데이터
    //CookMap22, Frag1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_current_menu);

        ListView listview_reservation_current = (ListView) findViewById(R.id.listview_reservation_current);

        /*
        try {
            String rst = String.valueOf(new Task().execute().get()); //Task() 필요, CookMap22, Frag3
            JSONArray jArr = new JSONArray(rst);

            int reservationId = 0;
            double latitude = 0;
            double longitude = 0;
            String name = "";
            String image = "";
            String date = "";
            String time = "";
            String lotcode = "";
            String barcode = "";

            JSONObject json = null;
            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                reservationId = Integer.parseInt(json.getString("reservationId"));
                latitude = Double.parseDouble(json.getString("latitude"));
                longitude = Double.parseDouble(json.getString("longitude"));
                name = json.getString("name");
                image = json.getString("image");
                date = json.getString("date");
                time = json.getString("time");
                lotcode = json.getString("lotcode");
                barcode = json.getString("barcode");
                reservationData.add(new ReservationData(
                        reservationId,
                        latitude,
                        longitude,
                        name,
                        image,
                        date,
                        time,
                        lotcode,
                        barcode));
                names.add(name); //List에 넣을 String 리스트
            }

        } catch (Exception e) {
            e.printStackTrace();
        } */

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.expandable_list_content, names);
        listview_reservation_current.setAdapter(adapter);

        /*
        TODO:
        listview_reservation_current.setOnItemClickListener...
         */

        /*need
        ReservationData(Parcel in) <- CookMap22, MapData.java

    private int reservationId;
    private double latitude;
    private double longitude;
    private String name;
    private String image;
    private String date;
    private String time;
    private String lotcode;
    private String barcode;

         */

    }
}