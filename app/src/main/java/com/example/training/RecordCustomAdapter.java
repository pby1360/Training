package com.example.training;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecordCustomAdapter extends RecyclerView.Adapter<RecordCustomAdapter.CustomViewHolder> {

    private ArrayList<RecordDictionary> RecordList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView recordNo;
        protected TextView recordTime;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recordNo = (TextView) itemView.findViewById(R.id.tv_sw_record_no);
            this.recordTime = (TextView) itemView.findViewById(R.id.tv_sw_record_time);
        }
    }

    public RecordCustomAdapter(ArrayList<RecordDictionary> RecordList){
        this.RecordList = RecordList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.stopwatch_record, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.recordNo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.recordTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.recordNo.setGravity(Gravity.CENTER);
        holder.recordTime.setGravity(Gravity.CENTER);

        holder.recordNo.setText(RecordList.get(position).getRecordNo());
        holder.recordTime.setText(RecordList.get(position).getRecordTime());

    }

    @Override
    public int getItemCount() {
        return (null != RecordList ? RecordList.size() : 0);
    }
}
