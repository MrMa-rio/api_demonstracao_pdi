package com.criar.pdi.demonstracao.utils.JSON;

import com.google.gson.Gson;

public class JsonConverter {
    private static final Gson gson = new Gson();
    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }
}
