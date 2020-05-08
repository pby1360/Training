package com.example.training;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class MemoPopUpDialog extends Dialog {

    private MemoPopUpListener memoPopUpListener;
    private EditText et_memoPopup_memo;
    private Button btn_memoPopup_submit;
    private Button btn_memoPopup_cancel;

    public MemoPopUpDialog(@NonNull Context context, MemoPopUpListener memoPopUpListener) {
        super(context);
        this.memoPopUpListener = memoPopUpListener;
    }
    public interface MemoPopUpListener {
        void clickSubmitBtn(String memo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_popup);
    }
}
