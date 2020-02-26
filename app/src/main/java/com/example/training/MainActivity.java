package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_home_timer;
    private Button btn_home_counter;
    private Button btn_home_calendar;
    private Button btn_home_routine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_home_timer = findViewById(R.id.btn_home_timer);
        btn_home_counter = findViewById(R.id.btn_home_counter);
        btn_home_calendar = findViewById(R.id.btn_home_calendar);
        btn_home_routine = findViewById(R.id.btn_home_routine);

        btn_home_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TimerActivity.class);
                startActivity(intent);
            }
        });
		btn_home_counter.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(MainActivity.this,CounterActivity.class);
					startActivity(intent);
				}
			});
			
		btn_home_calendar.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(MainActivity.this,CounterActivity.class);
					startActivity(intent);
				}
			});
		
    }
}

