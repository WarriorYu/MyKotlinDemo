package com.example.yuxibing.mykotlindemo.javacode.view;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Scroller;

/**
 * Author   :   yuxibing
 * Create   :   2019-12-10
 * Describe :
 */
public class ScrollButton extends AppCompatButton {
    public ScrollButton(Context context) {
        this(context, null);
    }

    public ScrollButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    // 分别记录上次滑动的坐标
    private float mLastX = 0;
    private float mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取在点击的位置在屏幕中的坐标
        float x = event.getRawX();
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                float detX = x - mLastX;
                float detY = y - mLastY;
                float translationX = getTranslationX() + detX;
                float translationY = getTranslationY() + detY;
                setTranslationX(translationX);
                setTranslationY(translationY);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Scroller scroller = new Scroller(getContext());
        if (scroller.computeScrollOffset()) {
            getScrollX();
            invalidate();
            postInvalidate();
        }
    }
}
