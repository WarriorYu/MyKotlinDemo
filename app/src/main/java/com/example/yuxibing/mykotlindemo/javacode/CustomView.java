package com.example.yuxibing.mykotlindemo.javacode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yuxibing on 2017//27.
 * 描述:自定义TextView介绍
 */

public class CustomView extends View {
    private Paint paint;
    //方式一:
    /*
    //用于代码中动态创建自定义控件实例
    public CustomTextView(Context context) {
        super(context);
        init();
    }
    //用于布局文件中使用该自定义控件
    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //用于布局文件中使用该自定义控件，并且加入样式
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    */

    //方式二:只需要在第三个调用init()方法
    //用于代码中动态创建自定义控件实例
    public CustomView(Context context) {
        this(context,null);
    }
    //用于布局文件中使用该自定义控件
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    //用于布局文件中使用该自定义控件，并且加入样式
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.e("test", "初始化");
        //新建画笔
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    //用于封装x,y点的坐标
    private PointF facePoint = new PointF(300, 500);
    private int faceRadius = 200;
    private PointF line1Point = new PointF(200,380);
    private PointF line2Point = new PointF(400,380);
    private PointF line3Point = new PointF(300,380);
    private PointF line4Point = new PointF(300,600);
    private PointF line5Point = new PointF(200,500);
    private PointF line6Point = new PointF(250,450);
    private PointF line7Point = new PointF(350,450);

    private int airRadius = 50;







    //在绘制阶段调用该方法 参数：canvas 画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //float cx, float cy, float radius, @NonNull Paint paint 绘制圆
        canvas.drawCircle(facePoint.x,facePoint.y,faceRadius,paint);
        //float startX, float startY, float stopX, float stopY, @NonNull Paint paint
        canvas.drawLine(line1Point.x,line1Point.y,line2Point.x,line2Point.y,paint);
        canvas.drawLine(line3Point.x,line3Point.y,line4Point.x,line4Point.y,paint);
        canvas.drawLine(line4Point.x,line4Point.y,line5Point.x,line5Point.y,paint);
        canvas.drawCircle(line6Point.x,line6Point.y,airRadius,paint);
        canvas.drawCircle(line7Point.x,line7Point.y,airRadius,paint);


    }
}
