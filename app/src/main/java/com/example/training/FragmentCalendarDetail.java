package com.example.training;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentCalendarDetail extends Fragment implements CalendarActivity.OnBackPressedListener {

    private ImageView iv_calendar_back;
    private String params;
	private TextView tv_week_day1;
	private TextView tv_week_day2;
	private TextView tv_week_day3;
	private TextView tv_week_day4;
	private TextView tv_week_day5;
	private TextView tv_week_day6;
	private TextView tv_week_day7;
	private TextView tv_week_date1;
	private TextView tv_week_date2;
	private TextView tv_week_date3;
	private TextView tv_week_date4;
	private TextView tv_week_date5;
	private TextView tv_week_date6;
	private TextView tv_week_date7;
    private SimpleDateFormat fm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar_detail, container, false);

		iv_calendar_back = root.findViewById(R.id.iv_calendar_back);
        tv_week_day1 = root.findViewById(R.id.tv_week_day1);
		tv_week_day2 = root.findViewById(R.id.tv_week_day2);
		tv_week_day3 = root.findViewById(R.id.tv_week_day3);
		tv_week_day4 = root.findViewById(R.id.tv_week_day4);
		tv_week_day5 = root.findViewById(R.id.tv_week_day5);
		tv_week_day6 = root.findViewById(R.id.tv_week_day6);
		tv_week_day7 = root.findViewById(R.id.tv_week_day7);
		tv_week_date1 = root.findViewById(R.id.tv_week_date1);
		tv_week_date2 = root.findViewById(R.id.tv_week_date2);
		tv_week_date3 = root.findViewById(R.id.tv_week_date3);
		tv_week_date4 = root.findViewById(R.id.tv_week_date4);
		tv_week_date5 = root.findViewById(R.id.tv_week_date5);
		tv_week_date6 = root.findViewById(R.id.tv_week_date6);
		tv_week_date7 = root.findViewById(R.id.tv_week_date7);

        fm = new SimpleDateFormat("yyyy-MM-dd");

//      전달한 key 값 String param2 = getArguments().getString("param2"); // 전달한 key 값 }
//        if(getArguments() != null) {
        params = getArguments().getString("date");
//        }

        setWeek(params);

//      back button click 종료 대신 calendar 로 이동
        iv_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
                getFragmentManager().popBackStack();
            }
        });

        tv_week_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date1.getTag());
                setWeek(date);
            }
        });
        tv_week_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date2.getTag());
                setWeek(date);
            }
        });
        tv_week_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date3.getTag());
                setWeek(date);
            }
        });
        tv_week_date5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date5.getTag());
                setWeek(date);
            }
        });
        tv_week_date6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date6.getTag());
                setWeek(date);
            }
        });
        tv_week_date7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date7.getTag());
                setWeek(date);
            }
        });

        return root;
    }

    //      back button click 종료 대신 calendar 로 이동
    @Override
    public void onBack() {
        getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
        getFragmentManager().popBackStack();
    }

//    주단위 요일,날짜 셋팅
    public void setWeek(String params) {

        Calendar cal = Calendar.getInstance();
        String getDate;
        String getDay;

        try {
            cal.setTime(fm.parse(params));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.add(Calendar.DATE, -3);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date1.setText(getDate);
        tv_week_date1.setTag(cal.getTime());
        tv_week_day1.setText(getDay);


        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date2.setText(getDate);
        tv_week_date2.setTag(cal.getTime());
        tv_week_day2.setText(getDay);

        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date3.setText(getDate);
        tv_week_date3.setTag(cal.getTime());
        tv_week_day3.setText(getDay);

        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date4.setText(getDate);
        tv_week_day4.setText(getDay);

        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date5.setText(getDate);
        tv_week_date5.setTag(cal.getTime());
        tv_week_day5.setText(getDay);

        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date6.setText(getDate);
        tv_week_date6.setTag(cal.getTime());
        tv_week_day6.setText(getDay);

        cal.add(Calendar.DATE, 1);
        getDate = Integer.toString(cal.getTime().getDate());
        getDay = setDay(cal.getTime().getDay());
        tv_week_date7.setText(getDate);
        tv_week_date7.setTag(cal.getTime());
        tv_week_day7.setText(getDay);

    }
// 요일 셋팅
    public String setDay(int getDay) {
        String day = "";
        switch (getDay) {
            case 0:
                day = "SUN";
                break;
            case 1:
                day = "MON";
                break;
            case 2:
                day = "TUE";
                break;
            case 3:
                day = "WED";
                break;
            case 4:
                day = "THU";
                break;
            case 5:
                day = "FRI";
                break;
            case 6:
                day = "SAT";
                break;
        }
        return day;
    }
}
