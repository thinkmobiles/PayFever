package com.payfever.presentation.custom_views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.animation.ValueAnimatorCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * Created by richi on 2015.11.04..
 */
public class SplashView extends View {

    private int mDx;
    private int mColorRectHight, mColorRectWidht;
    private int[] mColors = new int[13];
    private boolean mIsStarted;

    private Paint mPaint;
    private ValueAnimator mValueAnimator;
    private Random mRandom;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
        initColors();
        initValueAnimator();
//        startAnimation();
    }

    private void initPaint() {
        mRandom = new Random();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void initColors() {
        int difference = 255 / 13;
        for (int i = 0; i < mColors.length; i++) {
            mColors[i] = i * difference;
        }
    }

    private void initValueAnimator() {
        mValueAnimator = ValueAnimator.ofInt(0, 255);
        mValueAnimator.setDuration(200000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(mUpdateListener);
    }

    private ValueAnimator.AnimatorUpdateListener mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mDx = (Integer) animation.getAnimatedValue();
            invalidate();
        }
    };

    private void startAnimation() {
        if (mIsStarted)
            return;
        mIsStarted = true;
        mValueAnimator.start();
    }

    private void stopValueAnimator() {
        mIsStarted = false;
        mValueAnimator.cancel();
    }

    public boolean isPlaying() {
        return mIsStarted;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateColorRectSize(w, h);
    }

    private void calculateColorRectSize(int _w, int _h) {
        mColorRectHight = _h / mColors.length;
        mColorRectWidht = _w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawColorRects(canvas);
        startAnimation();
    }

    private void drawColorRects(Canvas _canvas) {
        Rect rect = new Rect();
        for (int i = 0; i < mColors.length; i++) {
            rect.set(0, i * mColorRectHight, mColorRectWidht,
                    ((i * mColorRectHight) + mColorRectHight));
            int color = getColor(mColors[i]);
            mPaint.setColor(color);
            mColors[i] = color;
            _canvas.drawRect(rect, mPaint);
        }
    }

    private int getColor(int _c) {
        if (_c + mDx > 255)
            _c = mDx + _c - 255;
        else
            _c += mDx;

        if (_c == 0)
            _c = 1;

        int r = mRandom.nextInt(3);
        int g = mRandom.nextInt(3);

        if (_c + r > 255)
            r = 255;
        else
            r += _c;

        if (_c + g > 255)
            g = 255;
        else
            g += _c;

        return Color.argb(255, _c, r, g);
    }

    private void redrawIfNeed() {
        if (mIsStarted)
            invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopValueAnimator();
        super.onDetachedFromWindow();
    }
}
