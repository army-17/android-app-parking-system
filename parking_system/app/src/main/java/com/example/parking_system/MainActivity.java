package com.example.parking_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnId, btnPw, btnLog;
    Dialog dialog_id, dialog_pw;
    TextView tv5;
    JoinFragment joinFragment;

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
                replaceFragment(joinFragment);

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReservationMainMenu.class);
                startActivity(intent);
            }
        });


    }

    public void showDialogId(){

        dialog_id.show();
    }

    public void showDialogPw(){
        dialog_pw.show();

    }
    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(androidx.constraintlayout.widget.R.id.constraint ,fragment).commit();
    }

}
