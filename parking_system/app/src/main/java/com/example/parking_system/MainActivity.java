package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnId, btnPw, btnLog;
    Dialog dialog_id, dialog_pw;
    TextView tv5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv5 = findViewById(R.id.textView5);

        btnId = findViewById(R.id.btnId);
        btnPw = findViewById(R.id.btnPw);
        btnLog = findViewById(R.id.btnLog);


        dialog_id = new Dialog(MainActivity.this);
        dialog_id.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_id.setContentView(R.layout.finding_id);

        dialog_pw = new Dialog(MainActivity.this);
        dialog_pw.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_pw.setContentView(R.layout.finding_password);



        //아이디 찾기
        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogId();

            }
        });

        btnPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogPw();


            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);

            }
        });

        EditText[] logs = new EditText[2];

        logs[0] = findViewById(R.id.logId);
        logs[1] = findViewById(R.id.logPw);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String[] key_list = new String[2];
                    key_list[0] = "member_id=";
                    key_list[1] = "&password=";
                    String serverIp = "https://android-parking-system.toast.paas-ta.com/member/login?";
                    String paramList = "";
                    for (int i = 0; i < 2; i++){
                        paramList += key_list[i];
                        paramList += logs[i].getText().toString().trim();
                    }
                    String rst = String.valueOf(new Task(serverIp, paramList).execute().get());

                    Log.d("member", rst);
                    JSONObject json = new JSONObject(rst);
                    int member_seq = Integer.parseInt(json.getString("member_seq"));
                    Intent intent = new Intent(getApplicationContext(), ReservationMenuActivity.class);
                    startActivity(intent);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });


    }
    public class Task extends AsyncTask<String, Void, String> {


        public Task(String serverIp, String params) {

            this.serverIp = serverIp;
            this.params = params;

        }

        String sendMsg="", receiveMsg;
        //String serverIp = "https://android-parking-system.toast.paas-ta.com/member/login/"; // 연결할 jsp주소
        String serverIp = this.serverIp;
        String params;

        @Override
        protected String doInBackground(String ... params) {

            try {
                String str;
                serverIp += this.params;

                URL url = new URL(serverIp);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");  //수정하고자 하면 POST, PUT-> 데이터를 DB 올리겠다.
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
    public void showDialogId(){
        dialog_id.show();

        EditText[] findIds = new EditText[2];
        findIds[0] = dialog_id.findViewById(R.id.findId_Name);
        findIds[1] = dialog_id.findViewById(R.id.findId_Car);
        TextView resultId = dialog_id.findViewById(R.id.findId_Result);

        Button findMemberId = dialog_id.findViewById(R.id.findMemberId);
        findMemberId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] key_list = new String[2];
                    key_list[0] = "member_name=";
                    key_list[1] = "&car_num=";
                    String serverIp = "https://android-parking-system.toast.paas-ta.com/member/findId?";
                    String paramList = "";
                    for (int i = 0; i < 2; i++){
                        paramList += key_list[i];
                        paramList += findIds[i].getText().toString().trim();
                    }
                    String rst = String.valueOf(new Task(serverIp, paramList).execute().get());

                    JSONObject json = new JSONObject(rst);
                    String id = json.getString("member_id");
                    Log.d("id", rst);
                    String temp = "당신의 아이디는 "+id;
                    resultId.setText(temp);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void showDialogPw(){
        dialog_pw.show();

    }



}
