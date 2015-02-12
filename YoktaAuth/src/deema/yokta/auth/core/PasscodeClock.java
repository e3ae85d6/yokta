// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package deema.yokta.auth.core;

//import a.a.a.a;

public class PasscodeClock
{

    public static final int DEFAULT_STEP = 30;
    private final int mStep;

    public PasscodeClock()
    {
        mStep = DEFAULT_STEP;
    }

    public PasscodeClock(int step)
    {
        //boolean flag = step > 0;
        //a.a(flag, "Invalid passcode clock step: %d.", Integer.valueOf(step));
        mStep = step;
    }

    public long getCurrentInterval()
    {
        return getInterval(System.currentTimeMillis());
    }

    public long getInterval(long l)
    {
        return l / 1000L / (long)mStep;
    }

    public int getStep()
    {
        return mStep;
    }

    public long getTime(long l)
    {
        return 1000L * (l * (long)mStep);
    }
}
