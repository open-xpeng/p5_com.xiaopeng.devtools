package com.xiaopeng.xui.view.c;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: XTouchDelegateGroup.java */
/* loaded from: classes13.dex */
public class b extends TouchDelegate {
    private static final Rect abx = new Rect();
    private List<a> aby;
    @Nullable
    private TouchDelegate abz;

    public b(View view) {
        super(abx, view);
        this.aby = new ArrayList();
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        Iterator<a> it = this.aby.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            a next = it.next();
            if (next.qQ().equals(aVar.qQ())) {
                this.aby.remove(next);
                break;
            }
        }
        this.aby.add(aVar);
    }

    public void a(TouchDelegate touchDelegate) {
        this.aby.remove(touchDelegate);
        if (touchDelegate == this.abz) {
            this.abz = null;
        }
    }

    @VisibleForTesting
    public List<a> getTouchDelegates() {
        return Collections.unmodifiableList(new ArrayList(this.aby));
    }

    @Override // android.view.TouchDelegate
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        boolean z = false;
        if (actionMasked == 0) {
            if (motionEvent.getPointerCount() > 1) {
                return false;
            }
            for (int size = this.aby.size() - 1; size >= 0; size--) {
                a aVar = this.aby.get(size);
                View qQ = aVar.qQ();
                if (qQ == null || qQ.getVisibility() == 0) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY();
                    boolean onTouchEvent = aVar.onTouchEvent(motionEvent);
                    motionEvent.setLocation(x, y);
                    if (onTouchEvent) {
                        this.abz = aVar;
                        return true;
                    }
                }
            }
            return false;
        }
        if (this.abz != null && this.abz.onTouchEvent(motionEvent)) {
            z = true;
        }
        if (actionMasked == 1 || actionMasked == 32) {
            this.abz = null;
        }
        return z;
    }
}
