package com.example.a190617_myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.a190617_myapplication.R;

public class Main4_Class extends AppCompatActivity {
    Intent intent;
    Toolbar toolbar;
    TextView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3__after_login);

        //툴바 사용설정
        toolbar = findViewById(R.id.main4_app_toolbar);
        setSupportActionBar(toolbar); //이 액티비티에서 툴바를 사용하겠다는 선언.
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        //STYLERA 누르면 홈으로 이동하기
        home = (TextView)findViewById(R.id.main4_title);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.appbar_action,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_search :
                //((TextView)findViewById(R.id.main1_title)).setText(("SEARCH"));
                return true;
            case R.id.action_account :
                //((TextView)findViewById(R.id.main1_title)).setText(("ACCOUNT"));
//                intent = new Intent(getApplicationContext(), Main2_Login.class);
//                startActivity(intent);

            case android.R.id.home :
                Toast.makeText(this, "홈버튼 눌림 ", Toast.LENGTH_LONG).show();
                return true;
//            case R.id.action_threeline :
//                ((TextView)findViewById(R.id.main1_title)).setText(("MENU"));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}