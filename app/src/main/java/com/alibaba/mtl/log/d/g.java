package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Comparator;

/* compiled from: KeyArraySorter.java */
/* loaded from: classes11.dex */
public class g {
    private static g cY = new g();
    private b cZ = new b();
    private a da = new a();

    private g() {
    }

    public static g aP() {
        return cY;
    }

    public String[] a(String[] strArr, boolean z) {
        Comparator comparator;
        if (z) {
            comparator = this.da;
        } else {
            comparator = this.cZ;
        }
        if (comparator != null && strArr != null && strArr.length > 0) {
            Arrays.sort(strArr, comparator);
            return strArr;
        }
        return null;
    }

    /* compiled from: KeyArraySorter.java */
    /* loaded from: classes11.dex */
    private class b implements Comparator<String> {
        private b() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                return (-1) * str.compareTo(str2);
            }
            return 0;
        }
    }

    /* compiled from: KeyArraySorter.java */
    /* loaded from: classes11.dex */
    private class a implements Comparator<String> {
        private a() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                return str.compareTo(str2);
            }
            return 0;
        }
    }
}
