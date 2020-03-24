package com.example.training;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

public class TimePopUpDialog extends Dialog {
//    private Context mContext;
    private TimePopUpListener timePopUpListener;
    private Button btn_timePopup_submit;
    private Button btn_timePopup_cancel;
    private EditText et_timePopup_hour;
    private EditText et_timePopup_min;
    private EditText et_timePopup_sec;
    private ImageButton btn_timePopup_plusHour;
    private ImageButton btn_timePopup_plusMin;
    private ImageButton btn_timePopup_plusSec;
    private ImageButton btn_timePopup_minusHour;
    private ImageButton btn_timePopup_minusMin;
    private ImageButton btn_timePopup_minusSec;
    private int hour;
    private int min;
    private int sec;
    private String strHour;
    private String strMin;
    private String strSec;

    public TimePopUpDialog(@NonNull Context context, TimePopUpListener timePopUpListener) {
        super(context);
        this.timePopUpListener=timePopUpListener;
//        this.mContext = context;

    }

    public interface TimePopUpListener {
        void clickSubmitBtn(int hour,int min, int sec);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_popup);

        btn_timePopup_plusHour = findViewById(R.id.btn_timePopup_plusHour);
        btn_timePopup_plusMin = findViewById(R.id.btn_timePopup_plusMin);
        btn_timePopup_plusSec = findViewById(R.id.btn_timePopup_plusSec);
        btn_timePopup_minusHour = findViewById(R.id.btn_timePopup_minusHour);
        btn_timePopup_minusMin = findViewById(R.id.btn_timePopup_minusMin);
        btn_timePopup_minusSec = findViewById(R.id.btn_timePopup_minusSec);

        btn_timePopup_submit = findViewById(R.id.btn_timePopup_submit);
        btn_timePopup_cancel = findViewById(R.id.btn_timePopup_cancel);
        et_timePopup_hour = findViewById(R.id.et_timePopup_hour);
        et_timePopup_min = findViewById(R.id.et_timePopup_min);
        et_timePopup_sec = findViewById(R.id.et_timePopup_sec);

        btn_timePopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btn_timePopup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.parseInt(et_timePopup_hour.getText().toString());
                int min = Integer.parseInt(et_timePopup_min.getText().toString());
                int sec = Integer.parseInt(et_timePopup_sec.getText().toString());
                timePopUpListener.clickSubmitBtn(hour,min,sec);
                dismiss();
            }
        });

        btn_timePopup_plusHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_hour.getText().toString()) < 9) {
                    hour = Integer.parseInt(et_timePopup_hour.getText().toString()) + 1;
                    strHour = "0" + hour;
                    et_timePopup_hour.setText(strHour);
                }
                else if(Integer.parseInt(et_timePopup_hour.getText().toString()) == 99) {
                    et_timePopup_hour.setText("00");
                }
                else {
                    hour = Integer.parseInt(et_timePopup_hour.getText().toString()) + 1;
                    strHour = Integer.toString(hour);
                    et_timePopup_hour.setText(strHour);
                }
            }
        });

        btn_timePopup_plusMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_min.getText().toString()) < 9) {
                    min = Integer.parseInt(et_timePopup_min.getText().toString()) + 1;
                    strMin = "0" + min;
                    et_timePopup_min.setText(strMin);
                } else if(Integer.parseInt(et_timePopup_min.getText().toString()) == 59) {
                    et_timePopup_min.setText("00");
                }
                else {
                    min = Integer.parseInt(et_timePopup_min.getText().toString()) + 1;
                    strMin = Integer.toString(min);
                    et_timePopup_min.setText(strMin);
                }
            }
        });

        btn_timePopup_plusSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_sec.getText().toString()) < 9) {
                    sec = Integer.parseInt(et_timePopup_sec.getText().toString()) + 1;
                    strSec = "0" + sec;
                    et_timePopup_sec.setText(strSec);
                } else if(Integer.parseInt(et_timePopup_sec.getText().toString()) == 59) {
                    et_timePopup_sec.setText("00");
                }
                else {
                    sec = Integer.parseInt(et_timePopup_sec.getText().toString()) + 1;
                    strSec = Integer.toString(sec);
                    et_timePopup_sec.setText(strSec);
                }
            }
        });

        btn_timePopup_minusHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_hour.getText().toString()) < 11 && Integer.parseInt(et_timePopup_hour.getText().toString()) > 0) {
                    hour = Integer.parseInt(et_timePopup_hour.getText().toString()) - 1;
                    strHour = "0" + hour;
                    et_timePopup_hour.setText(strHour);
                } else if (Integer.parseInt(et_timePopup_hour.getText().toString()) < 1) {
                    et_timePopup_hour.setText("99");
                } else {
                    hour = Integer.parseInt(et_timePopup_hour.getText().toString()) - 1;
                    strHour = Integer.toString(hour);
                    et_timePopup_hour.setText(strHour);
                }
            }
        });

        btn_timePopup_minusMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_min.getText().toString()) < 11 && Integer.parseInt(et_timePopup_min.getText().toString()) > 0) {
                    min = Integer.parseInt(et_timePopup_min.getText().toString()) - 1;
                    strMin = "0" + min;
                    et_timePopup_min.setText(strMin);
                } else if (Integer.parseInt(et_timePopup_min.getText().toString()) < 1) {
                    et_timePopup_min.setText("59");
                } else {
                    min = Integer.parseInt(et_timePopup_min.getText().toString()) - 1;
                    strMin = Integer.toString(min);
                    et_timePopup_min.setText(strMin);
                }
            }
        });

        btn_timePopup_minusSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(et_timePopup_sec.getText().toString()) < 11 && Integer.parseInt(et_timePopup_sec.getText().toString()) > 0) {
                    sec = Integer.parseInt(et_timePopup_sec.getText().toString()) - 1;
                    strSec = "0" + sec;
                    et_timePopup_sec.setText(strSec);
                } else if (Integer.parseInt(et_timePopup_sec.getText().toString()) < 1) {
                    et_timePopup_sec.setText("59");
                } else {
                    sec = Integer.parseInt(et_timePopup_sec.getText().toString()) - 1;
                    strSec = Integer.toString(sec);
                    et_timePopup_sec.setText(strSec);
                }
            }
        });

    }

}

