// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;

import android.net.Uri;
import java.text.ParseException;
import java.util.List;

public class UriParser
{
    static final String DEFAULT_USER = "Default User";
    static final String OTP_SCHEME = "otpauth";
    static final String SECRET_PARAM = "secret";
    static final String TOTP_AUTHORITY = "totp";
    static final String TOTP_SCHEME = "totp";

    public UriParser()
    {
    }

    public static Result parse(Uri uri) throws ParseException
    {
        String s = uri.getScheme();
        String s1 = "Default User";
		String s2;
        if ("otpauth".equalsIgnoreCase(s))
        {
            String s3 = uri.getAuthority();
            if (s3 == null || !s3.equalsIgnoreCase("totp"))
            {
                Object aobj2[] = new Object[3];
                aobj2[0] = uri.toString();
                aobj2[1] = s3;
                aobj2[2] = "totp";
                throw new ParseException(String.format("URI \"%s\" had unsupported authority \"%s\". Supported authority: \"%s\"", aobj2), 0);
            }
            List list = uri.getPathSegments();
            Object aobj[];

            Object aobj1[];
            String s4;
            String s5;
            if (list.size() > 0 && ((String)list.get(0)).length() > 0)
            {
                s4 = (String)list.get(0);
            } else
            {
                s4 = s1;
            }
            s5 = uri.getQueryParameter("secret");
            s1 = s4;
            s2 = s5;
        } else
        if ("totp".equalsIgnoreCase(s))
        {
            if (uri.getAuthority() != null && uri.getAuthority().length() > 0)
            {
                s1 = uri.getAuthority();
            }
            s2 = uri.getFragment();
        } else
        {
            throw new ParseException(String.format("URI \"%s\" had invalid authority.", uri.toString()), 0);
        }
        if (s2 == null || s2.length() == 0)
        {
            throw new ParseException(String.format("URI \"%s\" had no secret.", uri.toString()), 0);
        } else
        {
            return new Result(s1, s2);
        }
    }

    private static class Result
    {

        private final String mSecret;
        private final String mUser;

        public String getSecret()
        {
            return mSecret;
        }

        public String getUser()
        {
            return mUser;
        }

        public Result(String user, String password)
        {
            mUser = user;
            mSecret = password;
        }
    }

}
