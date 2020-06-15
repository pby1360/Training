package com.example.training;

import android.os.AsyncTask;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.*;
import androidx.room.Room;

import android.content.*;
import android.widget.Toast;


import java.util.*;

public class FragmentCalendarDetail extends Fragment implements CalendarActivity.OnBackPressedListener {

    private String params;
	private Context context;
    private ImageView iv_calendar_back;
    private ImageView iv_calendar_before;
    private ImageView iv_calendar_after;
    private TextView tv_calendar_fullDate;
    private TextView tv_calendar_month;
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
    private SimpleDateFormat ffm;
    private SimpleDateFormat fmon;
    private SimpleDateFormat fdate;
    private SimpleDateFormat fday;
	
	private ArrayList<MemoDictionary> memoList;
	private MemoCustomAdapter memoAdapter;

    MemoPopUpDialog memoPopUpDialog = MemoPopUpDialog.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        declare
        View root = inflater.inflate(R.layout.fragment_calendar_detail, container, false);

		context = getActivity();
        iv_calendar_after = root.findViewById(R.id.iv_calendar_after);
        iv_calendar_before = root.findViewById(R.id.iv_calendar_before);
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
		tv_calendar_month = root.findViewById(R.id.tv_calendar_month);
		tv_calendar_fullDate = root.findViewById(R.id.tv_calendar_fullDate);
        fm = new SimpleDateFormat("yyyy-MM-dd");
        ffm = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
        fmon = new SimpleDateFormat("MMMM");
        fday = new SimpleDateFormat("E");
        fdate = new SimpleDateFormat("dd");
		memoPopUpDialog.setCancelable(false);

        Button btn_calendar_button = root.findViewById(R.id.btn_calendar_button);

		final RecyclerView recyclerView = root.findViewById(R.id.rv_calendar_memo);
        final LinearLayoutManager memoManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(memoManager);

        memoList = new ArrayList<>();
        memoAdapter = new MemoCustomAdapter(memoList);
        memoAdapter.setOnItemClickListener(new MemoCustomAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("", "position: " + position);
                Log.i("", "getId: "+ memoList.get(position).getId());
                Log.i("", "getDate: "+ memoList.get(position).getDate());
            }
        });
        recyclerView.setAdapter(memoAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
		memoManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        params = getArguments().getString("date");
        setWeek(params);

//      db 선언
        final MemoDatabase db = Room.databaseBuilder(context, MemoDatabase.class, "memo-db")
                .allowMainThreadQueries()
                .build();

//      methods

        // 실시간 Live db 변화 감지 후 실행
//        db.memoDao().getLiveMemo(params).observe(this, new Observer<List<Memo>>() {
//            @Override
//            public void onChanged(List<Memo> memos) {
//                Log.i("", "onChanged: " + params);
//                memoList.clear();
//                for(int i = 0; i < memos.size(); i++) {
//                    MemoDictionary data = new MemoDictionary(
//                            memos.get(i).id,
//                            memos.get(i).contents,
//                            memos.get(i).date
//                    );
//                    memoList.add(data);
//                    memoAdapter.notifyDataSetChanged();
//                }
//            }
//        });


        List<Memo> list = db.memoDao().getMemo(params);
        for(int i = 0; i < list.size(); i++) {
            MemoDictionary data = new MemoDictionary(
                    list.get(i).id,
                    list.get(i).contents,
                    list.get(i).date
            );
            memoList.add(data);
        }
        memoAdapter.notifyDataSetChanged();

        //      전달한 key 값 String param2 = getArguments().getString("param2"); // 전달한 key 값 }
        btn_calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memoPopUpDialog.show(getFragmentManager(),"test");
                memoPopUpDialog.setDialogResult(new MemoPopUpDialog.OnMyDialogResult() {
                    @Override
                    public void finish(String result) {
                        new InsertAsyncTask(db.memoDao()).execute(new Memo(result, params));
                        MemoDictionary data = new MemoDictionary(0, result, params);
                        memoList.add(data);
                        memoAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        // back button click 종료 대신 calendar 로 이동
        iv_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmentCalendarDetail.this).commit();
                getFragmentManager().popBackStack();
            }
        });

        // detail에서 주단위 날짜 클릭 시 데이터 변경 및 db에서 memo select
        tv_week_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date1.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });
        tv_week_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date2.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });
        tv_week_date3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date3.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });
        tv_week_date5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date5.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });
        tv_week_date6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date6.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });
        tv_week_date7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = fm.format(tv_week_date7.getTag());
                setWeek(date);
                params = date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
            }
        });

        //월 변경
        iv_calendar_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                String date = fm.format(tv_week_date4.getTag());
                try {
                    cal.setTime(fm.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal.add(Calendar.DATE, -30);
                String _date = fm.format(cal.getTime());
                setWeek(_date);
                params = _date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();

            }
        });
        iv_calendar_after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                String date = fm.format(tv_week_date4.getTag());
                try {
                    cal.setTime(fm.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal.add(Calendar.DATE, 30);
                String _date = fm.format(cal.getTime());
                setWeek(_date);
                params = _date;
                memoList.clear();
                List<Memo> list = db.memoDao().getMemo(params);
                for(int i = 0; i < list.size(); i++) {
                    MemoDictionary data = new MemoDictionary(
                            list.get(i).id,
                            list.get(i).contents,
                            list.get(i).date
                    );
                    memoList.add(data);
                }
                memoAdapter.notifyDataSetChanged();
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

        try {
            cal.setTime(fm.parse(params));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tv_calendar_fullDate.setText(ffm.format(cal.getTime()));

        cal.add(Calendar.DATE, -3);
        tv_week_date1.setText(fdate.format(cal.getTime()));
        tv_week_date1.setTag(cal.getTime());
        tv_week_day1.setText(fday.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date2.setText(fdate.format(cal.getTime()));
        tv_week_date2.setTag(cal.getTime());
        tv_week_day2.setText(fday.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date3.setText(fdate.format(cal.getTime()));
        tv_week_date3.setTag(cal.getTime());
        tv_week_day3.setText(fday.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date4.setText(fdate.format(cal.getTime()));
        tv_week_date4.setTag(cal.getTime());
        tv_week_day4.setText(fday.format(cal.getTime()));
        tv_calendar_month.setText(fmon.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date5.setText(fdate.format(cal.getTime()));
        tv_week_date5.setTag(cal.getTime());
        tv_week_day5.setText( fday.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date6.setText(fdate.format(cal.getTime()));
        tv_week_date6.setTag(cal.getTime());
        tv_week_day6.setText( fday.format(cal.getTime()));

        cal.add(Calendar.DATE, 1);
        tv_week_date7.setText(fdate.format(cal.getTime()));
        tv_week_date7.setTag(cal.getTime());
        tv_week_day7.setText( fday.format(cal.getTime()));

    }

    // db insert method use background thread
    public static class InsertAsyncTask extends AsyncTask<Memo, Void, Void> {
        private MemoDao mMemoDao;

        public InsertAsyncTask(MemoDao memoDao) {
            this.mMemoDao = memoDao;
        }
        @Override
        protected Void doInBackground(Memo... memos) {
            mMemoDao.insert(memos[0]);
            return null;
        }
    }
}
