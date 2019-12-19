package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class detail_post extends AppCompatActivity {

    private TextView p_title,p_body,p_where,p_time;
    private String con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_post_layout);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");//从上一个活动得到
        String body=intent.getStringExtra("body");//从上一个活动得到单子的详情
        String where=intent.getStringExtra("where");//从上一个活动得到单子的地点
        String start_time=intent.getStringExtra("start_time");//从上一个活动得到单子的发帖时间
        String end_time=intent.getStringExtra("end_time");//从上一个活动得到单子的发帖时间
        String qq=intent.getStringExtra("qq");//从上一个活动得到单子的联系方式
        con=qq;
        Button contact=(Button)findViewById(R.id.contact);
        Button report=(Button)findViewById(R.id.report);
        p_title=(TextView) findViewById(R.id.p_title);
        p_body=(TextView) findViewById(R.id.p_body);
        p_where=(TextView) findViewById(R.id.p_where);
        p_time=(TextView) findViewById(R.id.p_time);
        p_title.setText(title);
        p_body.setText(body);
        p_time.setText("时间："+start_time+"-"+end_time);
        p_where.setText("地点："+where);
        contact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin="+con;//uin是发送过去的qq号码
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    Toast.makeText(detail_post.this,con,Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
        report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(detail_post.this, Jubao.class);
                startActivity(intent2);
            }
        });
    }
}
