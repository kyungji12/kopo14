package com.example.a190617_myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.a190617_myapplication.R;
import java.net.MalformedURLException;

//회원가입용 Class
public class JoinActivity extends AppCompatActivity {
    private EditText join_id, join_pw;      //회원가입할 때 쓸 아이디, 비밀번호
    private Button join_join;               //회원가입 버튼
    Intent intent;                          //페이지전환과 페이지간 데이터 전달용
    TextView home;                          //STYLERA 누르면 홈으로 이동

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        NetworkUtilForJoin.setNetworkPolicy();

        //STYLERA 누르면 홈으로 이동하기
        home = (TextView)findViewById(R.id.join_title);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //회원가입 처리
        join_id = (EditText)findViewById(R.id.join_id);
        join_pw = (EditText)findViewById(R.id.join_pw);
        join_pw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD); //비밀번호 가리기
        join_join = (Button)findViewById(R.id.join_join);

        join_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intent = new Intent(getApplicationContext(), Main2_Login.class);
                    PHPRequestForJoin request = new PHPRequestForJoin("http://192.168.23.95/android/join.php");
                    String result = request.PhPtest(String.valueOf(join_id.getText()),String.valueOf(join_pw.getText()));
                    if(result.equals("1")){
                        Toast.makeText(getApplication(),"회원가입 완료:) 로그인해주세요.",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplication(),"회원가입 실패:( 다른 ID를 선택해주세요.",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
