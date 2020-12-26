package com.example.translatio_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class MainActivity extends AppCompatActivity {

    CustomWebView webView;
    String langue = "ja";
    LangueInterface langueChoose = new LangueClass();
    String url = "";

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack())
        {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.framelayout);

        webView = (CustomWebView)findViewById(R.id.webviewer);
        /*
        webView.setButton(frameLayout);
        webView.getSettings().setJavaScriptEnabled(true);
        HtmlControl htmlControl = new HtmlControl();
        webView.addJavascriptInterface(htmlControl,"Android");
        TranslationInterface translationInterface = new PapagoTranslation(htmlControl);
        EditText editText = (EditText)findViewById(R.id.httpedittext);
        translationInterface.Main(webView,editText,"en");
        webView.loadUrl("https://ncode.syosetu.com/n8120gp/1/");
        Button button = (Button)findViewById(R.id.tbtn);
        langue = langueChoose.languechoose(0).getlangue("");
        button.setText(langueChoose.languechoose(0).getCountry());
        final int[] cnt = {0};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt[0] +=1;
                if(cnt[0] == 3)
                {
                    cnt[0] = 0;
                }
                button.setText(langueChoose.languechoose(cnt[0]).getCountry());
                translationInterface.Main(webView,editText,langueChoose.languechoose(cnt[0]).getlangue(""));
                webView.loadUrl(webView.getUrl());
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i)
                {
                    case IME_ACTION_SEARCH :
                        translationInterface.Main(webView,editText,button.toString());
                        webView.loadUrl(textView.toString());
                        webView.EditTextHint(editText,textView.toString());
                        break;
                }
                return true;
            }
        });

         */
        EditText editText = (EditText)findViewById(R.id.httpedittext);
        Button button = (Button)findViewById(R.id.tbtn);

        webView.setTranslationInterface();
        webView.UrlLink(button,editText);
        webView.LangueChange(button,editText);
    }

}
