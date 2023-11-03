package com.xiaopeng.lib.http.b;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.xiaopeng.lib.http.d;

/* compiled from: DelegateHelper.java */
/* loaded from: classes12.dex */
public class a {
    private static final Uri Vg = Uri.parse("content://com.xiaopeng.system.delegate");

    public static String a(Context context, String[] strArr, byte[] bArr) {
        String str;
        if (context == null) {
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (strArr.equals(d.UK)) {
            str = "buildTokenDataAll";
        } else {
            str = "buildTokenDataAC";
        }
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", bArr);
        Bundle call = contentResolver.call(Vg, str, (String) null, bundle);
        if (call == null) {
            return null;
        }
        return call.getString("result");
    }
}
