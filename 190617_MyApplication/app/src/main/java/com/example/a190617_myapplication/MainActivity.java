package com.example.a190617_myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바 사용설정
        toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);  //이 액티비티에서 툴바를 사용하겠다는 선언.


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
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
                intent = new Intent(getApplicationContext(), Main2_Login.class);
                startActivity(intent);

//            case R.id.home :
//            case R.id.action_threeline :
//                ((TextView)findViewById(R.id.main1_title)).setText(("MENU"));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}