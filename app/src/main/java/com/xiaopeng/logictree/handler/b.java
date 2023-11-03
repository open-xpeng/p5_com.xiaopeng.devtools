package com.xiaopeng.logictree.handler;

import android.app.Application;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisData;
import java.util.LinkedList;
import java.util.List;
import java.util.function.ToIntFunction;

/* compiled from: DiagnosisCode.java */
/* loaded from: classes12.dex */
public class b extends h {
    private final com.xiaopeng.commonfunc.b.b.a vs;

    public b(Application application) {
        super(application);
        this.CLASS_NAME = "DiagnosisCode";
        this.vs = new com.xiaopeng.commonfunc.b.b.a(this.context);
        this.vs.dJ();
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            long startTime = aVar.getStartTime();
            long pD = aVar.pD();
            if (pD - startTime < 1800000) {
                startTime = pD - 1800000;
            }
            List<Integer> a = this.vs.a(this.YA[1], startTime, pD);
            if (a.size() == 0) {
                a.add(0);
            }
            com.xiaopeng.logictree.d.d(a.stream().mapToInt(new ToIntFunction() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$b$UV1wDVoVlbcxpr8zevj_aMFtUGw
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int intValue;
                    intValue = ((Integer) obj).intValue();
                    return intValue;
                }
            }).toArray());
        } else if (a(this.YA, new String[]{"2"})) {
            long startTime2 = aVar.getStartTime();
            long pD2 = aVar.pD();
            if (pD2 - startTime2 < 1800000) {
                startTime2 = pD2 - 1800000;
            }
            List<DiagnosisData> b = this.vs.b(this.YA[1], startTime2, pD2);
            LinkedList linkedList = new LinkedList();
            for (DiagnosisData diagnosisData : b) {
                String str = diagnosisData.getErrorCode() + "|" + diagnosisData.getErrorMsg();
                if (!linkedList.contains(str)) {
                    linkedList.add(str);
                }
            }
            if (linkedList.size() == 0) {
                linkedList.add("");
            }
            com.xiaopeng.logictree.d.g((String[]) linkedList.toArray(new String[linkedList.size()]));
        } else if (a(this.YA, new String[]{"3"})) {
            long currentTimeMillis = System.currentTimeMillis();
            List<Integer> a2 = this.vs.a(this.YA[1], currentTimeMillis - 1800000, currentTimeMillis);
            if (a2.size() == 0) {
                a2.add(0);
            }
            com.xiaopeng.logictree.d.d(a2.stream().mapToInt(new ToIntFunction() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$b$UV1wDVoVlbcxpr8zevj_aMFtUGw
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int intValue;
                    intValue = ((Integer) obj).intValue();
                    return intValue;
                }
            }).toArray());
        } else if (a(this.YA, new String[]{"4"})) {
            long currentTimeMillis2 = System.currentTimeMillis();
            List<DiagnosisData> b2 = this.vs.b(this.YA[1], currentTimeMillis2 - 1800000, currentTimeMillis2);
            LinkedList linkedList2 = new LinkedList();
            for (DiagnosisData diagnosisData2 : b2) {
                String str2 = diagnosisData2.getErrorCode() + "|" + diagnosisData2.getErrorMsg();
                if (!linkedList2.contains(str2)) {
                    linkedList2.add(str2);
                }
            }
            if (linkedList2.size() == 0) {
                linkedList2.add("");
            }
            com.xiaopeng.logictree.d.g((String[]) linkedList2.toArray(new String[linkedList2.size()]));
        } else if (a(this.YA, new String[]{"5"})) {
            long startTime3 = aVar.getStartTime();
            long pD3 = aVar.pD();
            if (pD3 - startTime3 < 115200000) {
                startTime3 = pD3 - 115200000;
            }
            List<Integer> a3 = this.vs.a(this.YA[1], startTime3, pD3);
            if (a3.size() == 0) {
                a3.add(0);
            }
            com.xiaopeng.logictree.d.d(a3.stream().mapToInt(new ToIntFunction() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$b$UV1wDVoVlbcxpr8zevj_aMFtUGw
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    int intValue;
                    intValue = ((Integer) obj).intValue();
                    return intValue;
                }
            }).toArray());
        } else if (a(this.YA, new String[]{"6"})) {
            long startTime4 = aVar.getStartTime();
            long pD4 = aVar.pD();
            if (pD4 - startTime4 < 115200000) {
                startTime4 = pD4 - 115200000;
            }
            List<DiagnosisData> b3 = this.vs.b(this.YA[1], startTime4, pD4);
            LinkedList linkedList3 = new LinkedList();
            for (DiagnosisData diagnosisData3 : b3) {
                String str3 = diagnosisData3.getErrorCode() + "|" + diagnosisData3.getErrorMsg();
                if (!linkedList3.contains(str3)) {
                    linkedList3.add(str3);
                }
            }
            if (linkedList3.size() == 0) {
                linkedList3.add("");
            }
            com.xiaopeng.logictree.d.g((String[]) linkedList3.toArray(new String[linkedList3.size()]));
        }
        return null;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
        this.vs.onDestroy();
    }
}
