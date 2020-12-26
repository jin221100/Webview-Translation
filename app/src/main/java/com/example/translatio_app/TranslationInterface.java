package com.example.translatio_app;

import android.webkit.WebView;
import android.widget.EditText;

public interface TranslationInterface {
    void Main(WebView webView, EditText editText,String tbtn);
    void Recive(WebView webView, EditText editText,String tbtn);
    void Translation(WebView webView,String tbtn);
    void Give(WebView webView,BeforeAfter beforeAfter);
}
