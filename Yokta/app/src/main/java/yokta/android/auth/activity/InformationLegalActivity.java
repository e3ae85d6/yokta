// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package yokta.android.auth.activity:
//            InformationSectionActivity

public class InformationLegalActivity extends InformationSectionActivity
{

    public InformationLegalActivity()
    {
    }

    private String getHTMLAsset()
    {
        String s;
        try
        {
            InputStream inputstream = getAssets().open(getString(0x7f070021));
            byte abyte0[] = new byte[inputstream.available()];
            inputstream.read(abyte0);
            inputstream.close();
            s = new String(abyte0);
        }
        catch (IOException ioexception)
        {
            Log.e("Failed to open HTML asset", ioexception.toString());
            return "";
        }
        return s;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setToolbarTitle(0x7f070022);
        setHtml(getHTMLAsset());
    }
}
