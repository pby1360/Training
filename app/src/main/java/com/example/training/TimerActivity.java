package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class TimerActivity extends AppCompatActivity {

    private Context context;
	private TextView tv_timer_time;
	private ImageView iv_timer_stop;
	private ImageView iv_timer_start;
	private ImageView iv_timer_restart;
	private CheckBox cb_timer_sound;
    private CheckBox cb_timer_vibe;
	private Switch sch_timer_con1;
	private Switch sch_timer_con2;
	private ImageView iv_timer_con1Up;
	private ImageView iv_timer_con1Down;
	private EditText et_timer_con1Time;
	private Spinner sn_timer_con1Unit;

    private Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION );
    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;

	private long myBaseTime;
	private long myStartTime;
    private long oldTime;

    private boolean isPlay;

	private TimePopUpDialog dialog;


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
        setContentView(R.layout.activity_timer);

        context = getBaseContext();

		iv_timer_start = findViewById(R.id.iv_timer_start);
		iv_timer_stop = findViewById(R.id.iv_timer_stop);
		iv_timer_restart = findViewById(R.id.iv_timer_restart);
		tv_timer_time =  findViewById(R.id.tv_timer_time);
		cb_timer_sound = findViewById(R.id.cb_timer_sound);
		cb_timer_vibe =  findViewById(R.id.cb_timer_vibe);

		cb_timer_sound.setChecked(true);
		cb_timer_vibe.setChecked(true);

		tv_timer_time.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(isPlay == false) {
					dialog = new TimePopUpDialog(TimerActivity.this, new TimePopUpDialog.TimePopUpListener() {
						@Override
						public void clickSubmitBtn(int hour, int min, int sec) {
							myBaseTime = (hour * 3600 * 1000) + (min * 60 * 1000) +(sec * 1000);
							oldTime = myBaseTime;
							String sHour;
							String sMin;
							String sSec;

							if (hour < 10) {
								sHour = "0" + hour;
							} else {
								sHour = Integer.toString(hour);
							}
							if (min < 10) {
								sMin = "0" + min;
							} else {
								sMin = Integer.toString(min);
							}
							if (sec < 10) {
								sSec = "0" + sec;
							} else {
								sSec = Integer.toString(sec);
							}

							tv_timer_time .setText(sHour + " : " + sMin + " : " +  sSec);
						}
					});
					dialog.show();
				}
				else {
					Toast.makeText(TimerActivity.this,"타이머를 멈추고 시도하세요.", Toast.LENGTH_SHORT).show();
				}
			}
		});



		iv_timer_restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myTimer.removeMessages(0);
				iv_timer_start.setImageResource(R.drawable.icon_play_custom);
				String time = String.format("%02d : %02d : %02d", (oldTime / 1000 / 3600), (oldTime / 1000 / 60) % 60, (oldTime / 1000) % 60);
				myBaseTime = oldTime;
				tv_timer_time.setText(time);
				isPlay = false;
			}
		});

		iv_timer_stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myTimer.removeMessages(0);
				tv_timer_time.setText("00 : 00 : 00");
				iv_timer_start.setImageResource(R.drawable.icon_play_custom);
				isPlay = false;
				myBaseTime = 0;
			}
		});

		iv_timer_start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (isPlay == false) {
					if (myBaseTime == 0) {
						Toast.makeText(TimerActivity.this, "시간을 설정을 하세요.", Toast.LENGTH_SHORT).show();
					} else {
						myStartTime = SystemClock.elapsedRealtime();
						myTimer.sendEmptyMessage(0);
						iv_timer_start.setImageResource(R.drawable.icon_pause_custom);
						isPlay = true;
					}
				}
				else {
					long now = SystemClock.elapsedRealtime();
					myTimer.removeMessages(0);
					myBaseTime = myBaseTime -  (now - myStartTime);
					iv_timer_start.setImageResource(R.drawable.icon_play_custom);
					isPlay = false;
				}

			}
		});
    }

	Handler myTimer = new Handler() {
		public void handleMessage(Message msg) {
			if (tv_timer_time.getText().equals("00 : 00 : 00")) {
			    myBaseTime = 0;
				myTimer.removeMessages(0);
				isPlay = false;
				iv_timer_start.setImageResource(R.drawable.icon_play_custom);
				sound();
				vibe();
			} else {
				tv_timer_time.setText(getTimeOut());
				myTimer.sendEmptyMessage(0);
			}
		}
	};

	String getTimeOut() {
		long now = SystemClock.elapsedRealtime();
		long outTime = myBaseTime - (now - myStartTime);
		String time = String.format("%02d : %02d : %02d", (outTime / 1000 / 3600), (outTime / 1000 / 60) % 60, (outTime / 1000) % 60);
		return time;
	}

	void sound() {
	    if(cb_timer_sound.isChecked()) {
            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
            r.play();
        }
    }

    void vibe() {
	    if(cb_timer_vibe.isChecked()) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1000);
        }
    }
}
