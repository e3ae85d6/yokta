// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package deema.yokta.auth.core;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.crypto.Mac;

public class PasscodeGenerator
{

    private final int mModulo;
    private final int mPasscodeLength;
    private final Signer mSigner;

    public PasscodeGenerator(Signer signer, int i)
    {
        //a.a(signer, "Signer can not be null!");
        boolean flag;
        Object aobj[];
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        aobj = new Object[1];
        aobj[0] = Integer.valueOf(i);
        //a.a(flag, "Passcode should be positive (was %d).", aobj);
        mSigner = signer;
        mPasscodeLength = i;
        mModulo = (int)Math.pow(10D, i);
    }

    public PasscodeGenerator(Mac mac, int i)
    {
        this(((Signer) (new MacSigner(mac))), i);
    }

    static int hashToInt(byte buf[], int offset)
    {
        boolean flag = buf.length >= offset + 4;
        DataInputStream datainputstream;
        int j;
        //a.a(flag);
        datainputstream = new DataInputStream(new ByteArrayInputStream(buf, offset, buf.length - offset));
        try
        {
            j = datainputstream.readInt();
        }
        catch (IOException ioexception)
        {
            throw new IllegalStateException(ioexception);
        }
        return j;
    }

    static String normalizePasscode(int i, int j)
    {
        String s = Integer.toString(i);
        for (int k = s.length(); k < j; k++)
        {
            s = (new StringBuilder()).append("0").append(s).toString();
        }

        return s;
    }

    public String generate(long l)
    {
        return generate(ByteBuffer.allocate(8).putLong(l).array());
    }

    public String generate(byte abyte0[])
    {
        byte abyte1[] = mSigner.sign(abyte0);
        return normalizePasscode((0x7fffffff & hashToInt(abyte1, 0xf & abyte1[-1 + abyte1.length])) % mModulo, mPasscodeLength);
    }

    private static class MacSigner implements Signer {

        private final Mac mMac;

        public byte[] sign(byte input[]) {
            return mMac.doFinal(input);
        }

        public MacSigner(Mac mac) {
            //a.a(mac, "MAC can not be null");
            mMac = mac;
        }
    }


    private interface Signer {
        public abstract byte[] sign(byte input[]);
    }

}
