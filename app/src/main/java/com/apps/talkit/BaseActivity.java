package com.apps.talkit;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    public Context getTalkitContext(){
        return getBaseContext();
    }
}