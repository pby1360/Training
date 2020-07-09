package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView btn_home_timer;
    private ImageView btn_home_counter;
    private ImageView btn_home_calendar;
    private ImageView btn_home_stopwatch;
    private ImageView btn_home_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_home_timer = findViewById(R.id.btn_home_timer);
        btn_home_counter = findViewById(R.id.btn_home_counter);
        btn_home_calendar = findViewById(R.id.btn_home_calendar);
        btn_home_stopwatch = findViewById(R.id.btn_home_stopwatch);
        btn_home_setting = findViewById(R.id.btn_home_setting);

        btn_home_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TimerActivity.class);
                startActivity(intent);
            }
        });

        btn_home_stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StopwatchActivity.class);
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
					Intent intent=new Intent(MainActivity.this,CalendarActivity.class);
					startActivity(intent);
				}
			});

		btn_home_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), R.string.prepare, Toast.LENGTH_SHORT).show();
            }
        });
		
    }
}

