package com.xiaopeng.xui.app.a;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/* compiled from: XActivityDelegate.java */
/* loaded from: classes13.dex */
public abstract class b {
    public abstract boolean dispatchTouchEvent(MotionEvent motionEvent);

    public abstract void installViewFactory();

    public abstract void onBackPressed();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onPostCreate(Bundle bundle);

    public abstract void onResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean onTouchEvent(MotionEvent motionEvent);

    public abstract void qA();

    public static b a(@NonNull AppCompatActivity appCompatActivity) {
        return new c(appCompatActivity);
    }
}
