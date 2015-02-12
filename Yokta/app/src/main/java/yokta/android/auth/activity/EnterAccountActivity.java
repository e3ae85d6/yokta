// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import yokta.android.auth.core.Base32String;
import yokta.android.auth.core.UriParser;
import yokta.android.auth.data.OktaAccount;
import java.text.ParseException;

// Referenced classes of package yokta.android.auth.activity:
//            ToolbarActivity

public class EnterAccountActivity extends ToolbarActivity
    implements TextWatcher, android.widget.TextView.OnEditorActionListener
{

    public static final String ACTION_PARSE_URI = "parseUri";
    private static final int MIN_SECRET_BYTE_LEN = 10;
    public static final String STATE_VALIDATED = "validated";
    private OktaAccount mAccount;
    private EditText mSecretEditText;
    private TextView mSecretStatusTextView;
    private View mSecretValidationBackground;
    private EditText mUsernameEditText;
    private TextView mUsernameStatusTextView;
    private View mUsernameValidationBackground;
    private boolean mValidState;
    private boolean mValidated;

    public EnterAccountActivity()
    {
        mValidState = false;
        mValidated = false;
    }

    private void cancel()
    {
        setResult(0);
        finish();
    }

    private void handleParseIntent(Intent intent)
    {
label0:
        {
            boolean flag = parseUri(intent.getData());
            if (!flag)
            {
                Toast.makeText(this, 0x7f070006, 1).show();
                finish();
            }
            if ("parseUri".equals(intent.getAction()))
            {
                if (flag)
                {
                    break label0;
                }
                cancel();
            }
            return;
        }
        save();
    }

    private boolean parseUri(Uri uri)
    {
        try
        {
            yokta.android.auth.core.UriParser.Result result = UriParser.parse(uri);
            if (!TextUtils.isEmpty(result.getUser()))
            {
                mUsernameEditText.setText(result.getUser());
            }
            mSecretEditText.setText(result.getSecret());
        }
        catch (ParseException parseexception)
        {
            return false;
        }
        return true;
    }

    private void save()
    {
        String s = mUsernameEditText.getText().toString();
        String s1 = mSecretEditText.getText().toString();
        if (validate())
        {
            mAccount.setName(s);
            if (s1.length() > 0)
            {
                mAccount.setSecret(s1);
            }
            setResult(-1);
            finish();
        }
    }

    private void showError(boolean flag, String s, TextView textview, View view)
    {
        textview.setText(s);
        byte byte0 = 0;
        if (!flag)
        {
            byte0 = 8;
        }
        textview.setVisibility(byte0);
        if (flag)
        {
            if (view != null)
            {
                view.setBackgroundResource(0x7f020013);
            }
        } else
        {
            int i = getResources().getColor(0x7f04001b);
            if (view != null)
            {
                view.setBackgroundColor(i);
                return;
            }
        }
    }

    private void updateSecretHint()
    {
        if (TextUtils.isEmpty(mAccount.getSecret()))
        {
            mSecretEditText.setHint(0x7f07000f);
            return;
        } else
        {
            mSecretEditText.setHint(0x7f070010);
            return;
        }
    }

    private boolean validateSecret(String s)
    {
label0:
        {
            hideSecretError();
            if (TextUtils.isEmpty(s))
            {
                if (TextUtils.isEmpty(mAccount.getSecret()))
                {
                    showSecretError(0x7f070008);
                    return false;
                } else
                {
                    return true;
                }
            }
            try
            {
                if (Base32String.decode(s).length >= 10)
                {
                    break label0;
                }
                showSecretError(0x7f07000a);
            }
            catch (yokta.android.auth.core.Base32String.DecodingException decodingexception)
            {
                Object aobj[] = new Object[1];
                aobj[0] = decodingexception.getMessage();
                showSecretError(getString(0x7f070007, aobj));
                return false;
            }
            return false;
        }
        return true;
    }

    public void afterTextChanged(Editable editable)
    {
        if (mValidated)
        {
            validate();
        }
    }

    public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    protected void hideSecretError()
    {
        showSecretError(false, "");
    }

    protected void hideUsernameError()
    {
        showUsernameError(false, "");
    }

    public void onCancel(View view)
    {
        cancel();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030003);
        ToolbarActivity.ButtonType abuttontype[] = new ToolbarActivity.ButtonType[2];
        abuttontype[0] = ToolbarActivity.ButtonType.HOME;
        abuttontype[1] = ToolbarActivity.ButtonType.INFO;
        showToolbarButtons(abuttontype);
        setToolbarTitle(0x7f070004);
        mAccount = new OktaAccount(this);
        mUsernameEditText = (EditText)findViewById(0x7f08000d);
        mSecretEditText = (EditText)findViewById(0x7f080011);
        mUsernameStatusTextView = (TextView)findViewById(0x7f08000e);
        mSecretStatusTextView = (TextView)findViewById(0x7f080012);
        mUsernameValidationBackground = findViewById(0x7f08000c);
        mSecretValidationBackground = findViewById(0x7f080010);
        mUsernameEditText.addTextChangedListener(this);
        mSecretEditText.addTextChangedListener(this);
        mUsernameEditText.setOnEditorActionListener(this);
        mSecretEditText.setOnEditorActionListener(this);
        mSecretEditText.setKeyListener(new SecretKeyListener());
        if (!TextUtils.isEmpty(mAccount.getName()))
        {
            mUsernameEditText.setText(mAccount.getName());
        }
        hideUsernameError();
        hideSecretError();
        updateSecretHint();
        if (bundle == null)
        {
            Intent intent = getIntent();
            if (intent.getData() != null)
            {
                handleParseIntent(intent);
            }
        }
    }

    public boolean onEditorAction(TextView textview, int i, KeyEvent keyevent)
    {
        if (i == 6)
        {
            validate();
        }
        return false;
    }

    protected void onRestoreInstanceState(Bundle bundle)
    {
        super.onRestoreInstanceState(bundle);
        if (!mValidated && bundle.getBoolean("validated", false))
        {
            validate();
        }
    }

    public void onSave(View view)
    {
        save();
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("validated", mValidated);
    }

    public void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    protected void showSecretError(int i)
    {
        showSecretError(getString(i));
    }

    protected void showSecretError(String s)
    {
        showSecretError(true, s);
    }

    protected void showSecretError(boolean flag, String s)
    {
        showError(flag, s, mSecretStatusTextView, mSecretValidationBackground);
    }

    protected void showUsernameError(int i)
    {
        showUsernameError(getString(i));
    }

    protected void showUsernameError(String s)
    {
        showUsernameError(true, s);
    }

    protected void showUsernameError(boolean flag, String s)
    {
        showError(flag, s, mUsernameStatusTextView, mUsernameValidationBackground);
    }

    public boolean validate()
    {
        mValidState = true;
        updateSecretHint();
        if (TextUtils.isEmpty(mUsernameEditText.getText()))
        {
            showUsernameError(0x7f07000b);
            mValidState = false;
        } else
        {
            hideUsernameError();
        }
        if (!validateSecret(mSecretEditText.getText().toString()))
        {
            mValidState = false;
        }
        mValidated = true;
        return mValidState;
    }

    private class SecretKeyListener extends NumberKeyListener
    {

        protected char[] getAcceptedChars()
        {
            return Base32String.sInputAlphabet.toCharArray();
        }

        public int getInputType()
        {
            return 0x80001;
        }

        SecretKeyListener()
        {
        }
    }

}
