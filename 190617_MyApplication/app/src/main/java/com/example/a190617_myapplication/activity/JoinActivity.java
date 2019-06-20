package com.example.a190617_myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a190617_myapplication.R;

import java.net.MalformedURLException;

public class JoinActivity extends AppCompatActivity {
    private EditText join_id, join_pw;
    private Button join_join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        NetworkUtilForJoin.setNetworkPolicy();
        join_id = (EditText)findViewById(R.id.join_id);
        join_pw = (EditText)findViewById(R.id.join_pw);
        join_join = (Button)findViewById(R.id.join_join);
        join_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PHPRequestForJoin request = new PHPRequestForJoin("http://192.168.23.95/android/join.php");
                    String result = request.PhPtest(String.valueOf(join_id.getText()),String.valueOf(join_pw.getText()));
                    if(result.equals("1")){
                        Toast.makeText(getApplication(),"회원가입 완료:)",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplication(),"회원가입 실패:(",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}