package com.demo.lucar.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {

    public static <T> T parseJson(String jsonData, Class<T> classOf) throws IllegalAccessException, InstantiationException {
        Gson gson = new Gson();
        T object = gson.fromJson(jsonData, classOf);
        return object;
    }

    public static <T> T parseJson(InputStream in, Class<T> classOf) throws IllegalAccessException, InstantiationException {
        JsonReader reader = null;
        reader = new JsonReader(new InputStreamReader(in));
        Gson gson = new Gson();
        T object = gson.fromJson(reader, classOf);
        return object;
    }

    public static <T> List<T> getObjectList(String jsonString, Class<T> classOf){
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, classOf));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> String toString(T object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}