package com.example.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.SystemClock;
import android.view.View.*;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class StopwatchActivity extends AppCompatActivity {

    private Context context;
    private TextView tv_sw_time;
    private TextView tv_sw_milSec;
    private ImageView iv_sw_reset;
    private ImageView iv_sw_start;
    private ImageView iv_sw_stop;
    private Button btn_sw_record;
    private ScrollView sv_sw_record;
    private long myStartTime;
    private long myBaseTime;
    private boolean isPlay = false;
    private ArrayList<RecordDictionary> recordList;
    private RecordCustomAdapter recordAdapter;
    private int countNo = 1;

    private long   backPressedTime = 0;
    private final long FINISH_INTERVAL_TIME = 2000;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            myTimer.removeMessages(0);
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
        setContentView(R.layout.activity_stopwatch);

        context = getBaseContext();
        sv_sw_record = findViewById(R.id.sv_sw_record);
        tv_sw_time = findViewById(R.id.tv_sw_time);
        tv_sw_milSec = findViewById(R.id.tv_sw_milSec);
        iv_sw_start = findViewById(R.id.iv_sw_start);
        iv_sw_reset = findViewById(R.id.iv_sw_reset);
        iv_sw_stop = findViewById(R.id.iv_sw_stop);
        btn_sw_record = findViewById(R.id.btn_sw_record);

        final RecyclerView recyclerView = findViewById(R.id.rv_sw_record);
        LinearLayoutManager recordManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recordManager);

        recordList = new ArrayList<>();
        recordAdapter = new RecordCustomAdapter(recordList);
        recyclerView.setAdapter(recordAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                recordManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
		
		iv_sw_reset.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					recordList.clear();
                    recordAdapter.notifyDataSetChanged();
                    countNo = 1;
				}
		});

        btn_sw_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlay == true) {
                    String current_time = getTimeOut() + " " + getMilSec();
                    RecordDictionary data = new RecordDictionary(countNo + " ", current_time);
                    recordList.add(data);
                    recordAdapter.notifyDataSetChanged();
                    countNo++;
                } else {
                    Toast.makeText(getApplicationContext(), "스톱워치를 시작하세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        iv_sw_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTimer.removeMessages(0);
                myStartTime = 0;
                myBaseTime = 0;

                tv_sw_time.setText("00 : 00 : 00");
                tv_sw_milSec.setText("00");
                iv_sw_start.setImageResource(R.drawable.icon_play_custom);
                isPlay = false;
            }
        });


        iv_sw_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay == false) {
                    if (myStartTime == 0) {
                    }
                    myBaseTime = SystemClock.elapsedRealtime();
                    myTimer.sendEmptyMessage(0);
                    iv_sw_start.setImageResource(R.drawable.icon_pause_custom);
                    isPlay = true;
                } else {
                    long now = SystemClock.elapsedRealtime();
                    myTimer.removeMessages(0);
                    myStartTime = now - myBaseTime + myStartTime;
                    iv_sw_start.setImageResource(R.drawable.icon_play_custom);
                    isPlay = false;
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
        long outTime = now - myBaseTime + myStartTime;
        String time = String.format("%02d : %02d : %02d", (outTime / 1000 / 3600), (outTime / 1000 / 60) % 60, (outTime / 1000) % 60);
        return time;
    }

    String getMilSec() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime + myStartTime;
        String time = String.format("%02d", (outTime / 10) % 100);
        return time;
    }
}
