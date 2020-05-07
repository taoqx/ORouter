package com.example.apt_api.operation;

public class Entry<O extends Operation,M extends Model>{
    private Class<O> operationClass;
    private Class<M> modelClass;

    public Entry(Class<O> operationClass, Class<M> modelClass) {
        this.operationClass = operationClass;
        this.modelClass = modelClass;
    }

    public Class<O> getOperationClass() {
        return operationClass;
    }

    public void setOperationClass(Class<O> operationClass) {
        this.operationClass = operationClass;
    }

    public Class<M> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<M> modelClass) {
        this.modelClass = modelClass;
    }
}