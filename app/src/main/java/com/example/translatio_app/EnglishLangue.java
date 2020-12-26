package com.example.translatio_app;

public class EnglishLangue implements LangueChoose{
    @Override
    public String getCountry() {
        return "영어";
    }

    @Override
    public String getlangue(String langue) {
        return "en";
    }
}
