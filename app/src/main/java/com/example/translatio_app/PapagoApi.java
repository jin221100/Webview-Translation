package com.example.translatio_app;

import android.util.Log;

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

import static com.example.translatio_app.ApiClass.Tvalue;

public class PapagoApi implements ApiClass{

    static String Tvalue = "";
    Thread th;

    @Override
    public String getApi(String args,String tbtn) throws InterruptedException {

        final String[] responseBody = {""};
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                String query;

                try {
                    query = URLEncoder.encode(args, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("인코딩 실패", e);
                }
                String apiURL = "https://openapi.naver.com/v1/papago/detectLangs";

                Map<String, String> requestHeaders = new HashMap<>();
                requestHeaders.put("X-Naver-Client-Id", ApiClass.clientId);
                requestHeaders.put("X-Naver-Client-Secret", ApiClass.clientSecret);

                //responseBody[0] = post(apiURL, requestHeaders, query);
                Tvalue = post(apiURL, requestHeaders, query, tbtn);

            }
        });
        th.start();
        th.join();

        return Tvalue;
    }

    @Override
    public String post(String apiUrl, Map<String, String> requestHeaders, String text, String tbtn) {
        HttpURLConnection con = connect(apiUrl);
        String postParams =  "query="  + text; //원본언어: 한국어 (ko) -> 목적언어: 영어 (en)
        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postParams.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    @Override
    public HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    @Override
    public String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }


}
