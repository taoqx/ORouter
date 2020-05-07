package com.example;

import com.example.apt_annotation.ORouter;
import com.example.apt_api.operation.Operation;

@ORouter(path = "test2")
public class TestOperation2 extends Operation<TestModel2> {
    @Override
    public void start() {
        request.showToast(request.getModel().msg);
    }
}
