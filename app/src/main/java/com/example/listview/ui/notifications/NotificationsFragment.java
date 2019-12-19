package com.example.listview.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.listview.Fatie_Record;
import com.example.listview.MainActivity;
import com.example.listview.Pindan_Record;
import com.example.listview.R;
import com.example.listview.global;
import com.example.listview.sign_in;

public class NotificationsFragment extends Fragment {

    private String value=new String();
    private int ID;
    private String username;
    private NotificationsViewModel notificationsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        global g=new global();
        ID=g.ID;
        username=g.username;
        value="用户名:"+username+"\n"+"用户ID:"+ID+"\n我的信誉:"+666;
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(value);
            }
        });
        Button button1=(Button)root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent(getActivity(), Pindan_Record.class);
                //intent1.putExtra("ID",ID);
                startActivity(intent1);
                Toast.makeText(getActivity(),"用户ID："+ID,Toast.LENGTH_SHORT).show();
            }
        });
        Button button2=(Button)root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent2 = new Intent(getActivity(), Fatie_Record.class);
                //intent2.putExtra("ID",ID);
                startActivity(intent2);
                Toast.makeText(getActivity(),"用户ID："+ID,Toast.LENGTH_SHORT).show();
            }
        });
        Button button3=(Button)root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(),
                        "账号已注销",Toast.LENGTH_LONG
                        ).show();
                Intent intent3 = new Intent(getActivity(), sign_in.class);
                startActivity(intent3);
                getActivity().finish();
            }
        });
        return root;
    }
}