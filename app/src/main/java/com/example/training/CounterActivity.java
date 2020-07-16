package com.example.training;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import java.text.*;
import java.util.Date;

public class CounterActivity extends AppCompatActivity {
	
	private TextView tv_counter_sumSet;
	private TextView tv_counter_cnt;
	private TextView tv_counter_sumCnt;
	private ImageView iv_counter_plus;
	private ImageView iv_counter_minus;
	private TextView tv_counter_comSet;
	private ImageView iv_counter_reset;
    private ImageView iv_counter_record;
	
	private int count = 0;
	private int setCnt = 0;
	private int sumCnt = 0;

	private long   backPressedTime = 0;
	private final long FINISH_INTERVAL_TIME = 2000;

	@Override
	public void onBackPressed() {
		long tempTime = System.currentTimeMillis();
		long intervalTime = tempTime - backPressedTime;

		if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
		{
			super.onBackPressed();
		}
		else
		{
			backPressedTime = tempTime;
			Toast.makeText(getApplicationContext(), getString(R.string.go_back), Toast.LENGTH_SHORT).show();
		}

	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
		
		tv_counter_sumSet = findViewById(R.id.tv_counter_sumSet);
		tv_counter_cnt  = findViewById(R.id.tv_counter_cnt);
		tv_counter_sumCnt = findViewById(R.id.tv_counter_sumCnt);
		iv_counter_plus = findViewById(R.id.iv_counter_plus);
		iv_counter_minus = findViewById(R.id.iv_counter_minus);
		tv_counter_comSet = findViewById(R.id.tv_counter_comSet);
		iv_counter_reset = findViewById(R.id.iv_counter_reset);
		iv_counter_record = findViewById(R.id.iv_counter_record);

		//db선언
		final MemoDatabase db = Room.databaseBuilder(this, MemoDatabase.class, "memo-db").build();
        
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        final String today = format.format(date);

        tv_counter_sumSet.setText("0 " + getString(R.string.set));

        iv_counter_record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	Bundle args =  new Bundle();
                	args.putInt("setCnt",setCnt);
					args.putInt("sumCnt",sumCnt);
					MemoPopUpDialog memoPopUpDialog = MemoPopUpDialog.getInstance();
					memoPopUpDialog.setCancelable(false);
					memoPopUpDialog.setArguments(args);
                	memoPopUpDialog.show(getSupportFragmentManager(),"add");
                	memoPopUpDialog.setDialogResult(new MemoPopUpDialog.OnMyDialogResult() {
						@Override
						public void finish(String result) {
							new InsertAsyncTask(db.memoDao()).execute(new Memo(0, result, today));
							Toast.makeText(getApplicationContext(), getString(R.string.save_memo),Toast.LENGTH_SHORT).show();
						}
					});
                }
        });

		iv_counter_plus.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					count++;
					tv_counter_cnt.setText(Integer.toString(count));
				}
		});

		iv_counter_minus.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					if(count !=0) {
						count = count - 1;
						tv_counter_cnt.setText(Integer.toString(count));
					}

				}
		});
		
		tv_counter_comSet.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = sumCnt + count;
					count = 0;
					setCnt++;
					tv_counter_cnt.setText(Integer.toString(count));
					tv_counter_sumSet.setText(setCnt + " " + getString(R.string.set));
					tv_counter_sumCnt.setText(Integer.toString(sumCnt));
				}
		});
		
		iv_counter_reset.setOnClickListener(new View.OnClickListener() {
			    @Override
				public void onClick(View v) {
					sumCnt = 0;
					count = 0;
					setCnt = 0;				
					tv_counter_sumSet.setText("0 " + getString(R.string.set));
					tv_counter_cnt.setText("0");
					tv_counter_sumCnt.setText("0");
				}
		});
    }
	// db insert data by date in the background thread
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
