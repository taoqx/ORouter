package com.example.apt_api.operation;

import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;

public class ORouter {
    private static final HashMap<String, Entry> caches = new HashMap<>(100);

    public static void init() {
        //绑定所有operations 和 models
        try {
            Class clazz = Class.forName("com.example.operation.ORouter$Map");
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String path = field.getName();
                String value = (String) field.get(null);
                if (value != null) {
                    Class operationClass = Class.forName(value);
                    ParameterizedType type = (ParameterizedType) operationClass.getGenericSuperclass();
                    bind(path, operationClass, (Class) type.getActualTypeArguments()[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(IActivity iActivity, String content) {
        Model model = findModel(content);
        Request request = model.createRequest(iActivity);
        Operation operation = findOperation(request);
        if (operation != null) {
            operation.start();
        }
    }

    public static <O extends Operation, M extends Model> void bind(String path, Class<O> operationClass, Class<M> modelClass) {
        Entry entry = new Entry<>(operationClass, modelClass);
        caches.put(path, entry);
    }

    private static Model findModel(String content) {
        try {
            Gson gson = new Gson();
            Model model = gson.fromJson(content, Model.class);
            String path = model.getPath();
            Entry<?, ?> entry = caches.get(path);
            if (entry != null) {
                model = gson.fromJson(content, entry.getModelClass());
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Model();
    }

    private static Operation findOperation(Request request) {
        Entry<?, ?> entry = caches.get(request.getModel().getPath());
        if (entry != null) {
            Class<? extends Operation> clazz = entry.getOperationClass();
            try {
                Constructor<? extends Operation> constructor = clazz.getConstructor();
                Operation operation = constructor.newInstance();
                operation.setRequest(request);
                return operation;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}


