// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import yokta.android.auth.core.IntentIntegrator;
import yokta.android.auth.core.IntentResult;

// Referenced classes of package yokta.android.auth.activity:
//            ToolbarActivity, EnterAccountActivity

public class ManageAccountActivity extends ToolbarActivity
{

    private static final int ACTIVITY_ENTER_ACCOUNT_REQUEST = 2;
    static final String EXTRA_ZXING_SAVE_HISTORY = "SAVE_HISTORY";
    static final String EXTRA_ZXING_SCAN_MODE = "SCAN_MODE";
    static final String ZXING_QR_CODE_SCAN_MODE = "QR_CODE_MODE";
    private IntentIntegrator integrator;
    private AlertDialog integratorDialog;
    private TextView mDescrTextView;

    public ManageAccountActivity()
    {
    }

    private void scanBarcode()
    {
        integrator = new IntentIntegrator(this);
        integrator.addExtra("SCAN_MODE", "QR_CODE_MODE");
        integrator.addExtra("SAVE_HISTORY", Boolean.valueOf(false));
        integrator.setTitleByID(0x7f070029);
        integrator.setMessageByID(0x7f070028);
        integrator.setButtonYesByID(0x7f070026);
        integrator.setButtonNoByID(0x7f070027);
        integratorDialog = integrator.initiateScan();
    }

    protected void onAccountUpdated(boolean flag)
    {
        if (flag)
        {
            finish();
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        IntentResult intentresult = IntentIntegrator.parseActivityResult(i, j, intent);
        if (intentresult != null)
        {
            String s = intentresult.getContents();
            if (s != null)
            {
                Intent intent1 = new Intent(this, EnterAccountActivity.class);
                intent1.setAction("parseUri");
                intent1.setData(Uri.parse(s));
                startActivityForResult(intent1, 2);
            }
        } else
        if (i == 2)
        {
            boolean flag;
            if (j == -1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            onAccountUpdated(flag);
            return;
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030007);
        setToolbarTitle(0x7f07002f);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[2];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        abuttontype[1] = ToolbarActivity.ButtonType.INFO;
        showToolbarButtons(abuttontype);
        mDescrTextView = (TextView)findViewById(0x7f080020);
    }

    public void onEnterManuallyClicked(View view)
    {
        startActivityForResult(new Intent(this, EnterAccountActivity.class), 2);
    }

    protected void onPause()
    {
        super.onPause();
        if (integratorDialog != null)
        {
            integratorDialog.dismiss();
        }
    }

    public void onScanBarcodeClicked(View view)
    {
        scanBarcode();
    }

    protected void setDescriptionText(int i)
    {
        mDescrTextView.setText(i);
    }

    protected void setDescriptionText(String s)
    {
        mDescrTextView.setText(s);
    }
}
