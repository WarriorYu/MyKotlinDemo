package com.example.yuxibing.mykotlindemo.javacode.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IInterface;
import android.os.Messenger;
import android.os.RemoteCallbackList;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.yuxibing.mykotlindemo.R;
import com.example.yuxibing.mykotlindemo.javacode.model.Food;
import com.example.yuxibing.mykotlindemo.javacode.themedemo.DownLoadThemeService;
import com.example.yuxibing.mykotlindemo.javacode.view.TTDialog;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private TextView img;
    private GestureDetector gestureDetector;
    private static final String[] STORAGE_AND_PHONE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE};
    private static final int RC_STORAGE_PHONE_PERM = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = "1.0";
//        int i = Integer.parseInt(a);
        double b = 9.0f;
        Number num = b;
        int i = num.intValue();
        Log.e("aaaaaa", i + "哈哈");
        setTransparentBar(200, 0);
        img = (TextView) findViewById(R.id.img);
        //饼状图
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomViewActivity.class));

            }
        });

        //自定义控件
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewGroupActivity.class));
            }
        });

        //圆形菜单
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CircleMenuActivity.class));
            }
        });

        //自定义自合控件
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//                ObjectAnimator.ofFloat(img, "translationY", -img.getHeight()).start();
                /*ValueAnimator colorAnim = ObjectAnimator.ofInt(img, "backgroundColor", *//*Red*//*0xFFFF8080, *//*Blue*//*0xFF8080FF);
                colorAnim.setDuration(3000);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                colorAnim.start();*/
                /*AnimatorSet set = new AnimatorSet();
                set.playTogether(
                        ObjectAnimator.ofFloat(img, "rotationX", 0, 360),
                        ObjectAnimator.ofFloat(img, "rotationY", 0, 180),
                        ObjectAnimator.ofFloat(img, "rotation", 0, -90),
                        ObjectAnimator.ofFloat(img, "translationX", 0, 90),
                        ObjectAnimator.ofFloat(img, "translationY", 0, 90),
                        ObjectAnimator.ofFloat(img, "scaleX", 1, 1.5f),
                        ObjectAnimator.ofFloat(img, "scaleY", 1, 0.5f),
                        ObjectAnimator.ofFloat(img, "alpha", 1, 0.25f, 1)
                );
                set.setDuration(5 * 1000).start();*/
//                animate(img).setDuration(2000).rotationYBy(100).x(100).y(100).start();
                ObjectAnimator animator = ObjectAnimator.ofFloat(img, "translationX", 360);
                animator.setDuration(1000);
                animator.start();
               /* ObjectAnimator animator = ObjectAnimator.ofFloat(img,"rotationX",0,270,0);
                animator.setDuration(2000);
                animator.start();*/

            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MallCategoryActivity.class));
            }
        });
        findViewById(R.id.btn5).setOnClickListener(v -> startActivity(new Intent(this, ArtPracticeActivity.class)));


        findViewById(R.id.btn6).setOnClickListener(v -> requestReadWritePermission());

        String[] strings = fileList();
        for (String filename : strings) {
            Log.e("文件", filename);
        }

    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTransparentBar(@ColorInt int color, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            //View.SYSTEM_UI_FLAG_LAYOUT_STABLE：保持整个View稳定, 常和控制System UI悬浮, 隐藏的Flags共用, 使View不会因为System UI的变化而重新layout
            decorView.setSystemUiVisibility(option);

            int finalColor = alpha == 0 ? Color.TRANSPARENT :
                    Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));

//            window.setNavigationBarColor(finalColor);
            window.setStatusBarColor(finalColor);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            int finalColor = alpha == 0 ? Color.TRANSPARENT : Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            decorView.addView(createStatusBarView(this, finalColor));
            /*if (navigationBarExist(this)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                decorView.addView(createNavBarView(this, finalColor));
            }*/
        }

    }

    private View createStatusBarView(Context context, @ColorInt int color) {
        View mStatusBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
        params.gravity = Gravity.TOP;
        mStatusBarTintView.setLayoutParams(params);
        mStatusBarTintView.setBackgroundColor(color);
        return mStatusBarTintView;
    }

    private View createNavBarView(Context context, @ColorInt int color) {
        View mNavBarTintView = new View(context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight(context));
        params.gravity = Gravity.BOTTOM;
        mNavBarTintView.setLayoutParams(params);
        mNavBarTintView.setBackgroundColor(color);
        return mNavBarTintView;
    }


    private boolean navigationBarExist(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }


    @ColorInt
    private int calculateColor(@ColorInt int color, int alpha) {
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }


    private void setRootView(Activity activity, boolean fit) {
        ViewGroup parent = (ViewGroup) activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(fit);
                ((ViewGroup) childView).setClipToPadding(fit);
            }
        }
    }


    private int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public int getNavigationHeight(Context context) {

        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }


    @AfterPermissionGranted(RC_STORAGE_PHONE_PERM)
    private void requestReadWritePermission() {
        if (hasReadWritePermission()) {
            String themeUrl = "http://guanfu-file.oss-cn-beijing.aliyuncs.com/theme/new_year_190118_online.zip";
            DownLoadThemeService.startDowloadService(this, themeUrl);
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, "需要存储权限", RC_STORAGE_PHONE_PERM, STORAGE_AND_PHONE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private boolean hasReadWritePermission() {
        return EasyPermissions.hasPermissions(this, STORAGE_AND_PHONE);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.e("权限", "被禁止");
    }
}
