package com.example.listview.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.listview.InputStreamToString;
import com.example.listview.R;
import com.example.listview.global;
import com.example.listview.sign_in;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private EditText time;
    private EditText title;
    private EditText addr;
    private EditText tel;
    private EditText detail;
    private Spinner spinner;
    private Button b1;
    private int ID;
    int code=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*Bundle bundle=this.getArguments();
        ID=bundle.getInt("ID");*/
        global g=new global();
        ID=g.ID;
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Button b1=(Button) view.findViewById(R.id.issue);
        title = (EditText) view.findViewById(R.id.title);
        time = (EditText) view.findViewById(R.id.time);
        addr = (EditText) view.findViewById(R.id.addr);
        tel = (EditText) view.findViewById(R.id.tel);
        detail = (EditText) view.findViewById(R.id.detail);
        spinner=(Spinner) view.findViewById(R.id.sort);
        spinner = (Spinner) view.findViewById(R.id.sort);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        String title1 = title.getText().toString();
                        String time1 = time.getText().toString();
                        String addr1 = addr.getText().toString();
                        String tel1 = tel.getText().toString();
                        String sort =spinner.getSelectedItem().toString();//获取类别
                        String type=null;
                        switch (sort){
                            case "拼书":type="shuben";break;
                            case "拼伞":type="san";break;
                            case "拼餐饮":type="canyin";break;
                            case "其他":type="qita";break;
                        }
                        String detail1 = detail.getText().toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                        String futime  = formatter.format(curDate);
                        try {
                            HttpURLConnection conn = null;
                            String content = "user_id=" + ID + "&type=" +
                                    type + "&title=" + title1 + "&address=" + addr1
                                    + "&contact=" + tel1 + "&start_time=" + futime
                                    + "&end_time=" + time1+ "&note=" + detail1;
                            String str_url = "http://pinpin.utools.club/server/fatie?" + content;
                            URL url = null;
                            url = new URL(str_url);
                            conn = (HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("Content-Type", "application/json");
                            Log.d("code", Integer.toString(conn.getResponseCode()));
                            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                InputStream in = conn.getInputStream();
                                InputStreamToString change = new InputStreamToString();
                                String str = change.go(in);
                                Log.d("str", str);

                                in.close();
                                Log.i("request", "get请求成功");

                                JSONObject json = null;
                                json = new JSONObject(str);
                                code = json.getInt("code");


                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(code==0){
                    Toast.makeText(getActivity(),"发布成功，用户ID："+ID,Toast.LENGTH_SHORT).show();
                    title.setText("");
                    time.setText("");
                    tel.setText("");
                    detail.setText("");
                    addr.setText("");
                }
            }
        });


        return view;
    }
    }