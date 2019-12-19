package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Jubao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jubao);
        Button jubao=(Button)findViewById(R.id.button);
        final EditText edt=(EditText)findViewById(R.id.jubao);
        jubao.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String JB=edt.getText().toString();
                if(JB.length()==0)
                    Toast.makeText(Jubao.this,
                            "请输入举报详情！",Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(Jubao.this,
                        "已成功提交，请耐心等待客服答复",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
