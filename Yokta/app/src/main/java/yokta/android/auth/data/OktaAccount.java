// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import a.a.a.a;

public class OktaAccount
{

    private static final String ACCOUNT_NAME = "accountName";
    private static final String ACCOUNT_SECRET = "accountSecret";
    private final Context mContext;
    private final SharedPreferences mSharedPrefs;

    public OktaAccount(Context context)
    {
        a.a(context);
        mContext = context.getApplicationContext();
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static boolean isSecretValid(String s)
    {
        return s != null && s.length() != 0;
    }

    public boolean exists()
    {
        return getSecret() != null && getName() != null;
    }

    public String getName()
    {
        return mSharedPrefs.getString(ACCOUNT_NAME, null);
    }

    public String getSecret()
    {
        return mSharedPrefs.getString(ACCOUNT_SECRET, null);
    }

    public void reset()
    {
        mSharedPrefs.edit().remove(ACCOUNT_NAME).remove(ACCOUNT_SECRET).commit();
    }

    public void set(String s, String s1)
    {
        setSecret(s1);
        setName(s);
    }

    public void setName(String s)
    {
        a.a(s, "Account name can not be null.");
        mSharedPrefs.edit().putString(ACCOUNT_NAME, s).commit();
    }

    public void setSecret(String s)
    {
        if (!isSecretValid(s))
        {
            throw new IllegalArgumentException("Invalid secret.");
        } else
        {
            mSharedPrefs.edit().putString(ACCOUNT_SECRET, s).commit();
            return;
        }
    }
}
