package com.xiaopeng.commonfunc.bean.http;

/* loaded from: classes11.dex */
public class UuidBean {
    private int code;
    private UuidData data;

    public UuidData getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return "UuidBean{code=" + this.code + ", data=" + this.data + '}';
    }

    /* loaded from: classes11.dex */
    public class UuidData {
        private String uuid;

        public UuidData() {
        }

        public String getUuid() {
            return this.uuid;
        }

        public String toString() {
            return "UuidData{uuid='" + this.uuid + "'}";
        }
    }
}
