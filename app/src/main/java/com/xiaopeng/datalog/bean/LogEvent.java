package com.xiaopeng.datalog.bean;

import android.support.annotation.Keep;
import com.xiaopeng.lib.utils.c.c;
import com.xiaopeng.lib.utils.i;
import java.util.List;

@Keep
/* loaded from: classes11.dex */
public class LogEvent {
    private String dbcVer;
    private String device;
    private String iccid;
    private String model;
    private List<Object> msg;
    private int packageId;
    private int ref;
    private String sid;
    private String sysVer;
    private long t;
    private long uid;
    private int v = 3;
    private int vid;
    private String vin;

    private LogEvent() {
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public String getVin() {
        return this.vin;
    }

    public void setVin(String str) {
        this.vin = str;
    }

    public int getVid() {
        return this.vid;
    }

    public void setVid(int i) {
        this.vid = i;
    }

    public String getSysVer() {
        return this.sysVer;
    }

    public void setSysVer(String str) {
        this.sysVer = str;
    }

    public String getSid() {
        return this.sid;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public long getT() {
        return this.t;
    }

    public void setT(long j) {
        this.t = j;
    }

    public int getRef() {
        return this.ref;
    }

    public void setRef(int i) {
        this.ref = i;
    }

    public void setV(int i) {
        this.v = i;
    }

    public int getV() {
        return this.v;
    }

    public int getPackageId() {
        return this.packageId;
    }

    public void setPackageId(int i) {
        this.packageId = i;
    }

    public List<Object> getMsg() {
        return this.msg;
    }

    public void setMsg(List<Object> list) {
        this.msg = list;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getDbcVer() {
        return this.dbcVer;
    }

    public void setDbcVer(String str) {
        this.dbcVer = str;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public static LogEvent create(RefType refType) {
        LogEvent logEvent = new LogEvent();
        logEvent.setDevice(i.getHardwareId());
        logEvent.setVin(i.eH());
        logEvent.setSysVer(getSystemVersion());
        logEvent.setSid(i.oS());
        logEvent.setUid(i.oR());
        logEvent.setT(System.currentTimeMillis());
        logEvent.setRef(refType.eS());
        logEvent.setModel(c.pp());
        logEvent.setDbcVer(i.oT());
        logEvent.setIccid(i.getIccid());
        return logEvent;
    }

    /* loaded from: classes11.dex */
    public enum RefType {
        CAN(2),
        CDU(1);
        
        private int refType;

        RefType(int i) {
            this.refType = i;
        }

        public int eS() {
            return this.refType;
        }
    }

    /* loaded from: classes11.dex */
    public enum SrcType {
        XMART_ROM(1),
        XMART_APP(2),
        XMART_THIRD_APP(3),
        ANDROID_APP(4);
        
        private int srcType;

        SrcType(int i) {
            this.srcType = i;
        }
    }

    public static SrcType from(int i) {
        switch (i) {
            case 1:
                return SrcType.XMART_ROM;
            case 2:
                return SrcType.XMART_APP;
            case 3:
                return SrcType.XMART_THIRD_APP;
            case 4:
                return SrcType.ANDROID_APP;
            default:
                throw new IllegalArgumentException("invalid type " + i + ", must be [1, 4]");
        }
    }

    public static String getSystemVersion() {
        String[] split = i.oS().split("_");
        if (split.length > 1) {
            return split[1];
        }
        return "unknown";
    }
}
