// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package yokta.android.auth.activity:
//            SecureActivity, CodeActivity, InformationActivity, ManageAccountActivity

public class ToolbarActivity extends SecureActivity
    implements android.view.View.OnClickListener
{

    private ViewGroup mChildContainer;
    private boolean mContentViewReady;
    private ImageButton mHomeButton;
    private ImageView mIconView;
    private ImageButton mInfoButton;
    private boolean mResumed;
    private View mRootView;
    private ImageButton mSettingsButton;
    private TextView mTitleText;

    public ToolbarActivity()
    {
        mContentViewReady = false;
        mResumed = false;
    }

    private void checkContentView()
    {
        if (!mContentViewReady)
        {
            throw new IllegalStateException("Content view is not set. Call super.onCreate() first!");
        } else
        {
            return;
        }
    }

    protected ViewGroup getChildContainerView()
    {
        return mChildContainer;
    }

    protected View getRootView()
    {
        return mRootView;
    }

    protected ImageButton getToolbarButton(ButtonType buttontype)
    {
        switch (buttontype)
        {

        case ButtonType.HOME:
            return mHomeButton;

        case ButtonType.SETTINGS:
            return mSettingsButton;

        case ButtonType.INFO:
            return mInfoButton;

		default:
				throw new IllegalArgumentException((new StringBuilder()).append("Button type ").append(buttontype.name()).append(" is not supprted.").toString());
        }

    }

    protected ImageView getToolbarIconView()
    {
        return mIconView;
    }

    protected TextView getToolbarTitleView()
    {
        return mTitleText;
    }

    protected void hideToolbarButtons()
    {
        ButtonType abuttontype[] = ButtonType.values();
        int i = abuttontype.length;
        for (int j = 0; j < i; j++)
        {
            getToolbarButton(abuttontype[j]).setVisibility(8);
        }

    }

    protected void hideToolbarButtons(ButtonType abuttontype[])
    {
        int i = abuttontype.length;
        for (int j = 0; j < i; j++)
        {
            getToolbarButton(abuttontype[j]).setVisibility(8);
        }

    }

    public boolean isActivityResumed()
    {
        return mResumed;
    }

    public void onClick(View view)
    {
        if (view == mHomeButton)
        {
            onToolbarButtonClicked(ButtonType.HOME);
        } else
        {
            if (view == mSettingsButton)
            {
                onToolbarButtonClicked(ButtonType.SETTINGS);
                return;
            }
            if (view == mInfoButton)
            {
                onToolbarButtonClicked(ButtonType.INFO);
                return;
            }
        }
    }

    protected void onCreate(Bundle bundle)
    {
        getWindow().setFormat(1);
        getWindow().addFlags(4096);
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        super.setContentView(0x7f030008);
        mContentViewReady = true;
        mRootView = findViewById(0x7f080023);
        mChildContainer = (ViewGroup)findViewById(0x7f080029);
        mIconView = (ImageView)findViewById(0x7f080024);
        mTitleText = (TextView)findViewById(0x7f080025);
        mHomeButton = (ImageButton)findViewById(0x7f080026);
        mSettingsButton = (ImageButton)findViewById(0x7f080027);
        mInfoButton = (ImageButton)findViewById(0x7f080028);
        mHomeButton.setOnClickListener(this);
        mSettingsButton.setOnClickListener(this);
        mInfoButton.setOnClickListener(this);
        mIconView.setVisibility(8);
        mTitleText.setText("");
        mHomeButton.setVisibility(8);
        mSettingsButton.setVisibility(8);
        mInfoButton.setVisibility(8);
    }

    protected void onHomeClicked()
    {
        CodeActivity.openAsHome(this);
    }

    protected void onInfoClicked()
    {
        startActivity(new Intent(this, InformationActivity.class));
    }

    protected void onPause()
    {
        super.onPause();
        mResumed = false;
    }

    protected void onResume()
    {
        super.onResume();
        mResumed = true;
    }

    protected void onSettingsClicked()
    {
        startActivity(new Intent(this, ManageAccountActivity.class));
    }

    protected void onToolbarButtonClicked(ButtonType buttontype)
    {
        switch (buttontype)
        {
        case ButtonType.HOME:
            onHomeClicked();
            return;

        case ButtonType.SETTINGS:
            onSettingsClicked();
            return;

        case ButtonType.INFO:
            onInfoClicked();
            break;
        }
    }

    protected void setChildBackgroundColor(int i)
    {
        mRootView.setBackgroundColor(i);
    }

    protected void setChildBackgroundDrawable(Drawable drawable)
    {
        mRootView.setBackgroundDrawable(drawable);
    }

    protected void setChildBackgroundResource(int i)
    {
        mRootView.setBackgroundResource(i);
    }

    public void setContentView(int i)
    {
        View view = LayoutInflater.from(this).inflate(i, mChildContainer, false);
        mChildContainer.removeAllViews();
        mChildContainer.addView(view);
    }

    public void setContentView(View view)
    {
        mChildContainer.removeAllViews();
        mChildContainer.addView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        mChildContainer.removeAllViews();
        mChildContainer.addView(view, layoutparams);
    }

    protected void setToolbarIcon(int i)
    {
        showToolbarIcon();
        mIconView.setImageResource(i);
    }

    protected void setToolbarTitle(int i)
    {
        setToolbarTitle(getResources().getString(i));
    }

    protected void setToolbarTitle(String s)
    {
        checkContentView();
        mTitleText.setText(s);
    }

    protected void showToolbarButtons()
    {
        ButtonType abuttontype[] = ButtonType.values();
        int i = abuttontype.length;
        for (int j = 0; j < i; j++)
        {
            getToolbarButton(abuttontype[j]).setVisibility(0);
        }

    }

    protected void showToolbarButtons(ButtonType abuttontype[])
    {
        int i = abuttontype.length;
        for (int j = 0; j < i; j++)
        {
            getToolbarButton(abuttontype[j]).setVisibility(0);
        }

    }

    protected void showToolbarIcon()
    {
        mIconView.setVisibility(0);
    }

    enum ButtonType
    {

        HOME, SETTINGS, INFO
    }

}
