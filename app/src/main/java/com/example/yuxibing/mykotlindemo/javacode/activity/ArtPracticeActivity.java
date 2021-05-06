package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.yuxibing.mykotlindemo.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author   : yuxibing
 * Create   : 2019-12-11
 * Describe : Android开发艺术探索练习
 */
public class ArtPracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_practice);
        findViewById(R.id.btn_1).setOnClickListener(v->startActivity(new Intent(this,ScrollViewActivity.class)));
        findViewById(R.id.btn_2).setOnClickListener(v->startActivity(new Intent(this,CustomActivity.class)));
        new LruCache<>(Runtime.getRuntime().maxMemory()/8){
            @Override
            protected int sizeOf(@NonNull Object key, @NonNull Object value) {
                return super.sizeOf(key, value);
            }
        };
        Thread.setDefaultUncaughtExceptionHandler();
    }


}
