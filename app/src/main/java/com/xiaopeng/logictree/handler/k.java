package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.text.TextUtils;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.ShellCmdListener;
import java.util.LinkedList;
import java.util.function.ToIntFunction;

/* compiled from: ShellCmd.java */
/* loaded from: classes12.dex */
public class k extends h {
    private final AfterSalesManager vl;
    private final ShellCmdListener vp;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public /* synthetic */ void a(int i, String str, boolean z) {
        char c;
        String trim;
        String aV;
        String trim2;
        int i2;
        String str2 = this.YA[0];
        int hashCode = str2.hashCode();
        if (hashCode != 1567) {
            switch (hashCode) {
                case 49:
                    if (str2.equals("1")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 52:
                    if (str2.equals("4")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 53:
                    if (str2.equals("5")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 54:
                    if (str2.equals("6")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 55:
                    if (str2.equals("7")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 56:
                    if (str2.equals("8")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 57:
                    if (str2.equals("9")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str2.equals("10")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
            case 1:
                if (i == 0) {
                    com.xiaopeng.logictree.d.pP();
                    return;
                } else {
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
            case 2:
                com.xiaopeng.logictree.d.pP();
                return;
            case 3:
                if (str != null && str.length() > 0) {
                    if (this.YA.length > 3) {
                        trim = com.xiaopeng.commonfunc.utils.i.g(str, this.YA[2], this.YA[3]).trim();
                    } else {
                        trim = com.xiaopeng.commonfunc.utils.i.aV(str).trim();
                    }
                    if (TextUtils.isEmpty(trim)) {
                        com.xiaopeng.logictree.d.pQ();
                        return;
                    } else {
                        com.xiaopeng.logictree.d.dR(trim);
                        return;
                    }
                }
                com.xiaopeng.logictree.d.pQ();
                return;
            case 4:
                if (str == null || str.length() <= 0) {
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
                try {
                    com.xiaopeng.logictree.d.a(Integer.valueOf(Integer.parseInt(com.xiaopeng.commonfunc.utils.i.g(str, this.YA.length > 2 ? this.YA[2] : "", this.YA.length > 3 ? this.YA[3] : ""), this.YA.length > 4 ? Integer.parseInt(this.YA[4]) : 10)));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
            case 5:
                if (str != null && str.length() > 0) {
                    if (this.YA.length > 5) {
                        aV = com.xiaopeng.commonfunc.utils.i.g(str, this.YA[4], this.YA[5]);
                    } else {
                        aV = com.xiaopeng.commonfunc.utils.i.aV(str);
                    }
                    com.xiaopeng.logictree.d.dR(aV);
                    return;
                }
                com.xiaopeng.logictree.d.pQ();
                return;
            case 6:
                if (str == null || str.length() <= 0) {
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
                try {
                    com.xiaopeng.logictree.d.a(Integer.valueOf(Integer.parseInt(com.xiaopeng.commonfunc.utils.i.g(str, this.YA.length > 4 ? this.YA[4] : "", this.YA.length > 5 ? this.YA[5] : ""), this.YA.length > 6 ? Integer.parseInt(this.YA[6]) : 10)));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
            case 7:
                if (str != null && str.length() > 0) {
                    if (this.YA.length > 4) {
                        trim2 = com.xiaopeng.commonfunc.utils.i.g(str, this.YA[3], this.YA[4]).trim();
                    } else {
                        trim2 = com.xiaopeng.commonfunc.utils.i.aV(str).trim();
                    }
                    if (TextUtils.isEmpty(trim2)) {
                        com.xiaopeng.logictree.d.pQ();
                        return;
                    } else {
                        com.xiaopeng.logictree.d.dR(trim2);
                        return;
                    }
                }
                com.xiaopeng.logictree.d.pQ();
                return;
            case '\b':
                if (str == null || str.length() <= 0) {
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
                try {
                    com.xiaopeng.logictree.d.a(Integer.valueOf(Integer.parseInt(com.xiaopeng.commonfunc.utils.i.g(str, this.YA.length > 3 ? this.YA[3] : "", this.YA.length > 4 ? this.YA[4] : ""), this.YA.length > 5 ? Integer.parseInt(this.YA[5]) : 10)));
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    com.xiaopeng.logictree.d.pQ();
                    return;
                }
            case '\t':
                if (str != null && str.length() > 0) {
                    try {
                        LinkedList linkedList = new LinkedList();
                        int length = (this.YA.length - 3) / 3;
                        if ((this.YA.length - 3) % 3 > 0) {
                            length++;
                        }
                        if (length == 0) {
                            linkedList.add(Integer.valueOf(Integer.parseInt(com.xiaopeng.commonfunc.utils.i.g(str, this.YA.length > 3 ? this.YA[3] : "", this.YA.length > 4 ? this.YA[4] : ""), this.YA.length > 5 ? Integer.parseInt(this.YA[5]) : 10)));
                        } else {
                            for (int i3 = 0; i3 < length; i3++) {
                                int i4 = (i3 * 3) + 3;
                                int i5 = i4 + 1;
                                int i6 = i4 + 2;
                                String g = com.xiaopeng.commonfunc.utils.i.g(str, this.YA.length > i4 ? this.YA[i4] : "", this.YA.length > i5 ? this.YA[i5] : "");
                                if (this.YA.length <= i6) {
                                    i2 = 10;
                                } else {
                                    i2 = Integer.parseInt(this.YA[i6]);
                                }
                                linkedList.add(Integer.valueOf(Integer.parseInt(g, i2)));
                            }
                        }
                        if (linkedList.size() > 0) {
                            com.xiaopeng.logictree.d.d(linkedList.stream().mapToInt(new ToIntFunction() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$k$UV1wDVoVlbcxpr8zevj_aMFtUGw
                                @Override // java.util.function.ToIntFunction
                                public final int applyAsInt(Object obj) {
                                    int intValue;
                                    intValue = ((Integer) obj).intValue();
                                    return intValue;
                                }
                            }).toArray());
                            return;
                        } else {
                            com.xiaopeng.logictree.d.pO();
                            return;
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        com.xiaopeng.logictree.d.pO();
                        return;
                    }
                }
                com.xiaopeng.logictree.d.pQ();
                return;
            default:
                return;
        }
    }

    public k(Application application) {
        super(application);
        this.vp = new ShellCmdListener() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$k$f7GTfXKCAretnnjEpOkLsq6VUUY
            public final void onShellResponse(int i, String str, boolean z) {
                k.this.a(i, str, z);
            }
        };
        this.CLASS_NAME = "ShellCmd";
        this.vl = (AfterSalesManager) this.context.getSystemService("xiaopeng_aftersales");
        this.vl.registerShellCmdListener(this.vp);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009c A[Catch: all -> 0x0119, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x002a, B:40:0x0097, B:42:0x009c, B:44:0x00c0, B:46:0x00ca, B:58:0x0113, B:47:0x00dc, B:49:0x00e8, B:50:0x00f1, B:52:0x00f6, B:54:0x00fd, B:55:0x0105, B:9:0x002f, B:12:0x003b, B:15:0x0046, B:18:0x0050, B:21:0x005a, B:24:0x0064, B:27:0x006e, B:30:0x0078, B:33:0x0082, B:36:0x008c), top: B:65:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00dc A[Catch: all -> 0x0119, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x002a, B:40:0x0097, B:42:0x009c, B:44:0x00c0, B:46:0x00ca, B:58:0x0113, B:47:0x00dc, B:49:0x00e8, B:50:0x00f1, B:52:0x00f6, B:54:0x00fd, B:55:0x0105, B:9:0x002f, B:12:0x003b, B:15:0x0046, B:18:0x0050, B:21:0x005a, B:24:0x0064, B:27:0x006e, B:30:0x0078, B:33:0x0082, B:36:0x008c), top: B:65:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0105 A[Catch: all -> 0x0119, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x002a, B:40:0x0097, B:42:0x009c, B:44:0x00c0, B:46:0x00ca, B:58:0x0113, B:47:0x00dc, B:49:0x00e8, B:50:0x00f1, B:52:0x00f6, B:54:0x00fd, B:55:0x0105, B:9:0x002f, B:12:0x003b, B:15:0x0046, B:18:0x0050, B:21:0x005a, B:24:0x0064, B:27:0x006e, B:30:0x0078, B:33:0x0082, B:36:0x008c), top: B:65:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0113 A[Catch: all -> 0x0119, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x000c, B:7:0x002a, B:40:0x0097, B:42:0x009c, B:44:0x00c0, B:46:0x00ca, B:58:0x0113, B:47:0x00dc, B:49:0x00e8, B:50:0x00f1, B:52:0x00f6, B:54:0x00fd, B:55:0x0105, B:9:0x002f, B:12:0x003b, B:15:0x0046, B:18:0x0050, B:21:0x005a, B:24:0x0064, B:27:0x006e, B:30:0x0078, B:33:0x0082, B:36:0x008c), top: B:65:0x0001 }] */
    @Override // com.xiaopeng.logictree.handler.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String a(com.xiaopeng.logictree.a r9) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.logictree.handler.k.a(com.xiaopeng.logictree.a):java.lang.String");
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        this.vl.unregisterShellCmdListener(this.vp);
        super.destroy();
    }
}
