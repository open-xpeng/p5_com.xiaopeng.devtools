package com.xiaopeng.xui.app.a;

import android.app.Activity;
import android.view.WindowManager;
import androidx.annotation.NonNull;

/* compiled from: XActivityWindowAttributes.java */
/* loaded from: classes13.dex */
public class j {
    private int flags;
    private int gravity;
    private int height;
    private final Activity mActivity;
    private int systemUiVisibility;
    private int width;
    private int x;
    private int y;

    public j(@NonNull Activity activity) {
        this.mActivity = activity;
        init();
    }

    private void init() {
        WindowManager.LayoutParams attributes;
        if (this.mActivity != null && this.mActivity.getWindow() != null && (attributes = this.mActivity.getWindow().getAttributes()) != null) {
            this.x = attributes.x;
            this.y = attributes.y;
            this.flags = attributes.flags;
            this.width = attributes.width;
            this.height = attributes.height;
            this.gravity = attributes.gravity;
            this.systemUiVisibility = attributes.systemUiVisibility;
        }
    }

    public void apply() {
        WindowManager.LayoutParams attributes;
        if (this.mActivity != null && this.mActivity.getWindow() != null && (attributes = this.mActivity.getWindow().getAttributes()) != null) {
            attributes.x = this.x;
            attributes.y = this.y;
            attributes.flags |= this.flags;
            attributes.width = this.width;
            attributes.height = this.height;
            attributes.gravity = this.gravity;
            attributes.systemUiVisibility |= this.systemUiVisibility;
            this.mActivity.getWindow().setAttributes(attributes);
        }
    }

    public j en(int i) {
        this.x = i;
        return this;
    }

    public j eo(int i) {
        this.y = i;
        return this;
    }

    public j ep(int i) {
        this.width = i;
        return this;
    }

    public j eq(int i) {
        this.height = i;
        return this;
    }

    public j er(int i) {
        this.gravity = i;
        return this;
    }

    @NonNull
    public String toString() {
        return "{x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", flags=" + this.flags + ", gravity=" + this.gravity + ", systemUiVisibility=" + this.systemUiVisibility + ", mActivity=" + this.mActivity + '}';
    }
}
