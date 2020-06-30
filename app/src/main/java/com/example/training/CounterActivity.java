package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class CounterActivity extends AppCompatActivity {
	
	private TextView tv_counter_sumSet;
	private TextView tv_counter_cnt;
	private TextView tv_counter_sumCnt;
	private TextView tv_counter_plus;
	private TextView tv_counter_minus;
	private TextView tv_counter_comSet;
	private ImageView iv_counter_reset;
    private Button btn_counter_record;
	
	private int count = 0;
	private int setCnt = 0;
	private int sumCnt = 0;

	private long   backPressedTime = 0;
	private final long FINISH_INTERVAL_TIME = 2000;

	@Override
	public void onBackPressed() {
		long tempTime = System.currentTimeMillis();
		long intervalTime = tempTime - backPressedTime;

		if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
		{
			super.onBackPressed();
		}
		else
		{
			backPressedTime = tempTime;
			Toast.makeText(getApplicationContext(), "한번 더 누르면 홈 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
		}

	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
		
		tv_counter_sumSet = findViewById(R.id.tv_counter_sumSet);
		tv_counter_cnt  = findViewById(R.id.tv_counter_cnt);
		tv_counter_sumCnt = findViewById(R.id.tv_counter_sumCnt);
		tv_counter_plus = findViewById(R.id.tv_counter_plus);
		tv_counter_minus = findViewById(R.id.tv_counter_minus);
		tv_counter_comSet = findViewById(R.id.tv_counter_comSet);
		iv_counter_reset = findViewById(R.id.iv_counter_reset);
        btn_counter_record = findViewById(R.id.btn_counter_record);

		tv_counter_plus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					count++;
					tv_counter_cnt.setText(Integer.toString(count));
				}
		});

		tv_counter_minus.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					if(count !=0) {
						count = count - 1;
						tv_counter_cnt.setText(Integer.toString(count));
					}

				}
		});
		
		tv_counter_comSet.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = sumCnt + count;
					count = 0;
					setCnt++;
					tv_counter_cnt.setText(Integer.toString(count));
					tv_counter_sumSet.setText(setCnt + " SET");
					tv_counter_sumCnt.setText(Integer.toString(sumCnt));
				}
		});
		
		iv_counter_reset.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = 0;
					count = 0;
					setCnt = 0;				
					tv_counter_sumSet.setText("0 SET");
					tv_counter_cnt.setText("0");
					tv_counter_sumCnt.setText("0");
				}
		});
    }
}
