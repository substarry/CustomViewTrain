package com.substarry.customviewtrain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 何凌 on 2016/4/29.
 */
public class CustomViewPorterDuffColor extends View{

    private Paint mPaint;
    private Context mContext;
    private int maxRadiu;
    private Bitmap bitmap;// 位图
    private int x,y;
    private boolean isClick;// 用来标识控件是否被点击过

    public CustomViewPorterDuffColor(Context context) {
        this(context, null);
    }

    public CustomViewPorterDuffColor(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initRes();
        initPaint();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 判断控件是否被点击过
                 */
                if (isClick) {
                    // 如果已经被点击了则点击时设置颜色过滤为空还原本色
                    mPaint.setColorFilter(null);
                    isClick = false;
                } else {
                    // 如果未被点击则点击时设置颜色过滤后为红色+暗
                    mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));
                    isClick = true;
                }

                // 记得重绘
                invalidate();
            }
        });
    }


    private void initPaint(){
        // 实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);
//
    /*
     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
        mPaint.setStyle(Paint.Style.FILL);

        // 设置画笔颜色为浅灰色
//        mPaint.setColor(Color.argb(255, 255, 128, 103));

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
//        mPaint.setStrokeWidth(10);

    }

    /**
     * 初始化资源
     */
    private void initRes() {
        // 获取位图
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.a, options);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //去除padding值后，获得有效半径
        int realWidthRadiu = (widthSize - getPaddingLeft() - getPaddingRight())/2;
        int realHeightRadiu = (heightSize - getPaddingTop() - getPaddingBottom())/2;
        maxRadiu = Math.min(realWidthRadiu, realHeightRadiu);


         /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        x=  getPaddingLeft();
        y = getPaddingTop();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, mPaint);
    }

}
