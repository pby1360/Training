package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.os.SystemClock;
import android.view.View.*;
import android.os.Handler;
import android.os.Message;

public class StopwatchActivity extends AppCompatActivity {
	
	private TextView tv_sw_time;
    private TextView tv_sw_milSec;
	private ImageView iv_sw_reset;
	private ImageView iv_sw_start;
	private ImageView iv_sw_stop;
	private Button btn_sw_record;
    private long myStartTime;
	private boolean isPlay=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
		
		tv_sw_time = findViewById(R.id.tv_sw_time);
		tv_sw_milSec = findViewById(R.id.tv_sw_milSec);
		iv_sw_start = findViewById(R.id.iv_sw_start);
		iv_sw_reset = findViewById(R.id.iv_sw_reset);
		iv_sw_stop = findViewById(R.id.iv_sw_stop);
		btn_sw_record = findViewById(R.id.btn_sw_record);
        
        iv_sw_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
					if (isPlay) {
						myTimer.removeMessages(0);
                        iv_sw_start.setImageResource(R.drawable.icon_play_custom);
						isPlay = false;
					} else {
						if (myStartTime == 0){
							myStartTime = SystemClock.elapsedRealtime();
						}
						myTimer.sendEmptyMessage(0);
						iv_sw_start.setImageResource(R.drawable.icon_pause_custom);
						isPlay = true;
					}
                    
                }
         });
		         
    }
    
	Handler myTimer = new Handler() {
		public void handleMessage(Message msg) {
			tv_sw_time.setText(getTimeOut());
			tv_sw_milSec.setText(getMilSec());
			myTimer.sendEmptyMessage(0);
		}
	};
	String getTimeOut() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myStartTime;
        String time = String.format("%02d : %02d : %02d", (outTime / 1000 / 3600), (outTime / 1000 / 60) % 60, (outTime / 1000) % 60);
        return time;
	}
	String getMilSec() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myStartTime;
        String time = String.format("%02d", (outTime / 10) % 100);
        return time;
    }
}
