package com.xiaopeng.commonfunc.bean.factorytest;

import java.util.List;

/* loaded from: classes11.dex */
public class ScreenDiagnosisResult {
    private String mCurrentResult;
    private List<ErrorItem> mErrorList;
    private long mTestCounts;

    public ScreenDiagnosisResult(long j, String str, List<ErrorItem> list) {
        this.mTestCounts = j;
        this.mCurrentResult = str;
        this.mErrorList = list;
    }

    public ScreenDiagnosisResult(long j) {
        this.mTestCounts = j;
        this.mCurrentResult = "";
        this.mErrorList = null;
    }

    public long getTestCounts() {
        return this.mTestCounts;
    }

    public String getCurrentResult() {
        return this.mCurrentResult;
    }

    public synchronized void setCurrentResult(String str) {
        this.mCurrentResult = str;
    }

    public List<ErrorItem> getErrorList() {
        return this.mErrorList;
    }

    public synchronized void setErrorList(List<ErrorItem> list) {
        this.mErrorList = list;
    }

    public synchronized void increaseTestCount() {
        this.mTestCounts++;
    }

    public synchronized void updateErrorResult(int i, String str) {
        this.mErrorList.get(i).setLastHappen(str);
        this.mErrorList.get(i).increaseErrorTimes();
    }

    /* loaded from: classes11.dex */
    public class ErrorItem {
        private int error_times;
        private String last_happen;
        private String name;

        public ErrorItem(String str, int i, String str2) {
            this.name = str;
            this.error_times = i;
            this.last_happen = str2;
        }

        public String getName() {
            return this.name;
        }

        public synchronized void setName(String str) {
            this.name = str;
        }

        public int getErrorTimes() {
            return this.error_times;
        }

        public synchronized void increaseErrorTimes() {
            this.error_times++;
        }

        public String getLastHappen() {
            return this.last_happen;
        }

        public synchronized void setLastHappen(String str) {
            this.last_happen = str;
        }
    }
}
