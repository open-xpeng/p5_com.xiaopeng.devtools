package com.alibaba.sdk.android.oss.model;

/* loaded from: classes11.dex */
public class RestoreObjectRequest extends OSSRequest {
    private String bucketName;
    private String objectKey;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public String getObjectKey() {
        return this.objectKey;
    }

    public void setObjectKey(String str) {
        this.objectKey = str;
    }
}