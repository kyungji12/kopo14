package com.example.a190617_myapplication.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.a190617_myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Main2_Login extends AppCompatActivity {
    //데이터를 받아올 PHP주소
    String url = "http://192.168.23.95/android/login.php";

    //데이터를 보기위한 TextView
    TextView textViewJudge;

    //PHP를 읽어올 때 사용할 변수
    public GettingPHP gPHP;
    boolean idCheck = false;
    List<AccountList> userList = new ArrayList<AccountList>();

    EditText idEdit, passEdit;
    String idStr, passStr;
    Button loginProcess;
    Toolbar toolbar;
    TextView join;
    Intent intent;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main2__login);

        idEdit = (EditText)findViewById(R.id.main2_id);
        passEdit = (EditText)findViewById(R.id.main2_pw);
        passEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        loginProcess = (Button)findViewById(R.id.main2_login);
        textViewJudge = (TextView)findViewById(R.id.main2_errText);

        join = (TextView)findViewById(R.id.main2_joinIn);
        join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });


        //툴바 사용설정
        toolbar = findViewById(R.id.main2_app_toolbar);
        setSupportActionBar(toolbar);  //이 액티비티에서 툴바를 사용하겠다는 선언.

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);

        loginProcess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                idStr = idEdit.getText().toString();
                passStr = passEdit.getText().toString();

                gPHP = new GettingPHP();
                gPHP.execute(url);


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
            super.onResume();
    }
//서버 연결할 때 필요한 쓰레드 AsyncTask
    class GettingPHP extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String...url){
            StringBuilder jsonHtml = new StringBuilder();
            try{
                URL phpUrl = new URL(url[0]);
                HttpURLConnection conn = (HttpURLConnection)phpUrl.openConnection();
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);

                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                        while (true){
                            String line = br.readLine();
                            if (line == null)
                                break;
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return jsonHtml.toString();
        }
        protected void onPostExecute(String str){
            /*서버 연결*/
            try{
                //PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                JSONObject jObject = new JSONObject(str);
                //results라는 key는 JSON배열로 되어있다.
                JSONArray results = jObject.getJSONArray("results");

                for (int i=0; i<results.length(); ++i){
                    JSONObject temp = results.getJSONObject(i);
                    userList.add(new AccountList(temp.get("ID").toString(),temp.get("Password").toString()));
                }

                for(AccountList user : userList){
                    if(idStr.equals(user.getAccountId()) && passStr.equals(user.getAccountPw())) idCheck = true;
                }

                if(idCheck == true) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("inputId", idStr);
                    intent.putExtra("inputPassword", passStr);
                    idCheck = false;
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "ID 또는 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }

            }catch(JSONException e){
                e.printStackTrace();
                Log.d("hello", e.toString());
                textViewJudge.setText("hello");
            }
        }
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

//            case R.id.home :
//            case R.id.action_threeline :
//                ((TextView)findViewById(R.id.main1_title)).setText(("MENU"));
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
