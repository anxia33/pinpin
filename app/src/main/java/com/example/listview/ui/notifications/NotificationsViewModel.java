package com.example.listview.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        String username="TinyGod"; //从数据库中按用户ID读取
        int credit=658;            //从数据库中按用户ID读取
        mText = new MutableLiveData<>();
        mText.setValue("用户名:"+username+"\n"+"我的信誉："+credit);
    }

    public LiveData<String> getText() {
        return mText;
    }
}