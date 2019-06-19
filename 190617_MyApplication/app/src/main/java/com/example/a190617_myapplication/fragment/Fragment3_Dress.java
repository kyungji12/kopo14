package com.example.a190617_myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a190617_myapplication.R;
import com.example.a190617_myapplication.activity.MainActivity;

public class Fragment3_Dress extends Fragment {
    private ImageButton fm3_backBtn;
    private ImageButton fm3_forwardBtn;
    View view;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_fragment3__dress, container, false );

        fm3_backBtn = view.findViewById(R.id.fm3_backBtn);
        fm3_backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Fragment2_bottom.class);
                ((MainActivity)getActivity()).setmViewPager(1);
            }
        });

        fm3_forwardBtn = view.findViewById(R.id.fm3_forwardBtn);
        fm3_forwardBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Fragment1_Top.class);
                ((MainActivity)getActivity()).setmViewPager(0);
            }
        });

        return view;
    }
}