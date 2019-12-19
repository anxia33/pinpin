package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.net.*;

import org.json.*;

public class sign_in extends AppCompatActivity implements View.OnClickListener {

    private EditText editText1, editText2;
    static int code;
    static int g_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }//这句判断是如果你的页面继承自AppCompatActivity必须加这句

        setContentView(R.layout.sign_in_layout);

        Button sign_in = (Button) findViewById(R.id.sign_in);
        Button sign_out = (Button) findViewById(R.id.sign_out);
        editText1 = (EditText) findViewById(R.id.username);
        editText2 = (EditText) findViewById(R.id.password);
        sign_in.setOnClickListener(this);
        sign_out.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in:
                String user = editText1.getText().toString(); //用户名
                String passward = editText2.getText().toString();//密码
                if (user.isEmpty() || passward.isEmpty())
                    Toast.makeText(sign_in.this, "账号或密码为空", Toast.LENGTH_SHORT).show();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String user_name = editText1.getText().toString(); //用户名
                            String user_pwd = editText2.getText().toString();//密码

                            HttpURLConnection conn = null;
                            String content = "user_name=" + user_name + "&user_pwd=" + user_pwd;
                            String str_url = "https://pinpin.utools.club/server/login?" + content;
                            URL url = new URL(str_url);

                            conn = (HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("Content-Type", "application/json");
                            //以字符串形式拿到返回的东西

                            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                Log.i("request", "get请求成功");
                                InputStream in = conn.getInputStream();
                                InputStreamToString change = new InputStreamToString();
                                String str = change.go(in);
                                Log.i("data", str);
                                in.close();

                                JSONObject json = new JSONObject(str);
                                sign_in.code = json.getInt("code");

                                if (sign_in.code == 0) {
                                    //登录成功
                                    sign_in.g_id = json.getJSONObject("data").getInt("id");
                                }
                                Log.i("code", String.valueOf(sign_in.code));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (sign_in.code == 0) {
                    //登录成功
                    Log.i("sign_in.code", String.valueOf(sign_in.code));
                    Intent intent = new Intent(sign_in.this, MainActivity.class);//跳转至主界面
                    startActivity(intent);
                    global.ID=sign_in.g_id;
                    Toast.makeText(sign_in.this, "登录成功,用户ID:" + sign_in.g_id, Toast.LENGTH_SHORT).show();
                    sign_in.this.finish();
                } else if (sign_in.code == -1) {
                    //用户不存在
                    Log.i("sign_in.code", String.valueOf(sign_in.code));
                    Toast.makeText(sign_in.this, "用户不存在" , Toast.LENGTH_SHORT).show();
                } else if (sign_in.code == -2) {
                    //密码错误
                    Log.i("sign_in.code", String.valueOf(sign_in.code));
                    Toast.makeText(sign_in.this, "密码错误" , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sign_out:
                Intent intent=new Intent(sign_in.this,sign_out.class);
                startActivity(intent);
            default:
                break;
        }
    }

}
