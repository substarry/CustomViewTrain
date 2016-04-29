package com.substarry.customviewtrain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 何凌 on 2016/4/29.
 */
public class CustomViewDynamicRing extends View{

    private Paint mPaint;
    private Context mContext;
    private int currentRadiu;
    private int maxRadiu;
    private int x,y;

    public CustomViewDynamicRing(Context context) {
        this(context, null);
    }

    public CustomViewDynamicRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initPaint();
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
        mPaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.LTGRAY);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //去除padding值后，获得有效半径
        int realWidthRadiu = (widthSize - getPaddingLeft() - getPaddingRight())/2;
        int realHeightRadiu = (heightSize - getPaddingTop() - getPaddingBottom())/2;
        maxRadiu = currentRadiu = Math.min(realWidthRadiu, realHeightRadiu);

        x = (widthSize -getPaddingRight() + getPaddingLeft())/2;
        y = (heightSize -getPaddingBottom() + getPaddingTop())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y , currentRadiu, mPaint);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                if(currentRadiu < maxRadiu){
                    currentRadiu += 5;

                }
                else{
                    currentRadiu = 0;
                }
                postInvalidate();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    public Runnable getRunnable() {
        return runnable;
    }
}
