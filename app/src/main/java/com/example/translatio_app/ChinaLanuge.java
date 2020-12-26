package com.example.translatio_app;

public class ChinaLanuge implements LangueChoose{
    @Override
    public String getlangue(String langue) {
        return "zh-cn";
    }

    @Override
    public String getCountry() {
        return "중국어";
    }
}
