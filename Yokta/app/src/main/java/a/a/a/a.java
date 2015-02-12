// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package a.a.a;


public final class a
{

    public static Object a(Object obj)
    {
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            return obj;
        }
    }

    public static Object a(Object obj, Object obj1)
    {
        if (obj == null)
        {
            throw new NullPointerException(String.valueOf(obj1));
        } else
        {
            return obj;
        }
    }

    static String a(String s, Object aobj[])
    {
        StringBuilder stringbuilder;
label0:
        {
            int i = 0;
            String s1 = String.valueOf(s);
            stringbuilder = new StringBuilder(s1.length() + 16 * aobj.length);
            int j = 0;
label1:
            do
            {
                int j1;
label2:
                {
                    if (i < aobj.length)
                    {
                        j1 = s1.indexOf("%s", j);
                        if (j1 != -1)
                        {
                            break label2;
                        }
                    }
                    stringbuilder.append(s1.substring(j));
                    if (i >= aobj.length)
                    {
                        break label0;
                    }
                    stringbuilder.append(" [");
                    int k = i + 1;
                    stringbuilder.append(aobj[i]);
                    int i1;
                    for (int l = k; l < aobj.length; l = i1)
                    {
                        stringbuilder.append(", ");
                        i1 = l + 1;
                        stringbuilder.append(aobj[l]);
                    }

                    break label1;
                }
                stringbuilder.append(s1.substring(j, j1));
                int k1 = i + 1;
                stringbuilder.append(aobj[i]);
                j = j1 + 2;
                i = k1;
            } while (true);
            stringbuilder.append(']');
        }
        return stringbuilder.toString();
    }

    public static void a(boolean flag)
    {
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return;
        }
    }

    public static void a(boolean flag, Object obj)
    {
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf(obj));
        } else
        {
            return;
        }
    }

    public static void a(boolean flag, String s, Object aobj[])
    {
        if (!flag)
        {
            throw new IllegalArgumentException(a(s, aobj));
        } else
        {
            return;
        }
    }
}
