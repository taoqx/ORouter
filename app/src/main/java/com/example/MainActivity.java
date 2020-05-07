package com.example;

import android.app.Activity;
import android.os.Bundle;

import com.example.apt_api.operation.Operators;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Operators.init();
        Operators.start("{\n" +
                "\"path\":\"test\",\n" +
                "\"message\":\"it is test message\"\n" +
                "}");
    }
}
