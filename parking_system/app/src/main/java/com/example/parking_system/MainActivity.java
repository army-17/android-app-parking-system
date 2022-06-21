package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnId, btnPw, btnLog;
    Dialog dialog_id, dialog_pw;
    TextView btnJoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJoin = findViewById(R.id.textView5);

        btnId = findViewById(R.id.btnId);
        btnPw = findViewById(R.id.btnPw);
        btnLog = findViewById(R.id.btnLog);


        dialog_id = new Dialog(MainActivity.this);
        dialog_id.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_id.setContentView(R.layout.finding_id);

        dialog_pw = new Dialog(MainActivity.this);
        dialog_pw.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_pw.setContentView(R.layout.finding_password);

        LogedMemberSeq.setLogin_member_seq(0);
        Log.d("LogedMemberSeq", Integer.toString(LogedMemberSeq.getLogin_member_seq()));


        //아이디 찾기
        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogId();
                Button btn5 = dialog_id.findViewById(R.id.PwClose);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_id.dismiss();
                    }
                });

            }
        });


        //비밀번호 찾기
        btnPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogPw();

                Button pwClose = dialog_pw.findViewById(R.id.PwClose);
                pwClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_pw.dismiss();
                    }
                });


            }
        });


        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);

            }
        });

        EditText[] logs = new EditText[2];

        logs[0] = findViewById(R.id.logId);
        logs[1] = findViewById(R.id.logPw);

        //로그인하기

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

                    int sequence = Integer.parseInt(rst);
                    LogedMemberSeq.setLogin_member_seq(sequence);
                    Log.d("LogedMemberSeq", Integer.toString(LogedMemberSeq.getLogin_member_seq()));
                    int temp = LogedMemberSeq.getLogin_member_seq();

                    if(LogedMemberSeq.getLogin_member_seq() != 0 ){    
                        Intent intent2 = new Intent(getApplicationContext(), ReservationMainMenu.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                  } else{
                        Toast.makeText(getApplicationContext(),"해당 아이디로 로그인할 수 없습니다!", Toast.LENGTH_SHORT).show();
                    }

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

                    receiveMsg="0";

                    Log.i("통신 결과", conn.getResponseCode() + "에러");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    //아이디찾기 다이얼로그
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
                    String rst = String.valueOf(new Task(serverIp, paramList).execute(resultId.getText().toString().trim()).get());

                    if(rst.length()==0 || rst == null){
                        String temp2 = "해당 아이디가 존재하지 않습니다." +rst ;
                        resultId.setText(temp2);

                    } else {
                        String temp = "당신의 아이디는 " + rst;
                        resultId.setText(temp);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //비밀번호 찾기 다이얼로그
    public void showDialogPw(){
        dialog_pw.show();

        EditText[] findPws = new EditText[3];
        findPws[0] = dialog_pw.findViewById(R.id.findPw_Name);
        findPws[1] = dialog_pw.findViewById(R.id.findPw_Car);
        findPws[2] = dialog_pw.findViewById(R.id.findPw_Id);

        Button findPw = dialog_pw.findViewById(R.id.findPw);
        TextView findPw_Result = dialog_pw.findViewById(R.id.findPw_Result);

        findPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] key_list = new String[3];
                    key_list[0] = "member_name=";
                    key_list[1] = "&car_num=";
                    key_list[2] = "&member_id=";
                    String serverIp = "https://android-parking-system.toast.paas-ta.com/member/findPwd?";
                    String paramList = "";
                    for (int i = 0; i < 3; i++){
                        paramList += key_list[i];
                        paramList += findPws[i].getText().toString().trim();
                    }
                    String rst = String.valueOf(new Task(serverIp, paramList).execute(findPw_Result.getText().toString().trim()).get());

                    if(rst.length()==0 || rst == null){
                        String temp2 = "해당 ID의 비밀번호가 존재하지 않습니다." +rst ;
                        findPw_Result.setText(temp2);

                    } else {
                        String temp = "당신의 비밀번호는 " + rst;
                        findPw_Result.setText(temp);


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }



}
