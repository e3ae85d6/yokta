// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.os.Handler;

// Referenced classes of package yokta.android.auth.activity:
//            CodeActivity

class this._cls0
    implements Runnable
{

    final CodeActivity this$0;

    public void run()
    {
        onUpdate();
        CodeActivity.access$100(CodeActivity.this).postDelayed(this, CodeActivity.access$000(CodeActivity.this));
    }

    ()
    {
        this$0 = CodeActivity.this;
        super();
    }
}
