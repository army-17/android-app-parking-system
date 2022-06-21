package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Button join = (Button) findViewById(R.id.join);

        EditText[] et = new EditText[4];
        et[0]= findViewById(R.id.name);
        et[1]= findViewById(R.id.carNum);
        et[2]= findViewById(R.id.memberId);
        et[3]= findViewById(R.id.password);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] params = new String[4];
                    for(int i=0; i<params.length; i++)
                        params[i] = et[i].getText().toString().trim();
                    Task task = new Task();
                    task.execute(params);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);


                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public class Task extends AsyncTask<String, Void, String> {

        String sendMsg="", receiveMsg;
        String serverIp = "https://android-parking-system.toast.paas-ta.com/member/signup/"; // 연결할 jsp주소

        @Override
        protected String doInBackground(String ... params) {
            try {
                String str;
                serverIp += ("?member_name="+params[0] + "&car_num=" + params[1] + "&member_id=" + params[2] + "&password=" + params[3]);

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
}
