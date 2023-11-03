package com.xiaopeng.commonfunc.bean.aftersales;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class DiagnosisErrorList {
    private static final DiagnosisName UNKNOWN_DIAGNOSIS_NAME = new DiagnosisName("未知", "UNKNOWN");
    private List<DiagnosisData> mDiagnosisDataList;
    private final Map<Integer, DiagnosisName> mErrorCodeList;

    public DiagnosisErrorList(Map<Integer, DiagnosisName> map) {
        this.mErrorCodeList = map;
    }

    private void newDiagnosisDataList() {
        this.mDiagnosisDataList = new ArrayList();
    }

    public void addErrorCode(int i, DiagnosisName diagnosisName) {
        this.mErrorCodeList.put(Integer.valueOf(i), diagnosisName);
    }

    public DiagnosisName getDiagnosisName(int i) {
        DiagnosisName diagnosisName = this.mErrorCodeList.get(Integer.valueOf(i));
        if (diagnosisName == null) {
            return UNKNOWN_DIAGNOSIS_NAME;
        }
        return diagnosisName;
    }

    public void addDiagnosisData(DiagnosisData diagnosisData) {
        if (this.mDiagnosisDataList == null) {
            newDiagnosisDataList();
        }
        this.mDiagnosisDataList.add(diagnosisData);
    }

    public void clearDiagnosisData() {
        if (this.mDiagnosisDataList == null) {
            newDiagnosisDataList();
        }
        this.mDiagnosisDataList.clear();
    }

    public DiagnosisData getDiagnosisData(int i) {
        if (this.mDiagnosisDataList == null) {
            newDiagnosisDataList();
        }
        return this.mDiagnosisDataList.get(i);
    }

    public int getDiagnosisDataSize() {
        if (this.mDiagnosisDataList == null) {
            newDiagnosisDataList();
        }
        return this.mDiagnosisDataList.size();
    }
}
