package com.example.translatio_app;

import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.ArrayList;

public class HtmlControl {
    ArrayList<String> result = new ArrayList<>();
    public boolean run = true;

    public Boolean run() {
        return run;
    }

    public ArrayList<String> Htmlvalue() {
        return result;
    }


    public void setRun() {
        run = true;
    }

    @JavascriptInterface
    public void getHtml(String html) {
        String plus = "";
        try {
            String value2 = removeTag(html);
            String[] value = value2.split("\\n");

            System.out.println(html);

            for(int i =0; i< value.length; i++)
            {
                if(!value[i].contains("<!--"))
                {
                    if(!value[i].contains("//-->")) {
                        if (!value[i].contains("<meta")) {
                            result.add(value[i]);

                        }
                    }
                }
                if(i == value.length-1)
                {
                    run=false;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String removeTag(String html) {
        return html.replaceAll("<(/)?(!)?([a-zA-Z]*[1-5]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");

    }
}
