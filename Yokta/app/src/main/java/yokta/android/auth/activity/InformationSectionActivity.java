// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

// Referenced classes of package yokta.android.auth.activity:
//            ToolbarActivity

public class InformationSectionActivity extends ToolbarActivity
{

    private TextView mTextView;

    public InformationSectionActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030006);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[1];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        showToolbarButtons(abuttontype);
        getRootView().setBackgroundResource(0x7f020008);
        mTextView = (TextView)findViewById(0x7f08001f);
    }

    protected void setHtml(int i)
    {
        setHtml(getString(i));
    }

    protected void setHtml(String s)
    {
        setText(Html.fromHtml(s));
    }

    protected void setText(int i)
    {
        mTextView.setText(i);
    }

    protected void setText(CharSequence charsequence)
    {
        mTextView.setText(charsequence);
    }
}
