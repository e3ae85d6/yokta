// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package deema.yokta.auth.core;

//import a.a.a.a;
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

    public OktaPasscodeGenerator(String secret) throws Base32String.DecodingException, GeneralSecurityException
    {
        this(secret, TIME_STEP);
    }

    OktaPasscodeGenerator(String secret, int i) throws Base32String.DecodingException, GeneralSecurityException
    {
        //a.a(secret, "Secret can not be null.");
        boolean flag = secret.length() > 0;
        byte abyte0[];
        Mac mac;
        //a.a(flag, "Secret can not be empty.");
        abyte0 = Base32String.decode(secret);
        mac = Mac.getInstance("HMACSHA1");
        mac.init(new SecretKeySpec(abyte0, ""));
        mClock = new PasscodeClock(i);
        mGenerator = new PasscodeGenerator(mac, PASS_CODE_LEN);
    }

    public static String generate(String s)
    {
        return generate(s, System.currentTimeMillis());
    }

    public static String generate(String secret, long l)
    {
        if (secret == null || secret.length() == 0)
        {
            return "Null or empty secret";
        }
        String s1;
        try
        {
            s1 = (new OktaPasscodeGenerator(secret)).generate(l);
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
