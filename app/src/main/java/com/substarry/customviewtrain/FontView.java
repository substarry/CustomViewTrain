package com.substarry.customviewtrain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 何凌 on 2016/5/3.
 */
public class FontView extends View {

    private static final String TEXT = "ab哈喽12gji";
    private Paint mPaint;// 画笔
    private Paint.FontMetrics mFontMetrics;// 文本测量对象


    public FontView(Context context) {
        this(context, null);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);

        mFontMetrics = mPaint.getFontMetrics();

        Log.d("Aige", "ascent：" + mFontMetrics.ascent);
        Log.d("Aige", "top：" + mFontMetrics.top);
        Log.d("Aige", "leading：" + mFontMetrics.leading);
        Log.d("Aige", "descent：" + mFontMetrics.descent);
        Log.d("Aige", "bottom：" + mFontMetrics.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(TEXT, 0, Math.abs(mFontMetrics.top), mPaint);
    }

}
