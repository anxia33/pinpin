package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class sign_out extends AppCompatActivity {
    private EditText editText;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    static int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }//这句判断是如果你的页面继承自AppCompatActivity必须加这句
        setContentView(R.layout.sign_out_layout);

        Button regist = (Button) findViewById(R.id.button_1);
        editText = (EditText) findViewById(R.id.edit_text);
        editText1 = (EditText) findViewById(R.id.edit_text1);
        editText2 = (EditText) findViewById(R.id.edit_text2);
        editText3 = (EditText) findViewById(R.id.edit_text3);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String student_no = editText1.getText().toString();//学号
                String user_name = editText.getText().toString();//用户名
                String user_pwd = editText2.getText().toString();//密码
                String user_pwd2 = editText3.getText().toString();
                if (student_no.length() == 0 || user_name.length() == 0 || user_pwd.length() == 0)
                    Toast.makeText(sign_out.this, "用户名、密码、学号不能为空", Toast.LENGTH_SHORT).show();
                if( !(user_pwd.equals(user_pwd2)) ) {
                    sign_out.code = -3;
                }
                else {
                    new Thread() {
                        public void run() {
                            try {
                                String student_no = editText1.getText().toString();//学号
                                String user_name = editText.getText().toString();//用户名
                                String user_pwd = editText2.getText().toString();//密码
                                String user_pwd2 = editText3.getText().toString();

                                HttpURLConnection conn = null;
                                String content = "student_no=" + student_no + "&user_name=" +
                                        user_name + "&user_pwd=" + user_pwd;
                                String str_url = "https://pinpin.utools.club/server/register?" + content;
                                URL url = new URL(str_url);

                                conn = (HttpURLConnection) url.openConnection();
                                conn.setConnectTimeout(5000);
                                conn.setRequestMethod("GET");
                                conn.setRequestProperty("Content-Type", "application/json");
                                //以字符串形式拿到返回的东西

                                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                    Log.i("PostGetUtil", "get请求成功");
                                    InputStream in = conn.getInputStream();
                                    InputStreamToString change = new InputStreamToString();
                                    String str = change.go(in);
                                    Log.i("PostGetUtil", str);
                                    in.close();

                                    JSONObject json = new JSONObject(str);
                                    sign_out.code = json.getInt("code");
                                    Log.i("code", String.valueOf(sign_in.code));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sign_out.code == 0) {
                    Toast.makeText(sign_out.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(sign_out.code == -1){
                    Toast.makeText(sign_out.this, "该学号已注册过", Toast.LENGTH_SHORT).show();
                }
                else if(sign_out.code == -2){
                    Toast.makeText(sign_out.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
                else if(sign_out.code == -3){
                    Toast.makeText(sign_out.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}