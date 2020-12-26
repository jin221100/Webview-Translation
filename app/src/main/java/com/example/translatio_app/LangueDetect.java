package com.example.translatio_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class LangueDetect implements GsonClass{

    @Override
    public String getValue(String result)
    {
        Gson gson = new Gson();
        TranslateItem translateItem = gson.fromJson(result,TranslateItem.class);
        return translateItem.langCode;
    }
}
