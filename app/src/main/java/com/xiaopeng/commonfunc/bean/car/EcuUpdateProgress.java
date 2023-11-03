package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class EcuUpdateProgress {
    private String Partition;
    private String Process;
    private String Target;

    public EcuUpdateProgress(String str, String str2, String str3) {
        this.Target = str;
        this.Process = str2;
        this.Partition = str3;
    }

    public String getTarget() {
        return this.Target;
    }

    public void setTarget(String str) {
        this.Target = str;
    }

    public String getProcess() {
        return this.Process;
    }

    public void setProcess(String str) {
        this.Process = str;
    }

    public String getPartition() {
        return this.Partition;
    }

    public void setPartition(String str) {
        this.Partition = str;
    }

    public String toString() {
        return "EcuUpdateProcess{Target='" + this.Target + "', Process='" + this.Process + "', Partition='" + this.Partition + "'}";
    }
}
