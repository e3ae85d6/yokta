// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// Referenced classes of package yokta.android.auth.activity:
//            InformationSectionActivity

public class DiagnosticsAccountUrisActivity extends InformationSectionActivity
{

    private static final Map sUris;

    public DiagnosticsAccountUrisActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030002);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[1];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        showToolbarButtons(abuttontype);
        setToolbarTitle(0x7f07001c);
        LinearLayout linearlayout = (LinearLayout)findViewById(0x7f08000a);
        TextView textview;
        for (Iterator iterator = sUris.entrySet().iterator(); iterator.hasNext(); linearlayout.addView(textview, -1, -2))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            String s1 = (String)entry.getValue();
            textview = new TextView(this);
            textview.setGravity(17);
            textview.setPadding(10, 10, 10, 10);
            textview.setText(Html.fromHtml((new StringBuilder()).append("<a href=\"").append(s1).append("\">").append(s).append("</a>").toString()));
            textview.setMovementMethod(LinkMovementMethod.getInstance());
        }

    }

    static 
    {
        TreeMap treemap = new TreeMap();
        treemap.put("otpauth-ok", "otpauth://totp/test@example.com?secret=longtestsecretlongtestsecret");
        treemap.put("otpauth-short-secret", "otpauth://totp/test@example.com?secret=short");
        treemap.put("otpauth-empty-secret", "otpauth://totp/test@example.com?secret=");
        treemap.put("otpauth-no-secret", "otpauth://totp/test@example.com");
        treemap.put("otpauth-no-user", "otpauth://totp/?secret=longtestsecretlongtestsecret");
        treemap.put("totp-ok", "totp://test@testAuthority.com#longtestsecretlongtestsecret");
        treemap.put("totp-short-secret", "totp://test@testAuthority.com#short");
        treemap.put("totp-empty-secret", "totp://test@testAuthority.com#");
        treemap.put("totp-no-secret", "totp://test@testAuthority.com");
        treemap.put("totp-no-user", "totp://#longtestsecretlongtestsecret");
        sUris = Collections.unmodifiableMap(treemap);
    }
}
