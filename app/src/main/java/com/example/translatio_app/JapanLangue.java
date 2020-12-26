package com.example.translatio_app;

public class JapanLangue implements LangueChoose{
    @Override
    public String getCountry() {
        return "일본어";
    }

    @Override
    public String getlangue(String langue) {
        return "ja";
    }
}
