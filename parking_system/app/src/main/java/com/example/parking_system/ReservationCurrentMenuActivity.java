package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReservationCurrentMenuActivity extends AppCompatActivity {

    ListView listview_reservation_current;

    int member_seq; // TODO: member_seq here
    // TODO: get length() of reserveData, and put that as i in i.e. String[i] = listParkingNameText
    String[] listParkingName, listStartDate, listStartTime, listLotcode;
    ReserveData[] listReserveData;

    //ArrayList<String> names = new ArrayList<>();
    //별도 클래스에서 ReserveData 생성 필요, CookMap22, MapData.java
    //MapData에 예약 날짜, 시간, 주차공간코드, 바코드를 추가한 데이터
    //CookMap22, Frag1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_current_menu);

        // GET JSON ReserveDB
        List<com.example.parking_system.ReserveData> reserveData = new ArrayList<ReserveData>();

        try {
            String rst = String.valueOf(new ReserveTask().execute().get());
            JSONArray jArr = new JSONArray(rst);

            int reserve_seq = 0;
            int member_seq = 0;
            int parking_seq = 0;
            int total_fee = 0;
            String parking_name = "";
            String lotcode = "";
            String reserve_start_date = "";
            String reserve_start_time = "";
            String reserve_end_date = "";
            String reserve_end_time = "";

            JSONObject json = null;

            for (int i = 0; i < jArr.length(); i++) {
                json = jArr.getJSONObject(i);
                reserve_seq = Integer.parseInt(json.getString("reserve_seq"));
                member_seq = Integer.parseInt(json.getString("member_seq"));
                parking_seq = Integer.parseInt(json.getString("parking_seq"));
                total_fee = Integer.parseInt(json.getString("total_fee"));
                parking_name = json.getString("parking_name");
                lotcode = json.getString("lotcode");
                reserve_start_date = json.getString("reserve_start_date");
                reserve_start_time = json.getString("reserve_start_time");
                reserve_end_date = json.getString("reserve_end_date");
                reserve_end_time = json.getString("reserve_end_time");

                reserveData.add(new ReserveData(
                        reserve_seq,
                        member_seq,
                        parking_seq,
                        total_fee,
                        parking_name,
                        lotcode,
                        reserve_start_date,
                        reserve_start_time,
                        reserve_end_date,
                        reserve_end_time
                ));

                listReserveData[i] = new ReserveData(
                        reserve_seq,
                        member_seq,
                        parking_seq,
                        total_fee,
                        parking_name,
                        lotcode,
                        reserve_start_date,
                        reserve_start_time,
                        reserve_end_date,
                        reserve_end_time
                );


                listParkingName[i] = parking_name;
                listStartDate[i] = reserve_start_date;
                listStartTime[i] = reserve_start_time;
                listLotcode[i] = lotcode;

            }
        }

        catch (Exception e) {
            Toast.makeText(this, "Failed to load ReserveData", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        // GET JSON ReserveDB end

        ReservationListAdapter adapter = new ReservationListAdapter(this,
                listParkingName,
                listStartDate,
                listStartTime,
                listLotcode);

        listview_reservation_current = (ListView) findViewById(R.id.listview_reservation_current);
        listview_reservation_current.setAdapter(adapter);

        listview_reservation_current.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                try {

                    for (int i = 0; i <= position; i++) {

                        Intent intent = new Intent(view.getContext(), SelectedReservationActivity.class);

                        String intentReserve_seq = Integer.toString(listReserveData[i].getReserve_seq());
                        String intentMember_seq = Integer.toString(listReserveData[i].getMember_seq());
                        String intentParking_seq = Integer.toString(listReserveData[i].getParking_seq());
                        String intentTotal_fee = Integer.toString(listReserveData[i].getTotal_fee());
                        String intentParking_name = listReserveData[i].getParking_name();
                        String intentLotcode = listReserveData[i].getLotcode();
                        String intentReserve_start_date = listReserveData[i].getReserve_start_date();
                        String intentReserve_start_time = listReserveData[i].getReserve_start_time();
                        String intentReserve_end_date = listReserveData[i].getReserve_end_date();
                        String intentReserve_end_time = listReserveData[i].getReserve_end_time();

                        Log.d("intent", intentReserve_seq);
                        Log.d("intent", intentMember_seq);
                        Log.d("intent", intentParking_seq);
                        Log.d("intent", intentTotal_fee);
                        Log.d("intent", intentParking_name);
                        Log.d("intent", intentLotcode);
                        Log.d("intent", intentReserve_start_date);
                        Log.d("intent", intentReserve_start_time);
                        Log.d("intent", intentReserve_end_date);
                        Log.d("intent", intentReserve_end_time);

                        intent.putExtra("intentReserve_seq", intentReserve_seq);
                        intent.putExtra("intentMember_seq", intentMember_seq);
                        intent.putExtra("intentParking_seq", intentParking_seq);
                        intent.putExtra("intentTotal_fee", intentTotal_fee);
                        intent.putExtra("intentParking_name", intentParking_name);
                        intent.putExtra("intentLotcode", intentLotcode);
                        intent.putExtra("intentReserve_start_date", intentReserve_start_date);
                        intent.putExtra("intentReserve_start_time", intentReserve_start_time);
                        intent.putExtra("intentReserve_end_date", intentReserve_end_date);
                        intent.putExtra("intentReserve_end_time", intentReserve_end_time);

                        startActivity(intent);

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "for loop failure", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }

    public class ReserveTask extends AsyncTask<Void, Void, String> {

        String sendMsg="", receiveMsg;
        String serverIp = "https://android-parking-system.toast.paas-ta.com/reserve/list" + member_seq; // 연결할 jsp주소

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

    public class ReservationListAdapter extends ArrayAdapter<String> {

        private final Activity context;
        private final String[] listParkingName;
        private final String[] listStartDate;
        private final String[] listStartTime;
        private final String[] listLotcode;

        public ReservationListAdapter(
                Activity context,
                String[] listParkingName,
                String[] listStartDate,
                String[] listStartTime,
                String[] listLotcode) {
            super(context, R.layout.reservations_list, listParkingName);

            this.context = context;
            this.listParkingName = listParkingName;
            this.listStartDate = listStartDate;
            this.listStartTime = listStartTime;
            this.listLotcode = listLotcode;

        }

        public View getView (int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.reservations_list, null, true);

            TextView listParkingNameText = (TextView) rowView.findViewById(R.id.listParkingNameText);
            TextView listStartDateText = (TextView) rowView.findViewById(R.id.listStartDateText);
            TextView listStartTimeText = (TextView) rowView.findViewById(R.id.listStartTimeText);
            TextView listLotcodeText = (TextView) rowView.findViewById(R.id.listLotcodeText);

            listParkingNameText.setText(listParkingName[position]);
            listStartDateText.setText(listStartDate[position]);
            listStartTimeText.setText(listStartTime[position]);
            listLotcodeText.setText(listLotcode[position]);

            return rowView;
        };
    }
}