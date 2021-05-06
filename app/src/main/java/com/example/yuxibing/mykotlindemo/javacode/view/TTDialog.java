package com.example.yuxibing.mykotlindemo.javacode.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.yuxibing.mykotlindemo.R;


/**
 * Created by yu on 2016/12/27.
 * 自定义标题、内容、确定按钮的Dialog
 */
public class TTDialog extends Dialog {


    private Context mContext;
    private OnResultListener resultListener;
    private String titleStr;
    private String contentStr;

    public interface OnResultListener {
        void onResult(boolean confirmed);
    }

    public TTDialog(Context context, String titleStr, String contentStr, OnResultListener listener) {
        super(context, 0);
        this.mContext = context;
        this.resultListener = listener;
        this.titleStr = titleStr;
        this.contentStr = contentStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        // 修改Dialog(Window)的弹出位置
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        window.setAttributes(layoutParams);

        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        this.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (resultListener != null) {
            resultListener.onResult(true);
        }
    }

}
