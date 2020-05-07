package com.example.apt_api.operation;

public abstract class Operation<M extends Model> {
    public abstract void start();

    protected Request<M> request;

    public void setRequest(Request<M> request) {
        this.request = request;
    }
}
