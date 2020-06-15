package com.example.training;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//public class MemoEditDialog extends DialogFragment implements View.OnClickListener {
public class MemoEditDialog extends DialogFragment {
//    OnMyDialogResult dialogResult;

    public MemoEditDialog() {}
    public static MemoEditDialog getInstance() {
        MemoEditDialog memoEditDialog = new MemoEditDialog();
        return memoEditDialog;
    }

    private EditText et_memoEdit_memo;
    private Button btn_memoEdit_edit;
    private Button btn_memoEdit_delete;
    private Button btn_memoEdit_cancel;
    private InputMethodManager mInputMethodManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.memo_edit_popup, container);

        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        et_memoEdit_memo = root.findViewById(R.id.et_memoEdit_memo);
        btn_memoEdit_edit = root.findViewById(R.id.btn_memoEdit_edit);
        btn_memoEdit_delete = root.findViewById(R.id.btn_memoEdit_delete);
        btn_memoEdit_cancel = root.findViewById(R.id.btn_memoEdit_cancel);
        btn_memoEdit_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setMemo();
            }
        });
//        btn_memoEdit_cancel.setOnClickListener(this);
        return root;
    }
//    public void setMemo() {
//        if(et_memoPopup_memo.getText().toString().equals("")) {
//            Toast.makeText(getActivity(),"내용을 입력하세요.",Toast.LENGTH_SHORT).show();
//        } else {
//            dialogResult.finish(et_memoPopup_memo.getText().toString());
//            et_memoPopup_memo.setText("");
//            mInputMethodManager.hideSoftInputFromWindow(et_memoPopup_memo.getWindowToken(), 0);
//            dismiss();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        mInputMethodManager.hideSoftInputFromWindow(et_memoPopup_memo.getWindowToken(), 0);
//        dismiss();
//    }
//
//    public void setDialogResult(OnMyDialogResult result){
//        dialogResult = result;
//    }
//
//    public interface OnMyDialogResult{
//        void finish(String result);
//    }
//
//    @Override
//    public void onResume() {
//        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
//        super.onResume();
//    }
}