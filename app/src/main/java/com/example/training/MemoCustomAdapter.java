package com.example.training;
import androidx.recyclerview.widget.*;
import android.view.*;
import java.util.*;
import android.widget.*;
import androidx.annotation.*;
import android.util.*;

public class MemoCustomAdapter extends RecyclerView.Adapter<MemoCustomAdapter.CustomViewHolder>
{
	private ArrayList <MemoDictionary> memoList;
	
	public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView memo;
		protected TextView memoNo;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.memoNo = (TextView) itemView.findViewById(R.id.tv_memo_no);
            this.memo = (TextView) itemView.findViewById(R.id.tv_memo_memo);
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
	public void onBindViewHolder(CustomViewHolder holder, int position)
	{
		holder.memoNo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.memo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.memoNo.setGravity(Gravity.CENTER);
        holder.memo.setGravity(Gravity.CENTER);

        holder.memoNo.setText(memoList.get(position).getMemoNo());
        holder.memo.setText(memoList.get(position).getMemo());
	}

	@Override
	public int getItemCount()
	{
		return (null != memoList ? memoList.size() : 0);
	}

}
