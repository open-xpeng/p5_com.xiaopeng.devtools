package com.xiaopeng.logictree;

import com.xiaopeng.a.a;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;

/* compiled from: LogicTreeHelper.java */
/* loaded from: classes12.dex */
public class d {
    public static final String XV = a.c.ec("LOGICTREE_UPGRADE_FOLDER");

    public static void dR(String str) {
        com.xiaopeng.lib.utils.c.i("LogicTreeHelper", "responseResult : %s", str);
        EventBus.getDefault().post(new b(str));
    }

    public static void a(Integer num) {
        com.xiaopeng.lib.utils.c.i("LogicTreeHelper", "responseResult : %d", num);
        EventBus.getDefault().post(new b(num));
    }

    public static void a(Long l) {
        com.xiaopeng.lib.utils.c.i("LogicTreeHelper", "responseResult : %ld", l);
        EventBus.getDefault().post(new b(l));
    }

    public static void d(int[] iArr) {
        com.xiaopeng.lib.utils.c.i("LogicTreeHelper", "responseResult : %s", Arrays.toString(iArr));
        EventBus.getDefault().post(new b(iArr));
    }

    public static void g(String[] strArr) {
        com.xiaopeng.lib.utils.c.i("LogicTreeHelper", "responseResult : %s", Arrays.toString(strArr));
        EventBus.getDefault().post(new b(strArr));
    }

    public static void pO() {
        com.xiaopeng.lib.utils.c.g("LogicTreeHelper", "responseResult : LOGIC_ACTION_RESPONSE_RESULT_FAIL");
        EventBus.getDefault().post(new b("LOGIC_ACTION_RESPONSE_RESULT_FAIL"));
    }

    public static void pP() {
        com.xiaopeng.lib.utils.c.g("LogicTreeHelper", "responseResult : LOGIC_ACTION_RESPONSE_RESULT_OK");
        EventBus.getDefault().post(new b("LOGIC_ACTION_RESPONSE_RESULT_OK"));
    }

    public static void pQ() {
        com.xiaopeng.lib.utils.c.g("LogicTreeHelper", "responseResult : LOGIC_ACTION_RESPONSE_EXEC_NO_RESULT");
        EventBus.getDefault().post(new b("LOGIC_ACTION_RESPONSE_EXEC_NO_RESULT"));
    }

    public static void aR(boolean z) {
        if (z) {
            pO();
        }
    }

    public static void aS(boolean z) {
        if (z) {
            pP();
        }
    }
}
