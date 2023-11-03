package com.alibaba.sdk.android.oss.model;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class DeleteMultipleObjectResult extends OSSResult {
    private List<String> deletedObjects;
    private List<String> failedObjects;
    private boolean isQuiet;

    public void clear() {
        if (this.deletedObjects != null) {
            this.deletedObjects.clear();
        }
        if (this.failedObjects != null) {
            this.failedObjects.clear();
        }
    }

    public void addDeletedObject(String str) {
        if (this.deletedObjects == null) {
            this.deletedObjects = new ArrayList();
        }
        this.deletedObjects.add(str);
    }

    public void addFailedObjects(String str) {
        if (this.failedObjects == null) {
            this.failedObjects = new ArrayList();
        }
        this.failedObjects.add(str);
    }

    public List<String> getDeletedObjects() {
        return this.deletedObjects;
    }

    public List<String> getFailedObjects() {
        return this.failedObjects;
    }

    public boolean getQuiet() {
        return this.isQuiet;
    }

    public void setQuiet(boolean z) {
        this.isQuiet = z;
    }
}
