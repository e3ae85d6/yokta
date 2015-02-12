// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package yokta.android.auth.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package yokta.android.auth.core:
//            IntentResult

public class IntentIntegrator
{

    public static final Collection ALL_CODE_TYPES = null;
    private static final String BSPLUS_PACKAGE = "com.srowen.bs.android";
    private static final String BS_PACKAGE = "com.google.zxing.client.android";
    public static final Collection DATA_MATRIX_TYPES = Collections.singleton("DATA_MATRIX");
    public static final String DEFAULT_MESSAGE = "This application requires Barcode Scanner. Would you like to install it?";
    public static final String DEFAULT_NO = "No";
    public static final String DEFAULT_TITLE = "Install Barcode Scanner?";
    public static final String DEFAULT_YES = "Yes";
    public static final Collection ONE_D_CODE_TYPES = list(new String[] {
        "UPC_A", "UPC_E", "EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128", "ITF", "RSS_14", "RSS_EXPANDED"
    });
    public static final Collection PRODUCT_CODE_TYPES = list(new String[] {
        "UPC_A", "UPC_E", "EAN_8", "EAN_13", "RSS_14"
    });
    public static final Collection QR_CODE_TYPES = Collections.singleton("QR_CODE");
    public static final int REQUEST_CODE = 49374;
    private static final String TAG = IntentIntegrator.class.getSimpleName();
    public static final List TARGET_ALL_KNOWN = list(new String[] {
        "com.google.zxing.client.android", "com.srowen.bs.android", "com.srowen.bs.android.simple"
    });
    public static final List TARGET_BARCODE_SCANNER_ONLY = Collections.singletonList("com.google.zxing.client.android");
    private final Activity activity;
    private String buttonNo;
    private String buttonYes;
    private String message;
    private final Map moreExtras = new HashMap(3);
    private List targetApplications;
    private String title;

    public IntentIntegrator(Activity activity1)
    {
        activity = activity1;
        title = "Install Barcode Scanner?";
        message = "This application requires Barcode Scanner. Would you like to install it?";
        buttonYes = "Yes";
        buttonNo = "No";
        targetApplications = TARGET_ALL_KNOWN;
    }

    private void attachMoreExtras(Intent intent)
    {
        for (Iterator iterator = moreExtras.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            Object obj = entry.getValue();
            if (obj instanceof Integer)
            {
                intent.putExtra(s, (Integer)obj);
            } else
            if (obj instanceof Long)
            {
                intent.putExtra(s, (Long)obj);
            } else
            if (obj instanceof Boolean)
            {
                intent.putExtra(s, (Boolean)obj);
            } else
            if (obj instanceof Double)
            {
                intent.putExtra(s, (Double)obj);
            } else
            if (obj instanceof Float)
            {
                intent.putExtra(s, (Float)obj);
            } else
            if (obj instanceof Bundle)
            {
                intent.putExtra(s, (Bundle)obj);
            } else
            {
                intent.putExtra(s, obj.toString());
            }
        }

    }

    private static boolean contains(Iterable iterable, String s)
    {
        for (Iterator iterator = iterable.iterator(); iterator.hasNext();)
        {
            if (s.equals(((ResolveInfo)iterator.next()).activityInfo.packageName))
            {
                return true;
            }
        }

        return false;
    }

    private String findTargetAppPackage(Intent intent)
    {
label0:
        {
            List list1 = activity.getPackageManager().queryIntentActivities(intent, 0x10000);
            if (list1 == null)
            {
                break label0;
            }
            Iterator iterator = targetApplications.iterator();
            String s;
            do
            {
                if (!iterator.hasNext())
                {
                    break label0;
                }
                s = (String)iterator.next();
            } while (!contains(list1, s));
            return s;
        }
        return null;
    }

    private static List list(String as[])
    {
        return Collections.unmodifiableList(Arrays.asList(as));
    }

    public static IntentResult parseActivityResult(int i, int j, Intent intent)
    {
        if (i == 49374)
        {
            if (j == -1)
            {
                String s = intent.getStringExtra("SCAN_RESULT");
                String s1 = intent.getStringExtra("SCAN_RESULT_FORMAT");
                byte abyte0[] = intent.getByteArrayExtra("SCAN_RESULT_BYTES");
                int k = intent.getIntExtra("SCAN_RESULT_ORIENTATION", 0x80000000);
                Integer integer = null;
                if (k != 0x80000000)
                {
                    integer = Integer.valueOf(k);
                }
                return new IntentResult(s, s1, abyte0, integer, intent.getStringExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL"));
            } else
            {
                return new IntentResult();
            }
        } else
        {
            return null;
        }
    }

    private AlertDialog showDownloadDialog()
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String s = (String)targetApplications.get(0);
				Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("market://details?id=").append(s).toString()));
				try
				{
					activity.startActivity(intent);
					return;
				}
				catch (ActivityNotFoundException activitynotfoundexception)
				{
					Log.w(IntentIntegrator.TAG, (new StringBuilder()).append("Google Play is not installed; cannot install ").append(s).toString());
				}
			}
		});
        builder.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) { }
		});
        return builder.show();
    }

    public final void addExtra(String s, Object obj)
    {
        moreExtras.put(s, obj);
    }

    public String getButtonNo()
    {
        return buttonNo;
    }

    public String getButtonYes()
    {
        return buttonYes;
    }

    public String getMessage()
    {
        return message;
    }

    public Map getMoreExtras()
    {
        return moreExtras;
    }

    public Collection getTargetApplications()
    {
        return targetApplications;
    }

    public String getTitle()
    {
        return title;
    }

    public final AlertDialog initiateScan()
    {
        return initiateScan(ALL_CODE_TYPES);
    }

    public final AlertDialog initiateScan(Collection collection)
    {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.addCategory("android.intent.category.DEFAULT");
        if (collection != null)
        {
            StringBuilder stringbuilder = new StringBuilder();
            String s1;
            for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuilder.append(s1))
            {
                s1 = (String)iterator.next();
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.append(',');
                }
            }

            intent.putExtra("SCAN_FORMATS", stringbuilder.toString());
        }
        String s = findTargetAppPackage(intent);
        if (s == null)
        {
            return showDownloadDialog();
        } else
        {
            intent.setPackage(s);
            intent.addFlags(0x4000000);
            intent.addFlags(0x80000);
            attachMoreExtras(intent);
            startActivityForResult(intent, 49374);
            return null;
        }
    }

    public void setButtonNo(String s)
    {
        buttonNo = s;
    }

    public void setButtonNoByID(int i)
    {
        buttonNo = activity.getString(i);
    }

    public void setButtonYes(String s)
    {
        buttonYes = s;
    }

    public void setButtonYesByID(int i)
    {
        buttonYes = activity.getString(i);
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setMessageByID(int i)
    {
        message = activity.getString(i);
    }

    public void setSingleTargetApplication(String s)
    {
        targetApplications = Collections.singletonList(s);
    }

    public final void setTargetApplications(List list1)
    {
        if (list1.isEmpty())
        {
            throw new IllegalArgumentException("No target applications");
        } else
        {
            targetApplications = list1;
            return;
        }
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public void setTitleByID(int i)
    {
        title = activity.getString(i);
    }

    public final AlertDialog shareText(CharSequence charsequence)
    {
        return shareText(charsequence, "TEXT_TYPE");
    }

    public final AlertDialog shareText(CharSequence charsequence, CharSequence charsequence1)
    {
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setAction("com.google.zxing.client.android.ENCODE");
        intent.putExtra("ENCODE_TYPE", charsequence1);
        intent.putExtra("ENCODE_DATA", charsequence);
        String s = findTargetAppPackage(intent);
        if (s == null)
        {
            return showDownloadDialog();
        } else
        {
            intent.setPackage(s);
            intent.addFlags(0x4000000);
            intent.addFlags(0x80000);
            attachMoreExtras(intent);
            activity.startActivity(intent);
            return null;
        }
    }

    protected void startActivityForResult(Intent intent, int i)
    {
        activity.startActivityForResult(intent, i);
    }
}
