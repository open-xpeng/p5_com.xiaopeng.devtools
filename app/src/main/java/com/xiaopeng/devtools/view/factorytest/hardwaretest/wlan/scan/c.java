package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import java.util.Locale;

/* compiled from: HelpUtils.java */
/* loaded from: classes12.dex */
public class c {
    private static final String TAG = c.class.getName();
    private static String Nl = null;

    private c() {
    }

    public static boolean a(Context context, MenuItem menuItem, String str) {
        if (TextUtils.isEmpty(str)) {
            menuItem.setVisible(false);
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", a(context, Uri.parse(str)));
        intent.setFlags(276824064);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            menuItem.setIntent(intent);
            menuItem.setShowAsAction(0);
            menuItem.setVisible(true);
            return true;
        }
        menuItem.setVisible(false);
        return false;
    }

    public static Uri a(Context context, Uri uri) {
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.appendQueryParameter("hl", Locale.getDefault().toString());
        if (Nl == null) {
            try {
                Nl = Integer.toString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                buildUpon.appendQueryParameter("version", Nl);
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf(TAG, "Invalid package name for context", e);
            }
        } else {
            buildUpon.appendQueryParameter("version", Nl);
        }
        return buildUpon.build();
    }
}
