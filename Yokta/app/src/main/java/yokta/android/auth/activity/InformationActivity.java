// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package yokta.android.auth.activity:
//            ToolbarActivity, DiagnosticsActivity, InformationHelpActivity, InformationLegalActivity

public class InformationActivity extends ToolbarActivity
{

    private static final String TAG = InformationActivity.class.getSimpleName();
    private TextView mVersionTextView;

    public InformationActivity()
    {
    }

    private void setVersion()
    {
        try
        {
            PackageInfo packageinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            TextView textview = mVersionTextView;
            Object aobj[] = new Object[1];
            aobj[0] = packageinfo.versionName;
            textview.setText(getString(0x7f070024, aobj));
            return;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            Log.e(TAG, "Failed to get application version.");
        }
        mVersionTextView.setVisibility(8);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030005);
        setToolbarTitle(0x7f070023);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[1];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        showToolbarButtons(abuttontype);
        mVersionTextView = (TextView)findViewById(0x7f08001e);
        setVersion();
        findViewById(0x7f08001c).setVisibility(8);
    }

    public void onDiagnosticsClicked(View view)
    {
        startActivity(new Intent(this, DiagnosticsActivity.class));
    }

    public void onHelpClicked(View view)
    {
        startActivity(new Intent(this, InformationHelpActivity.class));
    }

    public void onLegalClicked(View view)
    {
        startActivity(new Intent(this, InformationLegalActivity.class));
    }

}
