// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.text.method.NumberKeyListener;
import yokta.android.auth.core.Base32String;

class  extends NumberKeyListener
{

    protected char[] getAcceptedChars()
    {
        return Base32String.sInputAlphabet.toCharArray();
    }

    public int getInputType()
    {
        return 0x80001;
    }

    ()
    {
    }
}
