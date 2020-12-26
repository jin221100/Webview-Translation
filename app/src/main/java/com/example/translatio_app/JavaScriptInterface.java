package com.example.translatio_app;

import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;

public interface JavaScriptInterface {
    Boolean run();
    ArrayList<String> result = new ArrayList<>();
    void getHtml(String html);
    String removeTag(String html);
    ArrayList<String> Htmlvalue();
    void setRun();


}
