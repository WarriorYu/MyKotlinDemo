package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.view.SettingItemView;

public class SettingActivity extends AppCompatActivity {

    private SettingItemView st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        st = (SettingItemView) findViewById(R.id.st);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                st.toggle();
            }
        });
    }
}
