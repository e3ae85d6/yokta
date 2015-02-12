// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;


public final class IntentResult
{

    private final String contents;
    private final String errorCorrectionLevel;
    private final String formatName;
    private final Integer orientation;
    private final byte rawBytes[];

    IntentResult()
    {
        this(null, null, null, null, null);
    }

    IntentResult(String s, String s1, byte abyte0[], Integer integer, String s2)
    {
        contents = s;
        formatName = s1;
        rawBytes = abyte0;
        orientation = integer;
        errorCorrectionLevel = s2;
    }

    public String getContents()
    {
        return contents;
    }

    public String getErrorCorrectionLevel()
    {
        return errorCorrectionLevel;
    }

    public String getFormatName()
    {
        return formatName;
    }

    public Integer getOrientation()
    {
        return orientation;
    }

    public byte[] getRawBytes()
    {
        return rawBytes;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(100);
        stringbuilder.append("Format: ").append(formatName).append('\n');
        stringbuilder.append("Contents: ").append(contents).append('\n');
        int i;
        if (rawBytes == null)
        {
            i = 0;
        } else
        {
            i = rawBytes.length;
        }
        stringbuilder.append("Raw bytes: (").append(i).append(" bytes)\n");
        stringbuilder.append("Orientation: ").append(orientation).append('\n');
        stringbuilder.append("EC level: ").append(errorCorrectionLevel).append('\n');
        return stringbuilder.toString();
    }
}
