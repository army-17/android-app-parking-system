package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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



    }
}