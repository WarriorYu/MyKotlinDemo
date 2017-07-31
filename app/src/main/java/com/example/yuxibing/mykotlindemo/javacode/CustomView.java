package com.example.yuxibing.mykotlindemo.javacode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by yuxibing on 2017//27.
 * 描述:自定义View介绍
 */

public class CustomView extends View {
    private Paint paint;
    private Path path;
    private int radius;
    private Paint linePaint;
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
        this(context, null);
    }

    //用于布局文件中使用该自定义控件
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        path = new Path();

        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(3);
        linePaint.setAntiAlias(true);
        linePaint.setTextSize(30);
    }

    //用于封装x,y点的坐标
    private PointF facePoint = new PointF(300, 500);
    private int faceRadius = 200;
    private PointF line1Point = new PointF(200, 380);
    private PointF line2Point = new PointF(400, 380);
    private PointF line3Point = new PointF(300, 380);
    private PointF line4Point = new PointF(300, 600);
    private PointF line5Point = new PointF(200, 500);
    private PointF line6Point = new PointF(250, 450);
    private PointF line7Point = new PointF(350, 450);

    private int airRadius = 50;

    private RectF mouth = new RectF(180, 400, 420, 650);
    private float startAngle = 40f;
    private float sweepAngle = 100f;
    private RectF airLeft = new RectF(80, 400, 120, 600);
    private float[] ptrs = {30, 50, 60, 50, 100, 200};


    //在绘制阶段调用该方法 参数：canvas 画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
   /*     canvas.save();
        canvas.translate(50, 50);
        canvas.scale(0.5f, 0.5f);
        canvas.rotate(-30f);
        //float cx, float cy, float radius, @NonNull Paint paint 绘制圆
        canvas.drawCircle(facePoint.x, facePoint.y, faceRadius, paint);
        //float startX, float startY, float stopX, float stopY, @NonNull Paint paint
        canvas.drawLine(line1Point.x, line1Point.y, line2Point.x, line2Point.y, paint);
        canvas.drawLine(line3Point.x, line3Point.y, line4Point.x, line4Point.y, paint);
        canvas.drawLine(line4Point.x, line4Point.y, line5Point.x, line5Point.y, paint);
        canvas.drawCircle(line6Point.x, line6Point.y, airRadius, paint);
        canvas.drawCircle(line7Point.x, line7Point.y, airRadius, paint);
        //@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint
        canvas.drawArc(mouth, startAngle, sweepAngle, false, paint);
//        canvas.drawRect(airLeft, paint);
//        canvas.drawPoints(ptrs,1,ptrs.length-1,paint);
        path.moveTo(125, 400);
        path.lineTo(80, 400);
        path.lineTo(80, 600);
        path.lineTo(125, 600);
//        path.close();
        canvas.drawPath(path, paint);

//        path.moveTo(500, 400);
//        path.quadTo(600,450,500,500);
//        path.quadTo(600,450,400,600);
//        canvas.drawPath(path,paint);
        canvas.restore();

       *//* paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(40f);
        paint.setStrikeThruText(true);
        paint.setUnderlineText(true);
        paint.setColor(Color.BLUE);
        paint.setFakeBoldText(true);
//        paint.setTextScaleX(2);
        canvas.drawText("你好",400,800,paint);*/

        canvas.save();
        canvas.translate(width / 2, height / 2);
        draPie(canvas);

        canvas.restore();

    }

    private float totalValue;
    List<PieEntity> list;

    public void setData(List<PieEntity> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            totalValue += list.get(i).value;
        }
    }


    private RectF pieRectF;
    private float startActAngle;
    private float sweepArcAngle;

    private void draPie(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < list.size(); i++) {
            paint.setColor(list.get(i).color);
            path.moveTo(0, 0);
            sweepArcAngle = list.get(i).value / totalValue * 360 - 1;
            path.arcTo(pieRectF, startActAngle, sweepArcAngle);
            canvas.drawPath(path, paint);

            //绘制直线
            double a = startActAngle + sweepArcAngle / 2;
            float startX = (float) (radius * Math.cos(Math.toRadians(a)));
            float startY = (float) (radius * Math.sin(Math.toRadians(a)));
            float endX = (float) ((radius + 30) * Math.cos(Math.toRadians(a)));
            float endY = (float) ((radius + 30) * Math.sin(Math.toRadians(a)));
            canvas.drawLine(startX, startY, endX, endY, linePaint);

            startActAngle += sweepArcAngle + 1;
            path.reset();
            //绘制文本
            String percent = String.format("%.1f", list.get(i).value / totalValue * 100)+"%";
            float textWidth = linePaint.measureText(percent);
            if (startActAngle % 360.0f >= 90.0f && startActAngle % 360.0f <= 270.0f) {
                canvas.drawText(percent, endX - textWidth, endY, linePaint);
            } else {
                canvas.drawText(percent, endX, endY, linePaint);
            }



        }
    }


    private int width;
    private int height;

    //当自定义控件的尺寸已经决定好的时候回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radius = (int) (Math.min(w, h) * 0.7 / 2);
        pieRectF = new RectF(-radius, -radius, radius, radius);
    }
}
