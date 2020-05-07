package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.apt_api.operation.IActivity;
import com.example.apt_api.operation.ORouter;
import com.example.operation.R;


public class MainActivity extends Activity implements IActivity {
    String content = "{\n" +
            "\"path\":\"test\",\n" +
            "\"des\":\"com.example.TestActivity\"\n" +
            "}";

    String content2 = "{\n" +
            "\"path\":\"test2\",\n" +
            "\"msg\":\"this is a msg\"\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ORouter.init();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ORouter.start(MainActivity.this, content);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ORouter.start(MainActivity.this, content2);
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
