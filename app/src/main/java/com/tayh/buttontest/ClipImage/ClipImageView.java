package com.tayh.buttontest.ClipImage;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


public class ClipImageView extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;
    int width;
    int height;
    final int hang = 11;//定义行数
    final int lie = 11;//定义列数

    public ClipImageView(Context context) {
        super(context);
    }

    public ClipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ClipImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("zeyu","onDraw");
        width = getWidth();
        height = getHeight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("zeyu","onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("zeyu","onMeasure");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (width > 0 && height > 0 && event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            int area = 0;
            int selectI = 1;
            int selectJ = 1;
            for (int i = 1; i <= hang; i++) {
                if (x > width / hang * i) {
                    continue;
                }
                selectI = i;
                for (int j = 1; j <= lie; j++) {
                    if (y > height / lie * j) {
                        continue;
                    }
                    selectJ = j;
                    break;
                }
                break;
            }
            area = selectI + (selectJ - 1) * hang;
            ((ClipActivity) mContext).showClickArea(area);
        }
        return super.onTouchEvent(event);
    }
}
