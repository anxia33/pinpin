package com.example.listview;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamToString {
    String str;

    public String go(InputStream in) {
        try {
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            str = sb.toString();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
