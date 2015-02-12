// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;

import android.content.Context;
import android.content.res.Resources;

public class BuildMode
{

    public static final String BUILD_MODE_RELEASE = "release";
    public static final String BUILD_MODE_RES_NAME = "build_mode";
    public static final String BUILD_MODE_RES_TYPE = "string";

    private BuildMode()
    {
    }

    public static boolean debug(Context context)
    {
        return !release(context);
    }

    public static boolean release(Context context)
    {
        int i = context.getResources().getIdentifier("build_mode", "string", context.getPackageName());
        return i > 0 && context.getString(i).equals("release");
    }
}
