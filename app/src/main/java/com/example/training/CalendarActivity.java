package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private MaterialCalendarView cv_calendar_calendar;
    private Button btn_calendar_detail;
    private FragmentCalendarDetail fragment_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        cv_calendar_calendar = findViewById(R.id.cv_calendar_calendar);
        btn_calendar_detail = findViewById(R.id.btn_calendar_detail);
        fragment_detail = new FragmentCalendarDetail();

        //calendar date click event
        cv_calendar_calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
                String params = fm.format(date.getDate());
                Bundle bundle = new Bundle();
                bundle.putString("date", params); // Key, Value
                fragment_detail.setArguments(bundle);
            }
        });

        //date select click
        btn_calendar_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_calendar, fragment_detail).commit();
            }
        });

        //calendar decorator
        cv_calendar_calendar.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new TodayDecorator()
        );
    }

    public interface OnBackPressedListener {
        void onBack();
    }

//    fragment에서 뒤로가기시 calendarActivity로 이동, activity에서 뒤로가기 시 메인으로 이동
    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList.size() != 0) {
            for(Fragment fragment : fragmentList){
                if(fragment instanceof OnBackPressedListener){
                    ((OnBackPressedListener)fragment).onBack();
                }
            }
        } else {
            super.onBackPressed();
        }
    }

}
