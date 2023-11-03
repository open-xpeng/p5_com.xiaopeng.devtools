package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class EcuUpdateReq {
    private String ImgLocation;
    private String Target;

    public EcuUpdateReq(String str, String str2) {
        this.Target = str;
        this.ImgLocation = str2;
    }

    public String getTarget() {
        return this.Target;
    }

    public void setTarget(String str) {
        this.Target = str;
    }

    public String getImgLocation() {
        return this.ImgLocation;
    }

    public void setImgLocation(String str) {
        this.ImgLocation = str;
    }

    public String toString() {
        return "EcuUpdateReq{Target='" + this.Target + "', ImgLocation='" + this.ImgLocation + "'}";
    }
}
