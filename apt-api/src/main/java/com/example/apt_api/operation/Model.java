package com.example.apt_api.operation;

public class Model {
    private String path = null;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Request createRequest() {
        return new Request<>(this);
    }
}
