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
    private InputMethodManager mInputMethodManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.memo_popup, container);

        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        et_memoPopup_memo = root.findViewById(R.id.et_memoPopup_memo);
        btn_memoPopup_submit = root.findViewById(R.id.btn_memoPopup_submit);
        btn_memoPopup_cancel = root.findViewById(R.id.btn_memoPopup_cancel);

        Bundle args = getArguments();
        if(args != null) {
            int set = args.getInt("setCnt");
            int count = args.getInt("sumCnt");
            et_memoPopup_memo.setText(set + " " + getString(R.string.set) + ", " + getString(R.string.total) + " " + count + getString(R.string.ea));
        }

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
            Toast.makeText(getActivity(), getString(R.string.input_contents),Toast.LENGTH_SHORT).show();
        } else {
            dialogResult.finish(et_memoPopup_memo.getText().toString());
            et_memoPopup_memo.setText("");
            mInputMethodManager.hideSoftInputFromWindow(et_memoPopup_memo.getWindowToken(), 0);
            dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        mInputMethodManager.hideSoftInputFromWindow(et_memoPopup_memo.getWindowToken(), 0);
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
