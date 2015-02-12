// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package deema.yokta.auth.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Base32String {

	public static final String sBase32Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    public static final String sInputAlphabet;
    private static final Base32String sInstance = new Base32String();
    private static final String sOne = "1";
    private static final String sSeparator = "-";
    private static final String sWhitespace = " ";
    private static final String sZero = "0";
    private final String mAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private final Map mCharMap;
    private final char mDigits[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
    private final int mMask;
    private final int mShift;

    protected Base32String()
    {
        mMask = -1 + mDigits.length;
        mShift = Integer.numberOfTrailingZeros(mDigits.length);
        HashMap hashmap = new HashMap();
        for (int i = 0; i < mDigits.length; i++)
        {
            hashmap.put(Character.valueOf(mDigits[i]), Integer.valueOf(i));
        }

        mCharMap = Collections.unmodifiableMap(hashmap);
    }

    public static byte[] decode(String s) throws DecodingException
    {
        return getInstance().decodeInternal(s);
    }

    public static String encode(byte abyte0[])
    {
        return getInstance().encodeInternal(abyte0);
    }

    static Base32String getInstance()
    {
        return sInstance;
    }

    protected byte[] decodeInternal(String s) throws DecodingException
    {
        int i = 0;
        String s1 = s.trim().replaceAll("-", "").replaceAll(" ", "").replaceAll("0", "O").replaceAll("1", "I").toUpperCase();
        if (s1.length() == 0)
        {
            return new byte[0];
        }
        byte abyte0[] = new byte[(s1.length() * mShift) / 8];
        char ac[] = s1.toCharArray();
        int j = ac.length;
        int k = 0;
        int l = 0;
        int i1 = 0;
        while (k < j) 
        {
            char c = ac[k];
            if (!mCharMap.containsKey(Character.valueOf(c)))
            {
                throw new DecodingException((new StringBuilder()).append("Illegal character: ").append(c).toString());
            }
            int j1 = i << mShift | ((Integer)mCharMap.get(Character.valueOf(c))).intValue() & mMask;
            int k1 = l + mShift;
            int l1;
            if (k1 >= 8)
            {
                l1 = i1 + 1;
                abyte0[i1] = (byte)(j1 >> k1 - 8);
                k1 -= 8;
            } else
            {
                l1 = i1;
            }
            k++;
            i1 = l1;
            l = k1;
            i = j1;
        }
        return abyte0;
    }

    protected String encodeInternal(byte abyte0[])
    {
        if (abyte0.length == 0)
        {
            return "";
        }
        if (abyte0.length >= 0x10000000)
        {
            throw new IllegalArgumentException();
        }
        StringBuilder stringbuilder = new StringBuilder((-1 + (8 * abyte0.length + mShift)) / mShift);
        int i = abyte0[0];
        int j = 1;
        int k = 8;
        while (k > 0 || j < abyte0.length) 
        {
            int i1;
            int j1;
            int k1;
            int l1;
            if (k < mShift)
            {
                if (j < abyte0.length)
                {
                    int l2 = i << 8;
                    j1 = j + 1;
                    i1 = l2 | 0xff & abyte0[j];
                    k += 8;
                } else
                {
                    int i2 = mShift - k;
                    int j2 = i << i2;
                    k += i2;
                    int k2 = j;
                    i1 = j2;
                    j1 = k2;
                }
            } else
            {
                int l = j;
                i1 = i;
                j1 = l;
            }
            k1 = mMask & i1 >> k - mShift;
            k -= mShift;
            stringbuilder.append(mDigits[k1]);
            l1 = j1;
            i = i1;
            j = l1;
        }
        return stringbuilder.toString();
    }

    static 
    {
        sInputAlphabet = (new StringBuilder()).append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toLowerCase(Locale.ENGLISH)).append("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toUpperCase(Locale.ENGLISH)).append("-").append(" ").append("0").append("1").toString();
    }

    public static class DecodingException extends Exception {
        public DecodingException(String s)
        {
            super(s);
        }
    }

}
