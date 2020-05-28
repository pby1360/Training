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

        protected TextView id;
		protected TextView contents;
		protected TextView date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.id = (TextView) itemView.findViewById(R.id.tv_memo_id);
            this.contents = (TextView) itemView.findViewById(R.id.tv_memo_contents);
			this.date = (TextView) itemView.findViewById(R.id.tv_memo_date);
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
		holder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.contents.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
		holder.date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.id.setGravity(Gravity.CENTER);
        holder.contents.setGravity(Gravity.CENTER);
		holder.date.setGravity(Gravity.CENTER);

        holder.id.setText(Integer.toString(memoList.get(position).getId()));
        holder.contents.setText(memoList.get(position).getContents());
		holder.date.setText(memoList.get(position).getDate());
	}

	@Override
	public int getItemCount()
	{
		return (null != memoList ? memoList.size() : 0);
	}

}
