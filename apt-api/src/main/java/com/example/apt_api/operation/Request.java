package com.example.apt_api.operation;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class Request<M extends Model> {
    private M model;
    private IActivity iActivity;

    public Request(M model, IActivity iActivity) {
        this.model = model;
        this.iActivity = iActivity;
    }

    public M getModel() {
        return model;
    }

    public IActivity getIActivity() {
        return iActivity;
    }

    public void startActivity(String path) {
        Activity activity = iActivity.getActivity();
        if (activity != null) {
            try {
                activity.startActivity(new Intent(activity, Class.forName(path)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void showToast(String msg) {
        Activity activity = iActivity.getActivity();
        if (activity != null) {
            try {
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
