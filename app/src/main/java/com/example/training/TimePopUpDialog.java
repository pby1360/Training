package com.example.training;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class TimePopUpDialog extends Dialog {
    private Context mContext;
    private Button btn_timePopup_submit;
    private Button btn_timePopup_cancel;

    public TimePopUpDialog(@NonNull Context context) {
        super(context);
        mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_popup);

        btn_timePopup_submit = (Button)findViewById(R.id.btn_timePopup_submit);
        btn_timePopup_cancel = (Button)findViewById(R.id.btn_timePopup_cancel);

        btn_timePopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

}

