package com.example.apt_api.operation;

import android.app.Activity;

public abstract class Operation<M extends Model> {
    public abstract void start();

    protected Request<M> request;

    public void setRequest(Request<M> request) {
        this.request = request;
    }
}
