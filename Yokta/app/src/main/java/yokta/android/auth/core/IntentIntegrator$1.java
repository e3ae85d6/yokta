// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.List;

// Referenced classes of package yokta.android.auth.core:
//            IntentIntegrator

class this._cls0
    implements android.content.lickListener
{

    final IntentIntegrator this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        String s = (String)IntentIntegrator.access$000(IntentIntegrator.this).get(0);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("market://details?id=").append(s).toString()));
        try
        {
            IntentIntegrator.access$100(IntentIntegrator.this).startActivity(intent);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            Log.w(IntentIntegrator.access$200(), (new StringBuilder()).append("Google Play is not installed; cannot install ").append(s).toString());
        }
    }

    r()
    {
        this$0 = IntentIntegrator.this;
        super();
    }
}
