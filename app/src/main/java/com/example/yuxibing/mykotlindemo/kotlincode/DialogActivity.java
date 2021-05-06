package com.example.yuxibing.mykotlindemo.kotlincode;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.view.TTDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        new TTDialog(getApplicationContext(), "haha", "haha", new TTDialog.OnResultListener() {
            @Override
            public void onResult(boolean confirmed) {
                if (confirmed) {
                    Log.e("tag", "a");
                } else {
                    Log.e("tag", "b");
                }
            }
        }).show();



    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
