package com.bwf.tuanche.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zengqiang on 2016/8/15.
 * Description:ViewPagerIndcator
 */
public class ViewPagerIndecator extends LinearLayout {

    private Paint mPaint;

    private Path mPath;

    private int mTrangleWidth;

    private int mTrangleHeight;

    private static final float RADIO_TRANGLE_WIDTH = 1 / 6f;

    private int mTranslationX;

    private int mInitTranslationX;

    public ViewPagerIndecator(Context context) {
        this(context, null);
    }

    public ViewPagerIndecator(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();

        canvas.translate(mInitTranslationX + mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);

        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTrangleWidth = (int) (w / 3 * RADIO_TRANGLE_WIDTH);

        mInitTranslationX = w / 3 / 2 - 2 * mTrangleWidth;

        initTrangle();
    }

    /**
     * 初始化三角形
     */
    private void initTrangle() {
        mTrangleHeight = mTrangleWidth * 2 / 3;
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTrangleWidth, 0);
        mPath.lineTo(mTrangleWidth / 2, -mTrangleHeight + 5);
        mPath.close();
    }


    /**
     * 指示器跟随手指滚动
     *
     * @param position
     * @param positionOffset
     */
    public void scroll(int position, float positionOffset) {

        int tabWidth = getWidth() / 3;
        mTranslationX = (int) (tabWidth * (position + positionOffset));  //计算偏移量
        invalidate();  //三角形重绘

    }
}
