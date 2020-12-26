package com.example.translatio_app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface ApiClass {
    Thread th = null;
    static String Tvalue = "";
    String clientId = "EgbRJhQjs4fUDI5GsZ_P"; //애플리케이션 클라이언트 아이디값";
    String clientSecret = "mFEhh8qc8L"; //애플리케이션 클라이언트 시크릿값";
    String getApi(String args,String tbtn) throws InterruptedException;
    String post(String apiUrl, Map<String, String> requestHeaders, String text,String tbtn);
    HttpURLConnection connect(String apiUrl);
    String readBody(InputStream body);

}
