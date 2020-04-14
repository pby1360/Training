package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView cv_calendar_calendar;
    private Button btn_calendar_detail;
    private FragmentManager fragmentManager;
    private FragmentTest fragment_test;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        cv_calendar_calendar = findViewById(R.id.cv_calendar_calendar);
        btn_calendar_detail = findViewById(R.id.btn_calendar_detail);

//        fragmentManager = getSupportFragmentManager();
//        fragment_test = new FragmentTest();
//        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id., fragment);
//        fragmentTransaction.commit();




        btn_calendar_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        cv_calendar_calendar.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new TodayDecorator()
        );

        cv_calendar_calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.i("select", ": " + date.getDate());
            }
        });
    }
}
