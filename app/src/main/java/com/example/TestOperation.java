package com.example;

import com.example.apt_annotation.ORouter;
import com.example.apt_api.operation.Operation;

@ORouter(path = "test")
public class TestOperation extends Operation<TestModel> {
    @Override
    public void start() {
        System.out.println("TestOperation started message is " + request.getModel().message);
    }
}
