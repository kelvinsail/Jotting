package com.yifan.jotting2.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.yifan.jotting2.R;
import com.yifan.jotting2.utils.ResourcesUtils;
import com.yifan.jotting2.utils.WidgetUtils;

/**
 * 带颜色标签的CardView
 * <p/>
 * Created by yifan on 2016/7/20.
 */
public class ColorCardView extends CardView {

//    Paint mPaint;

    public ColorCardView(Context context) {
        this(context, null);
    }

    public ColorCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAntiAlias(true);
//        mPaint.setDither(true);
//        mPaint.setFilterBitmap(true);
//        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setColor(ResourcesUtils.getColor(R.color.text_orange));
    }

}
