// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import yokta.android.auth.core.OktaPasscodeGenerator;
import yokta.android.auth.core.PasscodeClock;
import yokta.android.auth.data.OktaAccount;

// Referenced classes of package yokta.android.auth.activity:
//            InformationSectionActivity, DiagnosticsAccountUrisActivity

public class DiagnosticsActivity extends InformationSectionActivity
{

    private static final long UPDATE_INTERVAL = 500L;
    private OktaAccount mAccount;
    private TextView mAccountName;
    private PasscodeClock mClock;
    private TextView mCountdownTime;
    private TextView mCurrentInterval;
    private Handler mHandler;
    private TextView mIntervalTime;
    private TextView mPin;
    private Runnable mUpdater;

    public DiagnosticsActivity()
    {
        mClock = new PasscodeClock(30);
        mUpdater = new Runnable() {
			@Override
			public void run() {
				update();
				mHandler.postDelayed(this, 500L);
			}
		};
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        getWindow().setFlags(8192, 8192);
        setContentView(0x7f030001);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[1];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        showToolbarButtons(abuttontype);
        setToolbarTitle(0x7f07001b);
        mAccountName = (TextView)findViewById(0x7f080003);
        mPin = (TextView)findViewById(0x7f080004);
        mCurrentInterval = (TextView)findViewById(0x7f080005);
        mIntervalTime = (TextView)findViewById(0x7f080006);
        mCountdownTime = (TextView)findViewById(0x7f080007);
        mAccount = new OktaAccount(this);
        mHandler = new Handler();
    }

    protected void onPause()
    {
        super.onPause();
        mHandler.removeCallbacks(mUpdater);
    }

    public void onResetAccount(View view)
    {
        mAccount.reset();
        update();
    }

    protected void onResume()
    {
        super.onResume();
        mUpdater.run();
    }

    public void onShowAccountUris(View view)
    {
        startActivity(new Intent(this, DiagnosticsAccountUrisActivity.class));
    }

    protected void update()
    {
        if (TextUtils.isEmpty(mAccount.getName()))
        {
            mAccountName.setText(0x7f070018);
        } else
        {
            mAccountName.setText(mAccount.getName());
        }
        mIntervalTime.setText(Integer.toString(30));
        updateCurrentInterval();
        updateCountdownTime();
        updatePin();
    }

    protected void updateCountdownTime()
    {
        long l = 30L - (System.currentTimeMillis() / 1000L) % 30L;
        mCountdownTime.setText(Long.toString(l));
    }

    protected void updateCurrentInterval()
    {
        long l = mClock.getCurrentInterval();
        mCurrentInterval.setText(Long.toString(l));
    }

    protected void updatePin()
    {
        String s = OktaPasscodeGenerator.generate(mAccount.getSecret());
        mPin.setText(s);
    }
}
