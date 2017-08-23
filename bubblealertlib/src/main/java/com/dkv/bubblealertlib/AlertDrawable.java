package com.dkv.bubblealertlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;

/**
 * @author DKV.
 */
public class AlertDrawable extends Drawable {

    private Paint mBackGroundPaint = null;
    private Paint mCircleFillPaint = null;
    private Paint mOuterCircleFillPaint = null;
    private Path borderPath = null;
    private Rect borderRect = null;
    private int circleRadius = 0;
    private int outerCircleOffset = 0;
    private int textSize = 0;
    private String mDrawText = "!";
    private Bitmap bitmapIcon = null;
    private Context mContext;


    public AlertDrawable(int outerCircleRadiusDP, int textSizeSP, int cornerArc, String drawText, Context context) {
        mContext = context;

        circleRadius = ScreenUtils.unitToPixels(context, TypedValue.COMPLEX_UNIT_DIP, outerCircleRadiusDP);
        textSize = ScreenUtils.unitToPixels(context, TypedValue.COMPLEX_UNIT_SP, textSizeSP);
        mDrawText = TextUtils.isEmpty(drawText) ? " " : drawText;
        initBitmap(mDrawText);
        mBackGroundPaint = new Paint();
        mBackGroundPaint.setAntiAlias(true);
        mBackGroundPaint.setDither(true);
        mBackGroundPaint.setColor(Color.WHITE);
        mBackGroundPaint.setStyle(Paint.Style.FILL);


        mCircleFillPaint = new Paint();
        mCircleFillPaint.setAntiAlias(true);
        mCircleFillPaint.setDither(true);
        mCircleFillPaint.setColor(ContextCompat.getColor(context, R.color.colorMultiArc));
        mCircleFillPaint.setStyle(Paint.Style.FILL);

        mOuterCircleFillPaint = new Paint();
        mOuterCircleFillPaint.setAntiAlias(true);
        mOuterCircleFillPaint.setDither(true);
        mOuterCircleFillPaint.setColor(Color.WHITE);
        mOuterCircleFillPaint.setStyle(Paint.Style.FILL);
        mOuterCircleFillPaint.setTextSize(textSize);


        borderPath = new Path();
        borderRect = new Rect();

        CornerPathEffect cornerPathEffect = new CornerPathEffect(cornerArc);
        mBackGroundPaint.setPathEffect(cornerPathEffect);

        outerCircleOffset = context.getResources().getInteger(R.integer.outerCircleOffset);
    }

    private void initBitmap(String mDrawText) {

        int resourceID = R.drawable.popup_tick;

        try {


            switch (mDrawText) {

                case ConstantsIcons.ALERT_ICON_SUCCESS:

                    resourceID = R.drawable.popup_tick;
                    break;

                case ConstantsIcons.ALERT_ICON_COMMON_INFO:

                    resourceID = R.drawable.popup_info;
                    break;
                case ConstantsIcons.ALERT_ICON_INFO:

                    resourceID = R.drawable.popup_excl;
                    break;
                case ConstantsIcons.ALERT_ICON_ERROR:

                    resourceID = R.drawable.popup_close;
                    break;

            }

            bitmapIcon = BitmapFactory.decodeResource(mContext.getResources(), resourceID);

        } catch (Exception ex) {

            AppLog.logErrorString(ex.getMessage());
            bitmapIcon = null;
        }

    }


    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        borderRect.set(bounds);
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.clipRect(borderRect);
        processPath(borderPath);
        canvas.drawPath(borderPath, mBackGroundPaint);
        canvas.drawCircle(borderRect.right / 2, borderRect.top + circleRadius, circleRadius, mOuterCircleFillPaint);
        canvas.drawCircle(borderRect.right / 2, borderRect.top + circleRadius, circleRadius - outerCircleOffset, mCircleFillPaint);

        float textHeight = mOuterCircleFillPaint.descent() - mOuterCircleFillPaint.ascent();
        float textOffset = (textHeight / 2) - mOuterCircleFillPaint.descent();
        if (null != bitmapIcon)
            canvas.drawBitmap(bitmapIcon, borderRect.right / 2 - bitmapIcon.getWidth() / 2,
                    borderRect.top + circleRadius - bitmapIcon.getHeight() / 2, mBackGroundPaint);
        //canvas.drawText(mDrawText, borderRect.right / 2 - (mOuterCircleFillPaint.measureText(mDrawText)/2), borderRect.top + circleRadius + textOffset, mOuterCircleFillPaint);
    }

    private void processPath(Path borderPath) {

        borderPath.moveTo(borderRect.left, borderRect.top + circleRadius);
        borderPath.lineTo(borderRect.right, borderRect.top + circleRadius);
        borderPath.lineTo(borderRect.right, borderRect.bottom);
        borderPath.lineTo(borderRect.left, borderRect.bottom);
        borderPath.lineTo(borderRect.left, borderRect.top + circleRadius);
        borderPath.lineTo(borderRect.right, borderRect.top + circleRadius);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
