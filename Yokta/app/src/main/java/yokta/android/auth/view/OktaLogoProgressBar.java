// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

// Referenced classes of package yokta.android.auth.view:
//            AutosizeTextView

public class OktaLogoProgressBar extends AutosizeTextView
{

    private static final int INNER_LEVEL = 2;
    private static final int OUTER_LEVEL = 0;
    private static final float OUTER_RINGS_WIDTH_RATIO = 0.225F;
    private static final int PROGRESS_LEVEL = 1;
    private static final float RING_WIDTH_RATIO = 0.075F;
    private int mBlueGradientStartColor;
    private Paint mBlueStrokePaint;
    private int mBluetGradientEndColor;
    private int mInnerShadowWidth;
    private Bitmap mLogoBitmap;
    private int mMax;
    private int mOuterShadowWidth;
    private int mProgress;
    private int mProgressColor;
    private ProgressColorsMode mProgressColorMode;
    private int mProgressExpireMark;
    private int mProgressExpiredColor;
    private Paint mProgressPaint;
    private Paint mShadowInnerPaint;
    private Paint mShadowOuterPaint;
    private int mStrokeWidth;

    public OktaLogoProgressBar(Context context)
    {
        super(context);
        mProgressColorMode = ProgressColorsMode.ColorOnTransparent;
        mInnerShadowWidth = 1;
        mOuterShadowWidth = 1;
        mStrokeWidth = 1;
        mProgressExpireMark = 0;
        init(context, null, 0);
    }

    public OktaLogoProgressBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mProgressColorMode = ProgressColorsMode.ColorOnTransparent;
        mInnerShadowWidth = 1;
        mOuterShadowWidth = 1;
        mStrokeWidth = 1;
        mProgressExpireMark = 0;
        init(context, attributeset, 0);
    }

    public OktaLogoProgressBar(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mProgressColorMode = ProgressColorsMode.ColorOnTransparent;
        mInnerShadowWidth = 1;
        mOuterShadowWidth = 1;
        mStrokeWidth = 1;
        mProgressExpireMark = 0;
        init(context, attributeset, i);
    }

    private static Canvas bmCleanCanvas(Bitmap bitmap)
    {
        bmClear(bitmap);
        return new Canvas(bitmap);
    }

    private static void bmClear(Bitmap bitmap)
    {
        bitmap.eraseColor(0);
    }

    private static void bmDrawOval(Bitmap bitmap, RectF rectf, int i)
    {
        bmDrawOval(bitmap, rectf, newPaint(i));
    }

    private static void bmDrawOval(Bitmap bitmap, RectF rectf, Paint paint)
    {
        bmCleanCanvas(bitmap).drawOval(rectf, paint);
    }

    private static void bmDrawRing(Bitmap bitmap, Canvas canvas, RectF rectf, RectF rectf1, Paint paint)
    {
        Paint paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setFilterBitmap(false);
        int i = saveCanvasLayer(canvas, new RectF(bmGetRect(bitmap)));
        bmDrawOval(bitmap, rectf, paint);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint1);
        paint1.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
        bmDrawOval(bitmap, rectf1, -1);
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint1);
        canvas.restoreToCount(i);
    }

    private static void bmFillBkGradient(Bitmap bitmap, int i, int j)
    {
        Canvas canvas = bmCleanCanvas(bitmap);
        int ai[] = {
            i, j
        };
        GradientDrawable gradientdrawable = new GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, ai);
        gradientdrawable.setGradientType(0);
        gradientdrawable.setDither(true);
        gradientdrawable.setBounds(bmGetRect(bitmap));
        gradientdrawable.draw(canvas);
    }

    private static Rect bmGetRect(Bitmap bitmap)
    {
        return new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    private void calculateProgressColor()
    {
        int i = mProgressColor;
        if (mProgress > mProgressExpireMark)
        {
            int j = mProgressColor;
            int k = mProgressExpiredColor;
            int l = Color.alpha(k) - Color.alpha(j);
            int i1 = Color.red(k) - Color.red(j);
            int j1 = Color.green(k) - Color.green(j);
            int k1 = Color.blue(k) - Color.blue(j);
            int l1 = mMax - mProgressExpireMark;
            double d = (1.0D * (double)(mProgress - mProgressExpireMark)) / (double)l1;
            i = Color.argb((int)((double)Color.alpha(j) + d * (double)l), (int)((double)Color.red(j) + d * (double)i1), (int)((double)Color.green(j) + d * (double)j1), (int)((double)Color.blue(j) + d * (double)k1));
        }
        mProgressPaint = newPaint(i);
    }

    private static RectF deflateRect(RectF rectf, float f)
    {
        RectF rectf1 = new RectF(rectf);
        rectf1.left = f + rectf1.left;
        rectf1.right = rectf1.right - f;
        rectf1.top = f + rectf1.top;
        rectf1.bottom = rectf1.bottom - f;
        return rectf1;
    }

    private static RectF inflateRect(RectF rectf, float f)
    {
        RectF rectf1 = new RectF(rectf);
        rectf1.left = rectf1.left - f;
        rectf1.right = f + rectf1.right;
        rectf1.top = rectf1.top - f;
        rectf1.bottom = f + rectf1.bottom;
        return rectf1;
    }

    private void init(Context context, AttributeSet attributeset, int i)
    {
        setGravity(17);
        setLines(1);
        mInnerShadowWidth = getResources().getDimensionPixelSize(0x7f050005);
        mOuterShadowWidth = getResources().getDimensionPixelSize(0x7f050006);
        mStrokeWidth = getResources().getDimensionPixelSize(0x7f050007);
        mBlueGradientStartColor = getResources().getColor(0x7f04000b);
        mBluetGradientEndColor = getResources().getColor(0x7f04000a);
        mBlueStrokePaint = newPaintFromResource(0x7f04000c);
        mShadowInnerPaint = newPaintFromResource(0x7f04000d);
        mShadowOuterPaint = newPaintFromResource(0x7f04000e);
        mProgressColor = context.getResources().getColor(0x7f04000f);
        mProgressExpiredColor = 0xff00ff00;
        calculateProgressColor();
        setProgressColorsMode(ProgressColorsMode.ColorOnTransparent);
        mMax = 100;
        mProgress = 33;
    }

    private static Paint newPaint(int i)
    {
        Paint paint = new Paint();
        paint.setColor(i);
        paint.setAntiAlias(true);
        return paint;
    }

    private Paint newPaintFromResource(int i)
    {
        return newPaint(getContext().getResources().getColor(i));
    }

    private static int saveCanvasLayer(Canvas canvas, RectF rectf)
    {
        return canvas.saveLayer(rectf, null, 31);
    }

    RectF calculateCircle(int i)
    {
        float f = 0.075F * (float)i * (float)getWidth();
        float f1 = 0.075F * (float)i * (float)getHeight();
        return new RectF(f, f1, (float)getWidth() - f, (float)getHeight() - f1);
    }

    protected void createLogoBitmap()
    {
        if (mLogoBitmap == null || mLogoBitmap.getWidth() != getWidth() || mLogoBitmap.getHeight() != getHeight())
        {
            if (mLogoBitmap != null)
            {
                mLogoBitmap.recycle();
            }
            mLogoBitmap = Bitmap.createBitmap(getWidth(), getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            drawLogoBitmap(mLogoBitmap);
        }
    }

    protected void drawLogoBitmap(Bitmap bitmap)
    {
        Canvas canvas = new Canvas(bitmap);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        RectF rectf = new RectF(0.0F, 0.0F, getWidth(), getHeight());
        RectF rectf1 = calculateCircle(0);
        RectF rectf2 = calculateCircle(1);
        RectF rectf3 = calculateCircle(2);
        RectF rectf4 = new RectF(rectf1);
        RectF rectf5 = deflateRect(rectf4, mOuterShadowWidth);
        RectF rectf6 = deflateRect(rectf5, mInnerShadowWidth);
        RectF rectf7 = deflateRect(rectf6, mStrokeWidth);
        RectF rectf8 = inflateRect(rectf2, mStrokeWidth);
        RectF rectf9 = deflateRect(rectf8, mStrokeWidth);
        RectF rectf10 = deflateRect(rectf9, mInnerShadowWidth);
        RectF rectf11 = deflateRect(rectf10, mOuterShadowWidth);
        RectF rectf12 = deflateRect(rectf3, mStrokeWidth);
        RectF rectf13 = inflateRect(rectf12, mStrokeWidth);
        RectF rectf14 = inflateRect(rectf13, mInnerShadowWidth);
        RectF rectf15 = inflateRect(rectf14, mOuterShadowWidth);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(false);
        bmDrawRing(bitmap1, canvas, rectf4, rectf11, mShadowOuterPaint);
        bmDrawRing(bitmap1, canvas, rectf5, rectf10, mShadowInnerPaint);
        bmDrawRing(bitmap1, canvas, rectf6, rectf9, mBlueStrokePaint);
        int i = saveCanvasLayer(canvas, rectf);
        bmFillBkGradient(bitmap1, mBlueGradientStartColor, mBluetGradientEndColor);
        canvas.drawBitmap(bitmap1, 0.0F, 0.0F, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        bmDrawOval(bitmap1, rectf7, -1);
        canvas.drawBitmap(bitmap1, 0.0F, 0.0F, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_OUT));
        bmDrawOval(bitmap1, rectf8, -1);
        canvas.drawBitmap(bitmap1, 0.0F, 0.0F, paint);
        canvas.restoreToCount(i);
        paint.setXfermode(null);
        canvas.drawOval(rectf15, mShadowOuterPaint);
        canvas.drawOval(rectf14, mShadowInnerPaint);
        canvas.drawOval(rectf13, mBlueStrokePaint);
        int j = saveCanvasLayer(canvas, rectf);
        bmFillBkGradient(bitmap1, mBlueGradientStartColor, mBluetGradientEndColor);
        canvas.drawBitmap(bitmap1, 0.0F, 0.0F, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        bmDrawOval(bitmap1, rectf12, -1);
        canvas.drawBitmap(bitmap1, 0.0F, 0.0F, paint);
        canvas.restoreToCount(j);
        paint.setXfermode(null);
        bitmap1.recycle();
    }

    public int getMax()
    {
        return mMax;
    }

    public int getProgress()
    {
        return mProgress;
    }

    public int getProgressExpireMark()
    {
        return mProgressExpireMark;
    }

    protected void onDraw(Canvas canvas)
    {
        createLogoBitmap();
        RectF rectf = calculateCircle(1);
        float f = (360F / (float)mMax) * (float)mProgress;
        Paint paint;
        if (mProgressColorMode == ProgressColorsMode.ColorOnTransparent)
        {
            canvas.drawArc(rectf, -90F, f, true, mProgressPaint);
        } else
        {
            canvas.drawArc(rectf, f - 90F, 1.0F + (360F - f), true, mProgressPaint);
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(false);
        canvas.drawBitmap(mLogoBitmap, 0.0F, 0.0F, paint);
        super.onDraw(canvas);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setPadding((int)(0.225F * (float)k), (int)(0.225F * (float)k), (int)(0.225F * (float)k), (int)(0.225F * (float)k));
        super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(k, 0x40000000));
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        createLogoBitmap();
    }

    public void setMax(int i)
    {
        mMax = i;
        calculateProgressColor();
        invalidate();
    }

    public void setProgress(int i)
    {
        mProgress = i;
        calculateProgressColor();
        invalidate();
    }

    public void setProgressColor(int i)
    {
        mProgressColor = i;
        calculateProgressColor();
    }

    public void setProgressColorsMode(ProgressColorsMode progresscolorsmode)
    {
        mProgressColorMode = progresscolorsmode;
        calculateProgressColor();
        invalidate();
    }

    public void setProgressExpireMark(int i)
    {
        mProgressExpireMark = i;
        calculateProgressColor();
        invalidate();
    }

    public void setProgressExpiredColor(int i)
    {
        mProgressExpiredColor = i;
        calculateProgressColor();
    }

	public enum ProgressColorsMode {
		ColorOnTransparent, TransparentOnColor
	}
}
