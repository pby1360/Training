package com.example.training;
import androidx.recyclerview.widget.*;
import android.view.*;
import java.util.*;
import android.widget.*;
import androidx.annotation.*;
import android.util.*;

public class MemoCustomAdapter extends RecyclerView.Adapter<MemoCustomAdapter.CustomViewHolder>
{
	interface  OnitemClickListener {
		void onItemClick(View view, int position);
	}
	OnitemClickListener mListner;
	private ArrayList <MemoDictionary> memoList;

	//외부에서 들어온게 위에와 연결
	public void setOnItemClickListener(OnitemClickListener listener) {
		mListner = listener;
	}
	
	public class CustomViewHolder extends RecyclerView.ViewHolder {

		protected TextView contents;
        //protected TextView id;
		//protected TextView date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.contents = (TextView) itemView.findViewById(R.id.tv_memo_contents);
		    //this.id = (TextView) itemView.findViewById(R.id.tv_memo_id);
			//this.date = (TextView) itemView.findViewById(R.id.tv_memo_date);
        }
    }
	
	public MemoCustomAdapter(ArrayList<MemoDictionary> memoList) {
		this.memoList = memoList;
	}
	
	

	@Override
	public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
	{
		View view = LayoutInflater.from(viewGroup.getContext())
			.inflate(R.layout.calendar_memo, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
	}

	@Override
	public void onBindViewHolder(final CustomViewHolder holder, final int position)
	{
		holder.contents.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		//holder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		//holder.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		//holder.id.setGravity(Gravity.CENTER);
		//holder.contents.setGravity(Gravity.START);
		//holder.date.setGravity(Gravity.CENTER);

		//holder.id.setText(Integer.toString(memoList.get(position).getId()));
		//holder.date.setText(memoList.get(position).getDate());
		holder.contents.setText(memoList.get(position).getContents());
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if(mListner != null) {
					mListner.onItemClick(holder.itemView, position);
					return false;
				}
				return false;
			}
		});
	}

	@Override
	public int getItemCount()
	{
		return (null != memoList ? memoList.size() : 0);
	}

}
