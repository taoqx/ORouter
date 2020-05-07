package com.example.apt_api.operation;

public class Request<M extends Model> {
    private M model;

    public Request(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }

}
