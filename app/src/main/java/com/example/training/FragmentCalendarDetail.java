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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentCalendarDetail extends Fragment implements CalendarActivity.OnBackPressedListener {

//    private FragmentCalendarWeek fragment_week;
    private ImageView iv_calendar_back;
    private String params;
    private Date date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_calendar_detail, container, false);

//        fragment_week = new FragmentCalendarWeek();
//        FragmentManager fmanager =  getFragmentManager();
//        FragmentTransaction ftrans = fmanager.beginTransaction();
//        ftrans.replace(R.id.fl_calendar_container, fragment_week).commit();


        if(getArguments() != null) {
            params = getArguments().getString("date"); // 전달한 key 값 String param2 = getArguments().getString("param2"); // 전달한 key 값 }
            Log.d("params", ": " +params);
        }

        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = fm.parse(params);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Log.i("date", "onCreateView: " + date.toString());

        iv_calendar_back = root.findViewById(R.id.iv_calendar_back);

        iv_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }

    @Override
    public void onBack() {
        getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
        getFragmentManager().popBackStack();
    }
}
