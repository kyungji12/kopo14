package com.example.a190617_myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    ImageView login;
    Intent intent;
    Toolbar tb;

    ActionBar ab = getSupportActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tb = findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.main1_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Main2_Login.class);
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
                ((TextView)findViewById(R.id.textView)).setText(("SEARCH"));
                return true;
            case R.id.action_account :
                ((TextView)findViewById(R.id.textView)).setText(("ACCOUNT"));
                return true;
            case R.id.action_settings :
                ((TextView)findViewById(R.id.textView)).setText(("SETTINGS"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}