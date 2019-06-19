package com.example.a190617_myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.a190617_myapplication.R;
import com.example.a190617_myapplication.adapter.SectionsStatePagerAdapter;
import com.example.a190617_myapplication.fragment.Fragment1_Top;
import com.example.a190617_myapplication.fragment.Fragment2_bottom;
import com.example.a190617_myapplication.fragment.Fragment3_Dress;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Toolbar toolbar;

    private SectionsStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 사용설정
        toolbar = findViewById(R.id.main1_app_toolbar);
        setSupportActionBar(toolbar);  //이 액티비티에서 툴바를 사용하겠다는 선언.

        //fragment
        mSectionStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPaager(mViewPager);


        getSupportActionBar().setDisplayShowCustomEnabled(true); //커스터마이징하겠당
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //왼쪽 메뉴 아이콘
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
    }

    private void setupViewPaager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1_Top(), "Fragment1"); //0
        adapter.addFragment(new Fragment2_bottom(), "Fragment2"); //1
        adapter.addFragment(new Fragment3_Dress(), "Fragment3"); //2
        viewPager.setAdapter(adapter);
    }
    public void setmViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_account:
                intent = new Intent(getApplicationContext(), Main2_Login.class);
                startActivity(intent);

            case android.R.id.home:
                Toast.makeText(this, "홈버튼 눌림 ", Toast.LENGTH_LONG).show();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}