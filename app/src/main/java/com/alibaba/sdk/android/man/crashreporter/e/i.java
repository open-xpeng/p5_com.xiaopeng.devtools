package com.alibaba.sdk.android.man.crashreporter.e;

import java.util.Map;

/* loaded from: classes11.dex */
public class i {
    public static boolean b(String str) {
        if (str != null && str.length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean a(CharSequence charSequence) {
        int length;
        if (charSequence == null || (length = charSequence.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(CharSequence charSequence) {
        return !a(charSequence);
    }

    public static String a(String str, String str2) {
        if (a((CharSequence) str)) {
            return str2;
        }
        return str;
    }

    public static int a(String str) {
        if (str.length() > 0) {
            int i = 0;
            for (char c : str.toCharArray()) {
                i = c + (31 * i);
            }
            return i;
        }
        return 0;
    }

    public static String a(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return ((String) obj).toString();
            }
            if (obj instanceof Integer) {
                return "" + ((Integer) obj).intValue();
            } else if (obj instanceof Long) {
                return "" + ((Long) obj).longValue();
            } else if (obj instanceof Double) {
                return "" + ((Double) obj).doubleValue();
            } else if (obj instanceof Float) {
                return "" + ((Float) obj).floatValue();
            } else if (obj instanceof Short) {
                return "" + ((int) ((Short) obj).shortValue());
            } else if (obj instanceof Byte) {
                return "" + ((int) ((Byte) obj).byteValue());
            } else if (obj instanceof Boolean) {
                return ((Boolean) obj).toString();
            } else {
                if (obj instanceof Character) {
                    return ((Character) obj).toString();
                }
                return obj.toString();
            }
        }
        return "";
    }

    public static String c(Map<String, String> map) {
        if (map != null) {
            boolean z = true;
            StringBuffer stringBuffer = new StringBuffer();
            for (String str : map.keySet()) {
                String str2 = map.get(str);
                if (str2 != null && str != null) {
                    if (z) {
                        if ("--invalid--".equals(str2)) {
                            stringBuffer.append(str);
                        } else {
                            stringBuffer.append(str + "=" + str2);
                        }
                        z = false;
                    } else if (!"--invalid--".equals(str2)) {
                        stringBuffer.append(",");
                        stringBuffer.append(str + "=" + str2);
                    } else {
                        stringBuffer.append(",");
                        stringBuffer.append(str);
                    }
                }
            }
            return stringBuffer.toString();
        }
        return null;
    }
}
