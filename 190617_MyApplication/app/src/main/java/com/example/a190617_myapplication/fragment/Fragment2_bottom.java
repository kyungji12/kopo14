package com.example.a190617_myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a190617_myapplication.R;
import com.example.a190617_myapplication.activity.MainActivity;

public class Fragment2_bottom extends Fragment {
    private ImageButton fm2_backBtn;
    private ImageButton fm2_forwardBtn;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_fragment2_bottom, container, false );
        //←버튼
        fm2_backBtn = view.findViewById(R.id.fm2_backBtn);
        fm2_backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).setmViewPager(0);
            }
        });
        //→버튼
        fm2_forwardBtn = view.findViewById(R.id.fm2_forwardBtn);
        fm2_forwardBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).setmViewPager(2);
            }
        });
        return view;
    }
}
