package com.example.translatio_app;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PapagoTranslation implements TranslationInterface{
    HtmlControl javaScriptInterface;
    ApiClass apiClass = new PapagoApi();
    ApiClass apiClasstranslation = new PapagoTranslationApi();

    public PapagoTranslation(HtmlControl javaScriptInterface) {
        this.javaScriptInterface = javaScriptInterface;
    }

    @Override
    public void Main(WebView webView, EditText editText,String tbtn) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                Recive(webView,editText,tbtn);
            }
        });


    }

    @Override
    public void Recive(WebView webView, EditText editText,String tbtn) {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println(tbtn+"값 보김");

                super.onPageFinished(view, url);
                editText.setHint(url);
                view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML);"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                Translation(view,tbtn);
            }
        });

    }
    Thread th;
    ArrayList<BeforeAfter> value;
    @Override
    public void Translation(WebView webView,String tbtn) {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean once = false;
                value = new ArrayList<>();

                while (true) {
                    try {

                        if (javaScriptInterface.run() == false) {
                            for(int i =0;i<javaScriptInterface.Htmlvalue().size(); i++)
                            {

                                if(!javaScriptInterface.Htmlvalue().get(i).equals("")) {
                                    if (javaScriptInterface.Htmlvalue().get(i).contains("class=") && javaScriptInterface.Htmlvalue().get(i).contains(">")) {
                                        int target_num = javaScriptInterface.Htmlvalue().get(i).indexOf("class=");
                                        int target_numend = javaScriptInterface.Htmlvalue().get(i).indexOf(">");
                                    }
                                    String langue = apiClass.getApi(javaScriptInterface.Htmlvalue().get(i),tbtn);
                                    if (!langue.contains("message"))
                                    {
                                        GsonClass gsonClass = new LangueDetect();
                                        if (gsonClass.getValue(langue).equals(tbtn)) {

                                            String result = apiClasstranslation.getApi(javaScriptInterface.Htmlvalue().get(i),tbtn);

                                            gsonClass = new TranslationGson();
                                            if(!result.contains("errorMessage")) {
                                                String trnvalue = gsonClass.getValue(result);
                                                BeforeAfter beforeAfter = new BeforeAfter(javaScriptInterface.Htmlvalue().get(i), trnvalue);
                                                value.add(beforeAfter);
                                                Give(webView,beforeAfter);

                                            }
                                        }
                                    }
                                }
                            }
                            javaScriptInterface.setRun();
                        }

                        th.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        th.start();

    }

    @Override
    public void Give(WebView webView,BeforeAfter beforeAfter) {
        //th.interrupt();
        webView.post(new Runnable() {
            @Override
            public void run() {
                String str = "javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML = document.getElementsByTagName('html')[0].innerHTML.replace('" + beforeAfter.before +
                        "','" + beforeAfter.after +
                        "'));";
                //webView.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML = document.getElementsByTagName('html')[0].innerHTML.replace('１　突然の解雇通告','블랙마도구사 길드를 추방당한 나'));"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                //webView.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML = document.getElementsByTagName('html')[0].innerHTML.replace('ギルド長の言葉に、私は言葉を失うことになった。','에요'));"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                webView.loadUrl(str); //<html></html> 사이에 있는 모든 html을 넘겨준다.
            }
        });

        /*
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String str = "javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML = document.getElementsByTagName('html')[0].innerHTML.replace('" + beforeAfter.before +
                        "','" + beforeAfter.after +
                        "'));";
                System.out.println(str + "STR번");
                //view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML = document.getElementsByTagName('html')[0].innerHTML.replace('１　突然の解雇通告','xx'));"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                view.loadUrl(str); //<html></html> 사이에 있는 모든 html을 넘겨준다.
            }
        });

        /*
        webView.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("들어감!ㅁ");
                //String jqurey = "$('." + beforeAfter.tag + "').html('" + beforeAfter.after + "')";
                String jqurey = "$('." + "novel_view" + "').html('" + "하이!" + "')";
                webView.loadUrl(jqurey);
                System.out.println(jqurey + "jquery확");
            }
        });

         */

    }
}
class BeforeAfter
{
    String before;
    String after;

    public BeforeAfter(String before, String after)
    {
        this.before = before;
        this.after = after;
    }
}
