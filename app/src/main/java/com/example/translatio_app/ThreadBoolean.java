package com.example.translatio_app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThreadBoolean extends AppCompatActivity {
    public boolean run = false;
    public static Context context_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context_main = this;
        run = false;
    }
}
