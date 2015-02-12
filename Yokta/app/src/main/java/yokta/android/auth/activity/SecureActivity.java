// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SecureActivity extends Activity
{

    public SecureActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (android.os.Build.VERSION.SDK_INT >= 11 || android.os.Build.VERSION.SDK_INT <= 8)
        {
            getWindow().setFlags(8192, 8192);
        }
    }
}
