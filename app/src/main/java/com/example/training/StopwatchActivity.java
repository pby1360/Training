package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class StopwatchActivity extends AppCompatActivity {
	
	private TextView tv_sw_time;
	private ImageView iv_sw_reset;
	private ImageView iv_sw_start;
	private ImageView iv_sw_stop;
	private Button btn_sw_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
		
		tv_sw_time = findViewById(R.id.tv_sw_time);
		iv_sw_start = findViewById(R.id.iv_sw_start);
		iv_sw_reset = findViewById(R.id.iv_sw_reset);
		iv_sw_stop = findViewById(R.id.iv_sw_stop);
		btn_sw_record = findViewById(R.id.btn_sw_record);
    }
}
