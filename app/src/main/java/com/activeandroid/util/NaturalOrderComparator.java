package com.activeandroid.util;

import java.util.Comparator;

/* loaded from: classes11.dex */
public class NaturalOrderComparator implements Comparator<Object> {
    int compareRight(String str, String str2) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            char charAt = charAt(str, i);
            char charAt2 = charAt(str2, i2);
            if (!Character.isDigit(charAt) && !Character.isDigit(charAt2)) {
                return i3;
            }
            if (!Character.isDigit(charAt)) {
                return -1;
            }
            if (!Character.isDigit(charAt2)) {
                return 1;
            }
            if (charAt < charAt2) {
                if (i3 == 0) {
                    i3 = -1;
                }
            } else if (charAt > charAt2) {
                if (i3 == 0) {
                    i3 = 1;
                }
            } else if (charAt == 0 && charAt2 == 0) {
                return i3;
            }
            i++;
            i2++;
        }
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        int compareRight;
        String obj3 = obj.toString();
        String obj4 = obj2.toString();
        int i = 0;
        int i2 = 0;
        while (true) {
            char charAt = charAt(obj3, i);
            char charAt2 = charAt(obj4, i2);
            int i3 = i;
            int i4 = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt) && charAt != '0') {
                    break;
                }
                if (charAt == '0') {
                    i4++;
                } else {
                    i4 = 0;
                }
                i3++;
                charAt = charAt(obj3, i3);
            }
            int i5 = i2;
            int i6 = 0;
            while (true) {
                if (!Character.isSpaceChar(charAt2) && charAt2 != '0') {
                    break;
                }
                if (charAt2 == '0') {
                    i6++;
                } else {
                    i6 = 0;
                }
                i5++;
                charAt2 = charAt(obj4, i5);
            }
            if (Character.isDigit(charAt) && Character.isDigit(charAt2) && (compareRight = compareRight(obj3.substring(i3), obj4.substring(i5))) != 0) {
                return compareRight;
            }
            if (charAt == 0 && charAt2 == 0) {
                return i4 - i6;
            }
            if (charAt < charAt2) {
                return -1;
            }
            if (charAt > charAt2) {
                return 1;
            }
            i = i3 + 1;
            i2 = i5 + 1;
        }
    }

    static char charAt(String str, int i) {
        if (i >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i);
    }
}
