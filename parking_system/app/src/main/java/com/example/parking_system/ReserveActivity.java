//TODO: Make reservations here, a la AsyncTest1


package com.example.parking_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class ReserveActivity extends AppCompatActivity {

    //parking

    String parking_name, latlngString, MapTaskTestString;
    TextView parking_nameText, feeText, latlngtest, MapTaskTest;
    List<com.example.parking_system.ParkingData> parkingData;
    int fee, parking_seq;
    String totalFee;

    //member
    int member_seq = LogedMemberSeq.getLogin_member_seq(); // TODO: member_seq here, temp placeholder 7

    String member_name, car_num;
    TextView member_nameText, car_numText;

    //reservation inputs
    int yearSelected, monthSelected, daySelected;
    int startHourSelected, startMinuteSelected;
    int durationSelected;
    String startDate, endDate, formattedStartDate, formattedEndDate, lotcode;
    boolean dateFlag = false, startTimeFlag = false, durationFlag = false;
    Button dateButton, startTimeButton, durationButton;
    TextView dateText, startTimeText, durationText, totalDuration;
    CalendarView datePicker;
    TimePicker startTimePicker;
    NumberPicker durationPicker;

    Button reserveButton;
    String reserveStartDate, reserveStartTime, reserveEndDate, reserveEndTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Log.d("member_seq", Integer.toString(member_seq));
        //member gets
        //memberId = loggedMember.member_seq; // TODO: implement memberId across the app (static)
//        member_nameText = findViewById(R.id.member_nameText);
//        car_numText = findViewById(R.id.car_numText);

        Intent intent = getIntent();
        parking_name = intent.getStringExtra("parking_name");
        latlngString = intent.getStringExtra("latlng");
        MapTaskTestString = "No";

        reserveButton = findViewById(R.id.reserveButton);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일, HH시 mm분");
        SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat Hm = new SimpleDateFormat("HH:mm");

        // GET JSON ParkingDB
        List<com.example.parking_system.ParkingData> parkingData = new ArrayList<ParkingData>();
        try {
            String rst = String.valueOf(new ParkingTask().execute().get());
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

        //GET JSON MemberDB
//        List<com.example.parking_system.MemberData> memberData = new ArrayList<MemberData>();
//        try {
//            String rst = String.valueOf(new MemberTask().execute().get());
//            JSONArray jArr = new JSONArray(rst);
//
//            int member_seq = 0;
//            String member_name = "";
//            String car_num = "";
//            String member_id = "";
//            String password = "";
//
//            JSONObject json = null;
//
//            for (int i = 0; i < jArr.length(); i++) {
//                json = jArr.getJSONObject(i);
//                member_seq = Integer.parseInt(json.getString("member_seq"));
//                member_name = json.getString("member_name");
//                car_num = json.getString("car_num");
//                member_id = json.getString("member_id");
//                password = json.getString("password");
//                memberData.add(new MemberData(
//                        member_seq,
//                        member_name,
//                        car_num,
//                        member_id,
//                        password));
//            }
//        }
//
//        catch (Exception e) {
//            Toast.makeText(this, "Failed to load MemberData", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//
//        try {
//
//            ArrayList<? extends MemberData> list = (ArrayList<? extends MemberData>) memberData;
//
//            for (com.example.parking_system.MemberData data : list) {
//
//                int db_member_seq = data.getMember_seq();
//
//                String db_member_seq_string = String.valueOf(db_member_seq);
//
//                Log.d("member", db_member_seq_string);
//
//                if(memberId == db_member_seq) {
//
//                    member_name = data.getMember_name();
//                    car_num = data.getCar_num();
//                    break;
//
//                }
//
//
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

//        member_nameText.setText(member_name);
//        car_numText.setText(car_num);
        feeText = findViewById(R.id.feeText);
        parking_nameText = findViewById(R.id.parking_nameText);
//        latlngtest = findViewById(R.id.latlngtest);
//        MapTaskTest = findViewById(R.id.MapTaskTest);



        try {

            ArrayList<? extends ParkingData> list = (ArrayList<? extends ParkingData>) parkingData;

            Log.d("parkingData", parkingData.toString());

            for (com.example.parking_system.ParkingData data : list) {

                String db_parking_name = data.getParking_name();
                Log.d("getParking_name", db_parking_name);
                Log.d("getParking_name_type", db_parking_name.getClass().getName());
                Log.d("parking_name", parking_name);
                Log.d("parking_name_type", parking_name.getClass().getName());

                if(parking_name.equals(db_parking_name)) {
                    Log.d("if_result", "dummy");
                    MapTaskTestString = "Yes";
                    // TODO: get fee
                    fee = data.getFee();
                    parking_seq = data.getParking_seq();
                    String logFee = Integer.toString(fee);
                    Log.d("fee", logFee);
                    break;
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        parking_nameText.setText(parking_name);
//        latlngtest.setText(latlng);
//        MapTaskTest.setText(MapTaskTestString);


        //date, time

        datePicker = findViewById(R.id.datePicker);
        startTimePicker = findViewById(R.id.startTimePicker);
        durationPicker = findViewById(R.id.durationPicker);

        dateButton = findViewById(R.id.dateButton);
        startTimeButton = findViewById(R.id.startTimeButton);
        durationButton = findViewById(R.id.durationButton);

        dateText = findViewById(R.id.dateText);
        startTimeText = findViewById(R.id.startTimeText);
        durationText = findViewById(R.id.durationText);

        totalDuration = findViewById(R.id.totalDuration);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.VISIBLE);
                startTimePicker.setVisibility(View.INVISIBLE);
                durationPicker.setVisibility(View.INVISIBLE);
            }
        });

        startTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.INVISIBLE);
                startTimePicker.setVisibility(View.VISIBLE);
                durationPicker.setVisibility(View.INVISIBLE);
            }
        });

        durationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setVisibility(View.INVISIBLE);
                startTimePicker.setVisibility(View.INVISIBLE);
                durationPicker.setVisibility(View.VISIBLE);
            }
        });

        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                // note: month requires +1

                yearSelected = year;
                monthSelected = month+1;
                daySelected = day;

                String selectedDate = year + "년 " + (month+1) + "월 " + day + "일";

                dateText.setText(selectedDate);

                dateFlag = true;

                //Calendar toDate = Calendar.getInstance();
                //Calendar nowDate = Calendar.getInstance();
                //toDate.set(<set-year>,<set-month>,<set-day>);
                //if(!toDate.before(nowDate)) {
                //    //display your report
                //} else {
                //    // don't display the report
                //}

//                Calendar check = Calendar.getInstance();
//                Calendar now = Calendar.getInstance();
//                check.set(yearSelected, monthSelected, daySelected);
//                Log.d("calendar", check.toString());
//                Log.d("calendar", now.toString());
//                if(!check.before(now)) {
//                    Log.d("calendar", Boolean.toString(!check.before(now))); //the other way
//                    dateFlag = true;
//                } else {
//                    Toast.makeText(getApplicationContext(),"예약은 미래에만 가능합니다", Toast.LENGTH_SHORT).show();
//                }

                try {

                    if (dateFlag == true && startTimeFlag == true && durationFlag == true) {


                        Calendar calendar = Calendar.getInstance();
                        calendar.set(yearSelected, monthSelected, daySelected, startHourSelected, startMinuteSelected);

                        formattedStartDate = format1.format(calendar.getTime());
                        reserveStartDate = yMd.format(calendar.getTime());
                        reserveStartTime = Hm.format(calendar.getTime());

                        startDate = calendar.getTime().toString();
                        Log.d("startDate", startDate);
                        Log.d("reserve", reserveStartDate);
                        Log.d("reserve", reserveStartTime);

                        calendar.add(Calendar.HOUR_OF_DAY, durationSelected);

                        formattedEndDate = format1.format(calendar.getTime());
                        reserveEndDate = yMd.format(calendar.getTime());
                        reserveEndTime = Hm.format(calendar.getTime());

                        endDate = calendar.getTime().toString();
                        Log.d("endDate", endDate);
                        Log.d("reserve", reserveEndDate);
                        Log.d("reserve", reserveEndTime);

                        String totalDurationText = "시작: " + formattedStartDate + '\n' + "종료: " + formattedEndDate;

                        totalDuration.setText(totalDurationText);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });

       startTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
           @Override
           public void onTimeChanged(TimePicker timePicker, int hour, int minute) {

               startHourSelected = hour;
               String stringHour = Integer.toString(hour);
               Log.d("hour", stringHour);
               startMinuteSelected = minute;

               String selectedStartTime = hour + "시 " + minute + "분";

               startTimeText.setText(selectedStartTime);

               startTimeFlag = true;

               try {

                   if (dateFlag == true && startTimeFlag == true && durationFlag == true) {


                       Calendar calendar = Calendar.getInstance();
                       calendar.set(yearSelected, monthSelected, daySelected, startHourSelected, startMinuteSelected);

                       formattedStartDate = format1.format(calendar.getTime());
                       reserveStartDate = yMd.format(calendar.getTime());
                       reserveStartTime = Hm.format(calendar.getTime());

                       startDate = calendar.getTime().toString();
                       Log.d("startDate", startDate);
                       Log.d("reserve", reserveStartDate);
                       Log.d("reserve", reserveStartTime);

                       calendar.add(Calendar.HOUR_OF_DAY, durationSelected);

                       formattedEndDate = format1.format(calendar.getTime());
                       reserveEndDate = yMd.format(calendar.getTime());
                       reserveEndTime = Hm.format(calendar.getTime());

                       endDate = calendar.getTime().toString();
                       Log.d("endDate", endDate);
                       Log.d("reserve", reserveEndDate);
                       Log.d("reserve", reserveEndTime);

                       String totalDurationText = "시작: " + formattedStartDate + '\n' + "종료: " + formattedEndDate;

                       totalDuration.setText(totalDurationText);
                   }
               } catch(Exception e) {
                   e.printStackTrace();
               }

           }
       });

       durationPicker.setMinValue(0);
       durationPicker.setMaxValue(12);
       durationPicker.setWrapSelectorWheel(false);
       durationPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
       durationPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
           public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
               durationSelected = newVal;
               String newValString = Integer.toString(newVal);
               Log.d("newVal", newValString);

               String selectedDuration = newVal + " 시간";
               durationText.setText(selectedDuration);

               totalFee = Integer.toString(newVal*fee);
               feeText.setText("금액: " + totalFee);

               durationFlag = true;

               try {

                   if (dateFlag == true && startTimeFlag == true && durationFlag == true) {


                       Calendar calendar = Calendar.getInstance();
                       calendar.set(yearSelected, monthSelected, daySelected, startHourSelected, startMinuteSelected);

                       formattedStartDate = format1.format(calendar.getTime());
                       reserveStartDate = yMd.format(calendar.getTime());
                       reserveStartTime = Hm.format(calendar.getTime());

                       startDate = calendar.getTime().toString();
                       Log.d("startDate", startDate);
                       Log.d("reserve", reserveStartDate);
                       Log.d("reserve", reserveStartTime);

                       calendar.add(Calendar.HOUR_OF_DAY, durationSelected);

                       formattedEndDate = format1.format(calendar.getTime());
                       reserveEndDate = yMd.format(calendar.getTime());
                       reserveEndTime = Hm.format(calendar.getTime());

                       endDate = calendar.getTime().toString();
                       Log.d("endDate", endDate);
                       Log.d("reserve", reserveEndDate);
                       Log.d("reserve", reserveEndTime);

                       String totalDurationText = "시작: " + formattedStartDate + '\n' + "종료: " + formattedEndDate;

                       totalDuration.setText(totalDurationText);
                   }
               } catch(Exception e) {
                   e.printStackTrace();
               }


           }
       });

       //            int memberId, //member_seq?
        //            int reservationId,
        //            double latitude,
        //            double longitude,
        //            String name,
        //            String image,
        //            String date,
        //            String startTime,
        //            String endTime,
        //            String lotcode,
        //            String barcode

        // int reserve_seq = (auto-gen at upload)

        // int member_seq = from static value from login
        // int parking_seq = parking_seq
        // int total_fee = String totalFee
        // String parking_name = parking_name
        // String lotcode = java.util.random
        // String reserve_start_date = yMd startDate
        // String reserve_start_time = Hm startDate
        // String reserve_end_date = yMd endDate
        // String reserve_end_time = Hm endTime

       reserveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               try {

                   if (dateFlag == true && startTimeFlag == true && durationFlag == true) {

                       //random, lotcode
                       Random random = new Random();
                       int lotAlphabetSeed = random.nextInt(6) +65;
                       char lotAlphabet = (char) lotAlphabetSeed;
                       int lotNumber = random.nextInt(20) + 1;
                       lotcode = Character.toString(lotAlphabet) + Integer.toString(lotNumber);
                       Log.d("random", lotcode);

                       String[] params = new String[9];
                       params[0] = Integer.toString(member_seq);
                       params[1] = Integer.toString(parking_seq);
                       params[2] = totalFee;
                       params[3] = parking_name;
                       params[4] = lotcode;
                       params[5] = reserveStartDate;
                       params[6] = reserveStartTime;
                       params[7] = reserveEndDate;
                       params[8] = reserveEndTime;

                       String rst = String.valueOf(new ReserveTask().execute(params).get());
                       Log.d("rst", String.valueOf(params));


                       Intent intent = new Intent(getApplicationContext(), ReservationMainMenu.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       startActivity(intent);

                   } else {
                       Toast.makeText(getApplicationContext(), "설정을 확인하여 주십시오", Toast.LENGTH_SHORT).show();
                   }

               }
               catch (Exception e) {
                   Toast.makeText(getApplicationContext(),"예약 신청 에러", Toast.LENGTH_SHORT).show();
                   e.printStackTrace();
               }

           }
       });

    }

    public class ParkingTask extends AsyncTask<Void, Void, String> {

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

    public class ReserveTask extends AsyncTask<String, Void, String> {

        String sendMsg = "", receiveMsg;
        String serverIp = "https://android-parking-system.toast.paas-ta.com/reserve/insert";

        @Override
        protected String doInBackground(String... params) {
            try {

                String str;

                // TODO: Note that url already contains member_seq, and params[0] is also member_seq
                // int member_seq = from static value from login
                // int parking_seq = parking_seq
                // int total_fee = String totalFee
                // String parking_name = parking_name
                // String lotcode = java.util.random
                // String reserve_start_date = yMd startDate
                // String reserve_start_time = Hm startDate
                // String reserve_end_date = yMd endDate
                // String reserve_end_time = Hm endTime
                serverIp +=(
                        "?member_seq=" + params[0] // params[0] is member_seq
                        +"&parking_seq=" + params[1]
                        +"&total_fee=" + params[2]
                        +"&parking_name=" + params[3]
                        +"&lotcode=" + params[4]
                        +"&reserve_start_date=" + params[5]
                        +"&reserve_start_time=" + params[6]
                        +"&reserve_end_date=" + params[7]
                        +"&reserve_end_time=" + params[8]
                        );

                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST"); //use "POST" for append
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                    Log.d("receive", buffer.toString());
                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return receiveMsg;
        }
    }

//    public class MemberTask extends AsyncTask<Void, Void, String> {
//
//        String sendMsg="", receiveMsg;
//        String serverIp = "https://android-parking-system.toast.paas-ta.com/member/" + memberId; // 연결할 jsp주소
//
//        @Override
//        protected String doInBackground(Void ... voids) {
//            try {
//                String str;
//                URL url = new URL(serverIp);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                if (conn.getResponseCode() == conn.HTTP_OK) {
//                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                    BufferedReader reader = new BufferedReader(tmp);
//                    StringBuffer buffer = new StringBuffer();
//                    while ((str = reader.readLine()) != null) {
//                        buffer.append(str);
//                    }
//                    receiveMsg = buffer.toString();
//                } else {
//                    Log.i("통신 결과", conn.getResponseCode() + "에러");
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return receiveMsg;
//        }
//
//    }
}