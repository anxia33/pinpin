package com.example.listview.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listview.Fatie_Record;
import com.example.listview.Pindan_Record;
import com.example.listview.R;
import com.example.listview.global;
import com.example.listview.post_show;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView post_show;
    private int ID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        global g=new global();
        ID=g.ID;
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button button_b=(Button)view.findViewById(R.id.button_b);//拼书
        button_b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String Sort="拼书";
                Intent intent1 = new Intent(getActivity(), post_show.class);
                intent1.putExtra("extra_data",Sort);
                startActivity(intent1);
                Toast.makeText(getActivity(),"用户ID："+ID,Toast.LENGTH_SHORT).show();
            }
        });
        Button button_f=(Button)view.findViewById(R.id.button_f);//拼餐饮
        button_f.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String Sort="拼餐饮";
                Intent intent2 = new Intent(getActivity(), post_show.class);
                intent2.putExtra("extra_data",Sort);
                startActivity(intent2);
            }
        });
        Button button_u=(Button)view.findViewById(R.id.button_u);//拼伞
        button_u.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String Sort="拼伞";
                Intent intent3 = new Intent(getActivity(), post_show.class);
                intent3.putExtra("extra_data",Sort);
                startActivity(intent3);
            }
        });
        Button button_o=(Button)view.findViewById(R.id.button_o);//拼伞
        button_o.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String Sort="其他";
                Intent intent4 = new Intent(getActivity(), post_show.class);
                intent4.putExtra("extra_data",Sort);
                startActivity(intent4);
            }
        });
        return view;
    }
}