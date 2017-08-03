package com.example.yuxibing.mykotlindemo.javacode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuxibing.mykotlindemo.R;

/**
 * Created by yuxibing on 2017/8/3.
 * 描述:
 */

public class SettingItemView extends RelativeLayout {
    private boolean isToggle = false;
    private ImageView img;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = View.inflate(getContext(), R.layout.setting_item_view, this);
        TextView textview = (TextView) this.findViewById(R.id.text);
        img = (ImageView) this.findViewById(R.id.img);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        int bg = typedArray.getInt(R.styleable.SettingItemView_my_background, 0);
        String title = typedArray.getString(R.styleable.SettingItemView_my_title);
        boolean isToggle = typedArray.getBoolean(R.styleable.SettingItemView_is_toggle, false);
        switch (bg) {
            case 1:
                view.setBackgroundResource(R.drawable.middle);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.last);
                break;
            default:
                view.setBackgroundResource(R.drawable.first);
                break;
        }
        textview.setText(title);
        setToggle(isToggle);
        typedArray.recycle();
    }

    public void toggle() {
        setToggle(!isToggle);
    }


    private void setToggle(boolean isToggle) {
        img.setBackgroundResource(isToggle ? R.drawable.on : R.drawable.off);
        this.isToggle = isToggle;
    }

}
