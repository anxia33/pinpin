package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;





public class Pindan_Record extends AppCompatActivity {
//    Intent intent=getIntent();
//    int ID=intent.getIntExtra("ID",0);
    private int ID;
    class danzi {
        danzi(int ID,String o,String t) {
            id=ID;
            time=t;
            object=o;
        }
        int getId(){
            return id;
        }
        String getTime(){
            return time;
        }
        String getObject(){
            return object;
        }
        private int id;
        private String time;
        private String object;
    }
    ArrayList<danzi> list=new ArrayList<danzi>();
    ArrayList<String> LIST=new ArrayList<String>();
    private String[] data={"apple","23:41","orange","5:50",
            "pear","6:12","pineapple","8:50"};//测试数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pindan__record);
        global g=new global();
        ID=g.ID;
//        for(int i=0;i<8;i+=2/*判断条件改成数据库是否到末尾*/) {
//            String title = data[i];//从数据库中获取
//            String time = data[i+1];//从数据库中获取
//            danzi D = new danzi(ID,title,time);
//            list.add(D);
//            LIST.add(title+"\n"+ID+"\n"+time);
//        }
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
//                Pindan_Record.this , android.R.layout.simple_list_item_1,
//                LIST
//        );
//        ListView listView=(ListView)findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id){
//                danzi D=list.get(position);
//                /*点击后的事件在这里写，跳转到链接之类的*/
//                Toast.makeText(Pindan_Record.this,
//                        D.getObject()+"\n"+D.getTime(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
