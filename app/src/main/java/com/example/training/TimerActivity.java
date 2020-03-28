package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class TimerActivity extends AppCompatActivity {
	
	private TextView tv_timer_time;
	private ImageView iv_timer_reset;
	private ImageView iv_timer_start;
	private ImageView iv_timer_timeSetting;
	private Switch sch_timer_con1;
	private Switch sch_timer_con2;
	private ImageView iv_timer_con1Up;
	private ImageView iv_timer_con1Down;
	private EditText et_timer_con1Time;
	private Spinner sn_timer_con1Unit;
	private int secSum;

	private TimePopUpDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
		
		iv_timer_start = findViewById(R.id.iv_timer_start);
		iv_timer_reset = findViewById(R.id.iv_timer_reset);
		iv_timer_timeSetting = findViewById(R.id.iv_timer_timeSetting);

		iv_timer_timeSetting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog = new TimePopUpDialog(TimerActivity.this, new TimePopUpDialog.TimePopUpListener() {
					@Override
					public void clickSubmitBtn(int hour, int min, int sec) {
						secSum = (hour * 3600) + (min * 60) +(sec);
						tv_timer_time.setText(hour + ":" + min + ":" + sec); 
					}
				});
				dialog.show();

			}
		});
		
		iv_timer_reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_timer_time.setText("00:00:00");					
			}
		});
			
    }
}
