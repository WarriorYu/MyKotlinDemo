package com.example.yuxibing.mykotlindemo.javacode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yuxibing.mykotlindemo.R;

/**
 * Created by yuxibing on 2017/7/31.
 * 描述:
 */

public class CustomeViewGroup extends ViewGroup {
    public CustomeViewGroup(Context context) {
        this(context,null);
    }

    public CustomeViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomeViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(R.drawable.ic_launcher);
            addView(imageView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int count = getChildCount();
        int index = 0;
        int top = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j <=i ; j++) {
                View child = getChildAt(index);
                index++;
                child.layout(50*j,top,(j+1)*50,top+50);
            }
            top+=50;
        }
    }
}
