// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.ClipboardManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import yokta.android.auth.core.OktaPasscodeGenerator;
import yokta.android.auth.core.PasscodeClock;
import yokta.android.auth.data.OktaAccount;
import yokta.android.auth.view.OktaLogoProgressBar;

// Referenced classes of package yokta.android.auth.activity:
//            ToolbarActivity, InitialSetupActivity

public class CodeActivity extends ToolbarActivity
{

    private static final int CODE_VALID_TIME_SECONDS = 30;
    private static final long DEFAULT_UPDATE_DELAY_MS = 50L;
    static long MIN_UPDATE_DELAY_MS = 0L;
    private static final int REQUEST_INITIAL_SETUP = 1;
    private static final int START_EXPIRING_AFTER_SECONDS = 22;
    private OktaAccount mAccount;
    private ClipboardManager mClipboardManager;
    private PasscodeClock mClock;
    private String mCurCode;
    private long mCurInterval;
    private int mCurProgress;
    private Handler mHandler;
    private int mMaxProgress;
    private TextView mNameTextView;
    private OktaLogoProgressBar mProgressBar;
    private long mUpdateDelay;
    private Runnable mUpdater;

    public CodeActivity()
    {
        mCurInterval = 0L;
        mCurCode = "";
        mUpdateDelay = DEFAULT_UPDATE_DELAY_MS;
        mUpdater = new Runnable() {
			@Override
			public void run() {
				onUpdate();
				mHandler.postDelayed(this, mUpdateDelay);
			}
		};
    }

    public static void openAsHome(Context context)
    {
        Intent intent = new Intent(context, CodeActivity.class);
        intent.addFlags(0x4000000);
        context.startActivity(intent);
    }

    protected void calculateCode()
    {
        mCurCode = OktaPasscodeGenerator.generate(mAccount.getSecret());
    }

    protected void calculateMaxProgress()
    {
        mMaxProgress = 30000;
    }

    protected void calculateProgress()
    {
        mCurProgress = (int)(System.currentTimeMillis() - mClock.getTime(mCurInterval));
    }

    protected void calculateUpdateDelay()
    {
        calculateMaxProgress();
        mUpdateDelay = 50L;
        mUpdateDelay = 30000 / (4 * Math.min(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight()));
        mUpdateDelay = Math.max(MIN_UPDATE_DELAY_MS, mUpdateDelay);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if (i == 1)
        {
            if (j == 0)
            {
                finish();
            }
            return;
        } else
        {
            super.onActivityResult(i, j, intent);
            return;
        }
    }

    public void onCopyCode()
    {
        String s = mProgressBar.getText().toString();
        mClipboardManager.setText(s);
        Toast toast = Toast.makeText(this, getString(0x7f070002, new Object[] {
            s
        }), 0);
        toast.setGravity(48, 0, getResources().getInteger(0x7f060000));
        toast.show();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        mClipboardManager = (ClipboardManager)getSystemService("clipboard");
        mHandler = new Handler();
        mClock = new PasscodeClock(30);
        mAccount = new OktaAccount(this);
        showToolbarIcon();
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[2];
        abuttontype[0] = ToolbarActivity.ButtonType.SETTINGS;
        abuttontype[1] = ToolbarActivity.ButtonType.INFO;
        showToolbarButtons(abuttontype);
        calculateMaxProgress();
        calculateUpdateDelay();
        mNameTextView = (TextView)findViewById(0x7f080000);
        mProgressBar = (OktaLogoProgressBar)findViewById(0x7f080001);
        onSetupProgressBar();
        mProgressBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onCopyCode();
			}
		});
    }

    protected void onPause()
    {
        super.onPause();
        mHandler.removeCallbacks(mUpdater);
    }

    protected void onResume()
    {
        super.onResume();
        if (!mAccount.exists())
        {
            startActivityForResult(new Intent(this, InitialSetupActivity.class), 1);
            return;
        } else
        {
            mNameTextView.setText(mAccount.getName());
            updateCode();
            mUpdater.run();
            return;
        }
    }

    public void onSetupProgressBar()
    {
        int i = getResources().getColor(0x7f040007);
        int j = getResources().getColor(0x7f040008);
        int k = (22 * mMaxProgress) / 30;
        mProgressBar.setProgressColor(i);
        mProgressBar.setProgressExpiredColor(j);
        mProgressBar.setMax(mMaxProgress);
        mProgressBar.setProgress(mCurProgress);
        mProgressBar.setProgressExpireMark(k);
        mProgressBar.setText(mCurCode);
    }

    protected void onUpdate()
    {
        long l = mClock.getCurrentInterval();
        if (l != mCurInterval)
        {
            mCurInterval = l;
            updateCode();
            if (mCurInterval % 2L == 0L)
            {
                mProgressBar.setProgressColorsMode(yokta.android.auth.view.OktaLogoProgressBar.ProgressColorsMode.ColorOnTransparent);
            } else
            {
                mProgressBar.setProgressColorsMode(yokta.android.auth.view.OktaLogoProgressBar.ProgressColorsMode.TransparentOnColor);
            }
        }
        calculateProgress();
        mProgressBar.setProgress(mCurProgress);
    }

    protected void updateCode()
    {
        calculateCode();
        mProgressBar.setText(mCurCode);
    }

    static 
    {
        MIN_UPDATE_DELAY_MS = 33L;
    }
}
