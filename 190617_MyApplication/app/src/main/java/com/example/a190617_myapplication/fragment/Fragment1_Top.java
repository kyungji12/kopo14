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

public class Fragment1_Top extends Fragment {
    private ImageButton fm1_backBtn;
    private ImageButton fm1_forwardBtn;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_fragment1__top, container, false );
        //←버튼
        fm1_backBtn = view.findViewById(R.id.fm1_backBtn);
        fm1_backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).setmViewPager(2);
            }
        });
        //→버튼
        fm1_forwardBtn = view.findViewById(R.id.fm1_forwardBtn);
        fm1_forwardBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).setmViewPager(1);
            }
        });
        return view;
    }
    }
