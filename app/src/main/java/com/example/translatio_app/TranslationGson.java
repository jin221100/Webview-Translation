package com.example.translatio_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TranslationGson implements GsonClass{
    @Override
    public String getValue(String result) {
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonElement rootObj = parser.parse(result.toString())
//원하는 데이터 까지 찾아 들어간다.
                .getAsJsonObject().get("message")
                .getAsJsonObject().get("result");
//안드로이드 객체에 담기
        TranslateItem items = gson.fromJson(rootObj.toString(), TranslateItem.class);

        return items.translatedText;
    }
}
