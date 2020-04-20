package com.example.training;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTest extends Fragment {

    private Button btn_frg_close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null) {
            String param1 = getArguments().getString("date"); // 전달한 key 값 String param2 = getArguments().getString("param2"); // 전달한 key 값 }
            Log.d("frg", ": " +param1);
        }


        View root = inflater.inflate(R.layout.fragment_test, container, false);

        btn_frg_close = root.findViewById(R.id.btn_frg_close);

        btn_frg_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(FragmentTest.this).commit();
                getFragmentManager().popBackStack();
            }
        });
        return root;
    }
}
