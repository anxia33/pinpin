package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Fatie_Record extends AppCompatActivity {
//    Intent intent=getIntent();
//    int ID=intent.getIntExtra("ID",0);
    private int ID;
    class tiezi {
        private String start_time;
        private String end_time;
        private String title;
        private String body;
        private String where;
        private String qq;
        tiezi(String s_t, String o,String w,String c,String b,String e_t) {
            start_time = s_t;
            end_time=e_t;
            where=w;
            body=b;
            qq=c;
            title = o;
        }
        String gets_Time() {
            return start_time;
        }
        String gete_Time() {
            return end_time;
        }
        String getTitle() {
            return title;
        }
        String getWhere(){
            return where;
        }
        String getBody(){
            return body;
        }
        String getQq(){
            return qq;
        }
    }
    ArrayList<tiezi> list=new ArrayList<tiezi>();
    ArrayList<String> LIST=new ArrayList<String>();
    tiezi T;
    String title,body,start_time,where,qq,end_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        global g=new global();
        ID=g.ID;
        setContentView(R.layout.activity_fatie__record);
        new Thread() {
            public void run() {
                try {
                    HttpURLConnection conn = null;
                    String content = "user_id=" + ID ;
                    String str_url = "http://pinpin.utools.club/server/history?" + content;
                    URL url = new URL(str_url);

                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        Log.i("request", "get请求成功");
                        InputStream in = conn.getInputStream();
                        InputStreamToString change = new InputStreamToString();
                        String str = change.go(in);
                        Log.i("data", str);
                        in.close();

                        JSONObject json = new JSONObject(str);
                        int code = json.getInt("code");

                        if (code == 0) {
                            int i=0;
                            JSONArray array = json.getJSONArray("data");
                            int l=array.length();
                            while(i<l){
                                JSONObject data = array.getJSONObject(i);
                                Log.i("data", data.toString());
                                title = data.getString("title");//从数据库中获取标题
                                body=data.getString("note");//从数据库中获取详细信息
                                start_time = data.getString("start_time");//从数据库中获取发帖时间
                                where=data.getString("address");//从数据库中获取地点
                                qq=data.getString("contact");//从数据库中获取联系方式
                                end_time=data.getString("end_time");
                                T = new tiezi(start_time,title,where,qq,body,end_time);
                                list.add(T);
                                Log.i("len1", Integer.toString(list.size()));
                                i++;
                            }
                        }
                        Log.i("code", String.valueOf(sign_in.code));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i=0;
        int l=list.size();
        Log.i("len", Integer.toString(l));
        while(i<l){

            LIST.add("标题："+list.get(i).getTitle()+"\n"+
                    "地点："+list.get(i).getWhere()+"\n"+
                    "发帖时间："+list.get(i).gets_Time()+"\n"+
                    "截止时间："+list.get(i).gete_Time()+"\n");
            Log.i("list", list.get(i).getTitle());
            i++;
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                Fatie_Record.this , android.R.layout.simple_list_item_1,
                LIST
        );
        ListView listView=(ListView)findViewById(R.id.list_view1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,
                                    int position, long id){
                tiezi T=list.get(position);
                /*点击后的事件在这里写，跳转到链接之类的*/
                Intent intent1 = new Intent(Fatie_Record.this, detail_post.class);
                intent1.putExtra("title",T.getTitle());
                intent1.putExtra("body",T.getBody());
                intent1.putExtra("start_time",T.gets_Time());
                intent1.putExtra("end_time",T.gete_Time());
                intent1.putExtra("qq",T.getQq());
                intent1.putExtra("where",T.getWhere());
                startActivity(intent1);
            }
        });
    }
}
