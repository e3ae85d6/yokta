// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.os.Bundle;
import yokta.android.auth.data.OktaAccount;

// Referenced classes of package yokta.android.auth.activity:
//            ManageAccountActivity

public class InitialSetupActivity extends ManageAccountActivity
{

    private OktaAccount mAccount;

    public InitialSetupActivity()
    {
    }

    protected void onAccountUpdated(boolean flag)
    {
        if (flag)
        {
            setResult(-1);
            finish();
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mAccount = new OktaAccount(this);
        showToolbarIcon();
        hideToolbarButtons();
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[1];
        abuttontype[0] = ToolbarActivity.ButtonType.INFO;
        showToolbarButtons(abuttontype);
        setToolbarTitle("");
        setDescriptionText(0x7f070025);
    }

    protected void onResume()
    {
        super.onResume();
        if (mAccount.exists())
        {
            setResult(-1);
            finish();
        }
    }
}
