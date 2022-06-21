package com.example.parking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReservationMainMenu extends AppCompatActivity {

    Button button_reservation_current, button_reserve_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_main_menu);

        button_reservation_current = findViewById(R.id.button_reservation_current);
        button_reserve_now = findViewById(R.id.button_reserve_now);

        button_reservation_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: populate listview from here? or at RCMA.class?

                Intent intent = new Intent(getApplicationContext(), ReservationCurrentMenuActivity.class);
                startActivity(intent);
            }
        });

        button_reserve_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}