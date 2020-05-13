package com.example.training;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MemoPopUpDialog extends DialogFragment implements View.OnClickListener {
    OnMyDialogResult dialogResult;

    public MemoPopUpDialog() {}
    public static MemoPopUpDialog getInstance() {
        MemoPopUpDialog memoPopUpDialog = new MemoPopUpDialog();
        return memoPopUpDialog;
    }


    private EditText et_memoPopup_memo;
    private Button btn_memoPopup_submit;
    private Button btn_memoPopup_cancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.memo_popup, container);

        et_memoPopup_memo = root.findViewById(R.id.et_memoPopup_memo);
        btn_memoPopup_submit = root.findViewById(R.id.btn_memoPopup_submit);
        btn_memoPopup_cancel = root.findViewById(R.id.btn_memoPopup_cancel);
        btn_memoPopup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMemo();
            }
        });
        btn_memoPopup_cancel.setOnClickListener(this);
        return root;
    }
    public void setMemo() {
        if(et_memoPopup_memo.getText().toString().equals("")) {
            Toast.makeText(getActivity(),"내용을 입력하세요.",Toast.LENGTH_SHORT).show();
        } else {
            dialogResult.finish(et_memoPopup_memo.getText().toString());
            et_memoPopup_memo.setText("");
            dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    public void setDialogResult(OnMyDialogResult result){
        dialogResult = result;
    }

    public interface OnMyDialogResult{
        void finish(String result);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }
}
