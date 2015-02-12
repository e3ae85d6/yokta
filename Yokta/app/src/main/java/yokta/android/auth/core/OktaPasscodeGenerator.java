// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;

import a.a.a.a;
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package yokta.android.auth.core:
//            Base32String, PasscodeClock, PasscodeGenerator

public class OktaPasscodeGenerator
{

    public static final int PASS_CODE_LEN = 6;
    public static final int TIME_STEP = 30;
    private final PasscodeClock mClock;
    private final PasscodeGenerator mGenerator;

    public OktaPasscodeGenerator(String s)
    {
        this(s, 30);
    }

    OktaPasscodeGenerator(String s, int i)
    {
        a.a(s, "Secret can not be null.");
        boolean flag;
        byte abyte0[];
        Mac mac;
        if (s.length() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        a.a(flag, "Secret can not be empty.");
        abyte0 = Base32String.decode(s);
        mac = Mac.getInstance("HMACSHA1");
        mac.init(new SecretKeySpec(abyte0, ""));
        mClock = new PasscodeClock(i);
        mGenerator = new PasscodeGenerator(mac, 6);
    }

    public static String generate(String s)
    {
        return generate(s, System.currentTimeMillis());
    }

    public static String generate(String s, long l)
    {
        if (s == null || s.length() == 0)
        {
            return "Null or empty secret";
        }
        String s1;
        try
        {
            s1 = (new OktaPasscodeGenerator(s)).generate(l);
        }
        catch (GeneralSecurityException generalsecurityexception)
        {
            return "General security exception";
        }
        catch (Base32String.DecodingException decodingexception)
        {
            return "Decoding exception";
        }
        return s1;
    }

    public String generate()
    {
        return mGenerator.generate(mClock.getCurrentInterval());
    }

    public String generate(long l)
    {
        return mGenerator.generate(mClock.getInterval(l));
    }

    public String generateByInterval(long l)
    {
        return mGenerator.generate(l);
    }
}
