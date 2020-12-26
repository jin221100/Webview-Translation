package com.example.translatio_app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class CustomWebView extends WebView {
    TranslationInterface translationInterface;
    Context context;

    String langue = "ja";
    LangueInterface langueChoose = new LangueClass();

    public CustomWebView(Context context, TranslationInterface translationInterface) {
        super(context);
        this.translationInterface = translationInterface;
        this.context = context;
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }
    public void UrlLink(Button button,EditText urledit)
    {
        WebView webView = this;
        translationInterface.Main(webView,urledit,"en");

        urledit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch (i)
                {
                    case IME_ACTION_SEARCH :
                        translationInterface.Main(webView,urledit,button.toString());
                        webView.loadUrl(textView.toString());
                        EditTextHint(urledit,textView.toString());
                        break;
                }
                return true;
            }
        });
    }
    public void LangueChange(Button button,EditText urledit)
    {
        WebView webView = this;
        langue = langueChoose.languechoose(1).getlangue("");
        button.setText(langueChoose.languechoose(1).getCountry());
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
                translationInterface.Main(webView,urledit,langueChoose.languechoose(cnt[0]).getlangue(""));
                webView.loadUrl(webView.getUrl());
            }
        });
    }
    public void setTranslationInterface()
    {
        WebView webView = this;
        webView.getSettings().setJavaScriptEnabled(true);
        HtmlControl htmlControl = new HtmlControl();
        webView.addJavascriptInterface(htmlControl,"Android");
        translationInterface = new PapagoTranslation(htmlControl);


        webView.loadUrl("https://ncode.syosetu.com/n8120gp/1/");
    }
    public void EditTextHint(EditText editText,String str)
    {
        editText.setHint(str);
    }
}
