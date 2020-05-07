package com.example;

import android.app.Activity;
import android.content.Intent;

import com.example.apt_annotation.ORouter;
import com.example.apt_api.operation.Operation;

@ORouter(path = "test")
public class TestOperation extends Operation<TestModel> {
    @Override
    public void start() {
        request.startActivity(request.getModel().des);
    }
}
