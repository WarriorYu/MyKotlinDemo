package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.view.CircleMenu;

public class CircleMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);
        int[] imgs = {R.drawable.home_mbank_1_clicked, R.drawable.home_mbank_2_clicked, R.drawable.home_mbank_3_clicked, R.drawable.home_mbank_4_clicked, R.drawable.home_mbank_5_clicked, R.drawable.home_mbank_6_clicked};
        String[] strs = {"我的账户","信用卡","爱好","你说呢","投资理财","哈哈"};
        CircleMenu menu = (CircleMenu) findViewById(R.id.menu);
        menu.setData(imgs,strs);
    }
}
