package com.xiaopeng.devtools.bean.http;

/* loaded from: classes12.dex */
public class UuidBean {
    private int code;
    private UuidData data;

    public UuidData getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }

    /* loaded from: classes12.dex */
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

    public String toString() {
        return "UuidBean{code=" + this.code + ", data=" + this.data + '}';
    }
}
