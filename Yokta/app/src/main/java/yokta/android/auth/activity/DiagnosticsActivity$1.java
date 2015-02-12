// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.os.Handler;

// Referenced classes of package yokta.android.auth.activity:
//            DiagnosticsActivity

class this._cls0
    implements Runnable
{

    final DiagnosticsActivity this$0;

    public void run()
    {
        update();
        DiagnosticsActivity.access$000(DiagnosticsActivity.this).postDelayed(this, 500L);
    }

    ()
    {
        this$0 = DiagnosticsActivity.this;
        super();
    }
}
