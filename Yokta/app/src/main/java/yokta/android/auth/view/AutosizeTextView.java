// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class AutosizeTextView extends TextView
{

    private static final float DEFAULT_MAX_TEXT_SIZE = 100F;
    private static final float DEFAULT_MIN_TEXT_SIZE = 1F;
    private static final float DEFAULT_STEP_TEXT_SIZE = 1F;
    WindowManager mWindowManager;

    public AutosizeTextView(Context context)
    {
        super(context);
        init();
    }

    public AutosizeTextView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    public AutosizeTextView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init();
    }

    protected void calculateTextSize(int i, int j)
    {
        int k;
        int l;
        float f;
        k = getMeasuredWidth();
        l = getMeasuredHeight();
        f = getTextSize();
        if (f < DEFAULT_MIN_TEXT_SIZE)
        {
            f = DEFAULT_MIN_TEXT_SIZE;
        }
        if (f > DEFAULT_MAX_TEXT_SIZE)
        {
            f = DEFAULT_MAX_TEXT_SIZE;
        }
        if (!checkTextSize(f, i, j))
        {
            break MISSING_BLOCK_LABEL_95;
        }
_L3:
        if (f <= DEFAULT_MAX_TEXT_SIZE && checkTextSize(f + 1.0F, i, j)) goto _L2; else goto _L1
_L1:
        setMeasuredDimension(k, l);
        setTextSize(0, f);
        return;
_L2:
        f++;
          goto _L3
        for (; f >= 1.0F && !checkTextSize(f, i, j); f--) { }
          goto _L1
    }

    protected boolean checkTextSize(float f, int i, int j)
    {
        setTextSize(0, f);
        super.onMeasure(0, 0);
        int k = getMeasuredWidth();
        int l = getMeasuredHeight();
        boolean flag = false;
        if (k <= i)
        {
            flag = false;
            if (l <= j)
            {
                flag = true;
            }
        }
        return flag;
    }

    void init()
    {
        mWindowManager = (WindowManager)getContext().getSystemService("window");
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        int k = android.view.View.MeasureSpec.getSize(i);
        int l = android.view.View.MeasureSpec.getSize(j);
        if (android.view.View.MeasureSpec.getMode(i) == 0)
        {
            k = mWindowManager.getDefaultDisplay().getWidth();
        }
        if (android.view.View.MeasureSpec.getMode(j) == 0)
        {
            l = mWindowManager.getDefaultDisplay().getHeight();
        }
        calculateTextSize(k, l);
        super.onMeasure(i, j);
    }
}
