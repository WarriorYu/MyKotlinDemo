package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Scroller;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.view.ScrollButton;

/**
 * Author   : yuxibing
 * Create   : 2019-12-11
 * Describe : 通过动画的方式，实现随意拖拽控件（ScrollButton）移动
 */
public class ScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        Button btnTranslate = findViewById(R.id.btn_translate);
        btnTranslate.setOnClickListener(v ->
//                findViewById(R.id.text_translate).startAnimation(animation));
                ObjectAnimator.ofFloat(findViewById(R.id.text_translate), "translationX", 0, 100).setDuration(1000).start());
        btnTranslate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


}
