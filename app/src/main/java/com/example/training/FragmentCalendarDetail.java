package com.example.training;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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

//    private FragmentCalendarWeek fragment_week;
    private ImageView iv_calendar_back;
    private String params;
    private Date date;
	private String tv_week_day1;
	private String tv_week_day2;
	private String tv_week_day3;
	private String tv_week_day4;
	private String tv_week_day5;
	private String tv_week_day6;
	private String tv_week_day7;
	private String tv_week_date1;
	private String tv_week_date2;
	private String tv_week_date3;
	private String tv_week_date4;
	private String tv_week_date5;
	private String tv_week_date6;
	private String tv_week_date7;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        fragment_week = new FragmentCalendarWeek();
//        FragmentManager fmanager =  getFragmentManager();
//        FragmentTransaction ftrans = fmanager.beginTransaction();
//        ftrans.replace(R.id.fl_calendar_container, fragment_week).commit();

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
		
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

//        cal.setTime(new Date());
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//        cal.add(Calendar.MONTH, 2);
//        cal.add(Calendar.DATE, -3);

//      전달한 key 값 String param2 = getArguments().getString("param2"); // 전달한 key 값 }
        if(getArguments() != null) {
            params = getArguments().getString("date");
        }
        try {
            date = fm.parse(params);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.setTime(date);
        
	//	tv_week_day1.setText(cal.add(Calendar.DATE, -3)?toString());
        cal.add(Calendar.DATE, 1);
        
//      back button click 종료 대신 calendar 로 이동
        iv_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
                getFragmentManager().popBackStack();
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
}
