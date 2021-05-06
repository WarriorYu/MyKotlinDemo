package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.Service;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuxibing.mykotlindemo.R;

import java.util.Comparator;
import java.util.PriorityQueue;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);



        TextView textView = new TextView(this);
        textView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        GradientDrawable gradientDrawable = new GradientDrawable();

        ImageView img = findViewById(R.id.img);
        TextView tv = findViewById(R.id.tv);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionDrawable background = (TransitionDrawable) tv.getBackground();
                background.reverseTransition(2000);

            }
        });

        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Dialog dialog = new Dialog(this);
        TextView view = new TextView(this);
        view.setText("呵呵呵呵呵");
        dialog.setContentView(view);
        dialog.show();
        ContentResolver contentResolver = getContentResolver();

    }

    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
        }

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }


}
