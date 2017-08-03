package com.example.yuxibing.mykotlindemo.javacode.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.util.CircleUtil;

/**
 * Created by yuxibing on 2017/8/1.
 * 描述: 旋转菜单
 */

public class CircleMenu extends ViewGroup {
    private int width = 1080;
    private int defaultWidth;

    public CircleMenu(Context context) {
        this(context, null);
    }

    public CircleMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setData(int[] imgs, String[] strs) {
        for (int i = 0; i < imgs.length; i++) {
            View view = View.inflate(getContext(), R.layout.menu_item, null);
            ImageView img = (ImageView) view.findViewById(R.id.img);
            TextView textView = (TextView) view.findViewById(R.id.text);
            img.setImageResource(imgs[i]);
            textView.setText(strs[i]);
            addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth;
        int measureHeight;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            //确切的
            measureWidth = measureHeight = Math.min(size, getDefaultWidth());
        } else {
            //未指定或者wrap_content
            //获取背景的宽度，如果为0，则无背景
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            if (suggestedMinimumWidth == 0) {
                measureWidth = measureHeight = getDefaultWidth();
            } else {
                measureWidth = measureHeight = Math.min(suggestedMinimumWidth, getDefaultWidth());
            }
        }
        setMeasuredDimension(measureWidth, measureHeight);
        width = measureWidth;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(width / 6, MeasureSpec.EXACTLY);
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }
    }


    private int startAngle;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            float radius = width / 3.0f;
            int left = (int) (width / 2 + Math.round(radius * Math.cos(Math.toRadians(startAngle))) - childWidth / 2);
            int right = left + childWidth;
            int top = (int) (width / 2 + Math.round(radius * Math.sin(Math.toRadians(startAngle))) - childWidth / 2);
            int bottom = top + childWidth;
            child.layout(left, top, right, bottom);
            startAngle += 360 / childCount;
        }
    }

    private float lastX;
    private float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float angle;
                float start = CircleUtil.getAngle(lastX, lastY, width);
                float end = CircleUtil.getAngle(x, y, width);

                int quadrant = CircleUtil.getQuadrant(x, y, width);
                if (quadrant == 1 || quadrant == 4) {
                    angle = end - start;
                } else {
                    angle = start - end;
                }
                startAngle += angle;
                requestLayout();
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                lastX = x;
                lastY = y;
                break;
        }

        return true;

    }

    public int getDefaultWidth() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        return Math.min(widthPixels, heightPixels);
    }
}
