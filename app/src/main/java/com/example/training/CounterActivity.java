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
	
	private int count = 0;
	private int setCnt = 0;
	private int sumCnt = 0;
	
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
		
		tv_counter_plus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					count++;
					tv_counter_cnt.setText(count);
				}
		});
		
		


		tv_counter_minus.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					count = count != 0 ? count-- : 0;
					tv_counter_cnt.setText(count);
				}
		});
		
		tv_counter_comSet.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = sumCnt + count;
					count = 0;
					setCnt++;
					tv_counter_sumSet.setText(setCnt + " SET");
					tv_counter_sumCnt.setText(sumCnt);
				}
		});
		
		iv_counter_reset.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = 0;
					count = 0;
					setCnt = 0;				
					tv_counter_sumSet.setText("0 SET");
					tv_counter_cnt.setText(0);
					tv_counter_sumCnt.setText(0);
				}
		});
    }
}
