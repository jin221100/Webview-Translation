package com.example.translatio_app;

public class LangueClass implements LangueInterface{
    @Override
    public LangueChoose languechoose(int cnt) {
        if(cnt == 0)
        {
            return new JapanLangue();
        }
        else if(cnt == 1)
        {
            return new EnglishLangue();
        }
        else if(cnt == 2)
        {
            return new ChinaLanuge();
        }
        return null;
    }

}
