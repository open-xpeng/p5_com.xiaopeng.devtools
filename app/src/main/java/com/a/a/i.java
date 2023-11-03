package com.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.a.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: ValueAnimator.java */
/* loaded from: classes11.dex */
public class i extends com.a.a.a {
    long mStartTime;
    HashMap<String, g> mValuesMap;
    private long nt;
    g[] nw;
    private static ThreadLocal<a> nj = new ThreadLocal<>();
    private static final ThreadLocal<ArrayList<i>> nk = new ThreadLocal<ArrayList<i>>() { // from class: com.a.a.i.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: dD */
        public ArrayList<i> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<i>> nl = new ThreadLocal<ArrayList<i>>() { // from class: com.a.a.i.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: dD */
        public ArrayList<i> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<i>> nm = new ThreadLocal<ArrayList<i>>() { // from class: com.a.a.i.3
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: dD */
        public ArrayList<i> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<i>> nn = new ThreadLocal<ArrayList<i>>() { // from class: com.a.a.i.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: dD */
        public ArrayList<i> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final ThreadLocal<ArrayList<i>> no = new ThreadLocal<ArrayList<i>>() { // from class: com.a.a.i.5
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: dD */
        public ArrayList<i> initialValue() {
            return new ArrayList<>();
        }
    };
    private static final Interpolator np = new AccelerateDecelerateInterpolator();
    private static final h mW = new d();
    private static final h mZ = new com.a.a.b();
    private static long nv = 10;
    long ni = -1;
    private boolean nq = false;
    private int nr = 0;
    private float mCurrentFraction = 0.0f;
    private boolean ns = false;
    int nu = 0;
    private boolean mRunning = false;
    private boolean mStarted = false;
    boolean mInitialized = false;
    private long mDuration = 300;
    private long mStartDelay = 0;
    private int mRepeatCount = 0;
    private int mRepeatMode = 1;
    private Interpolator mInterpolator = np;
    private ArrayList<b> mUpdateListeners = null;

    /* compiled from: ValueAnimator.java */
    /* loaded from: classes11.dex */
    public interface b {
        void d(i iVar);
    }

    public static i b(float... fArr) {
        i iVar = new i();
        iVar.setFloatValues(fArr);
        return iVar;
    }

    public void setFloatValues(float... fArr) {
        if (fArr == null || fArr.length == 0) {
            return;
        }
        if (this.nw == null || this.nw.length == 0) {
            a(g.a("", fArr));
        } else {
            this.nw[0].setFloatValues(fArr);
        }
        this.mInitialized = false;
    }

    public void a(g... gVarArr) {
        int length = gVarArr.length;
        this.nw = gVarArr;
        this.mValuesMap = new HashMap<>(length);
        for (g gVar : gVarArr) {
            this.mValuesMap.put(gVar.getPropertyName(), gVar);
        }
        this.mInitialized = false;
    }

    void initAnimation() {
        if (!this.mInitialized) {
            int length = this.nw.length;
            for (int i = 0; i < length; i++) {
                this.nw[i].init();
            }
            this.mInitialized = true;
        }
    }

    public i h(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDuration = j;
        return this;
    }

    public void setCurrentPlayTime(long j) {
        initAnimation();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.nu != 1) {
            this.ni = j;
            this.nu = 2;
        }
        this.mStartTime = currentAnimationTimeMillis - j;
        j(currentAnimationTimeMillis);
    }

    public long getCurrentPlayTime() {
        if (!this.mInitialized || this.nu == 0) {
            return 0L;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.mStartTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ValueAnimator.java */
    /* loaded from: classes11.dex */
    public static class a extends Handler {
        private a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            ArrayList arrayList = (ArrayList) i.nk.get();
            ArrayList arrayList2 = (ArrayList) i.nm.get();
            switch (message.what) {
                case 0:
                    ArrayList arrayList3 = (ArrayList) i.nl.get();
                    z = arrayList.size() <= 0 && arrayList2.size() <= 0;
                    while (arrayList3.size() > 0) {
                        ArrayList arrayList4 = (ArrayList) arrayList3.clone();
                        arrayList3.clear();
                        int size = arrayList4.size();
                        for (int i = 0; i < size; i++) {
                            i iVar = (i) arrayList4.get(i);
                            if (iVar.mStartDelay == 0) {
                                iVar.startAnimation();
                            } else {
                                arrayList2.add(iVar);
                            }
                        }
                    }
                    break;
                case 1:
                    z = true;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            ArrayList arrayList5 = (ArrayList) i.no.get();
            ArrayList arrayList6 = (ArrayList) i.nn.get();
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                i iVar2 = (i) arrayList2.get(i2);
                if (iVar2.i(currentAnimationTimeMillis)) {
                    arrayList5.add(iVar2);
                }
            }
            int size3 = arrayList5.size();
            if (size3 > 0) {
                for (int i3 = 0; i3 < size3; i3++) {
                    i iVar3 = (i) arrayList5.get(i3);
                    iVar3.startAnimation();
                    iVar3.mRunning = true;
                    arrayList2.remove(iVar3);
                }
                arrayList5.clear();
            }
            int size4 = arrayList.size();
            int i4 = 0;
            while (i4 < size4) {
                i iVar4 = (i) arrayList.get(i4);
                if (iVar4.j(currentAnimationTimeMillis)) {
                    arrayList6.add(iVar4);
                }
                if (arrayList.size() == size4) {
                    i4++;
                } else {
                    size4--;
                    arrayList6.remove(iVar4);
                }
            }
            if (arrayList6.size() > 0) {
                for (int i5 = 0; i5 < arrayList6.size(); i5++) {
                    ((i) arrayList6.get(i5)).dv();
                }
                arrayList6.clear();
            }
            if (z) {
                if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
                    sendEmptyMessageDelayed(1, Math.max(0L, i.nv - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
                }
            }
        }
    }

    public Object getAnimatedValue() {
        if (this.nw != null && this.nw.length > 0) {
            return this.nw[0].getAnimatedValue();
        }
        return null;
    }

    public void a(b bVar) {
        if (this.mUpdateListeners == null) {
            this.mUpdateListeners = new ArrayList<>();
        }
        this.mUpdateListeners.add(bVar);
    }

    public void setInterpolator(Interpolator interpolator) {
        if (interpolator != null) {
            this.mInterpolator = interpolator;
        } else {
            this.mInterpolator = new LinearInterpolator();
        }
    }

    private void start(boolean z) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.nq = z;
        this.nr = 0;
        this.nu = 0;
        this.mStarted = true;
        this.ns = false;
        nl.get().add(this);
        if (this.mStartDelay == 0) {
            setCurrentPlayTime(getCurrentPlayTime());
            this.nu = 0;
            this.mRunning = true;
            if (this.mListeners != null) {
                ArrayList arrayList = (ArrayList) this.mListeners.clone();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((a.InterfaceC0011a) arrayList.get(i)).a(this);
                }
            }
        }
        a aVar = nj.get();
        if (aVar == null) {
            aVar = new a();
            nj.set(aVar);
        }
        aVar.sendEmptyMessage(0);
    }

    @Override // com.a.a.a
    public void start() {
        start(false);
    }

    @Override // com.a.a.a
    public void cancel() {
        if (this.nu != 0 || nl.get().contains(this) || nm.get().contains(this)) {
            if (this.mRunning && this.mListeners != null) {
                Iterator it = ((ArrayList) this.mListeners.clone()).iterator();
                while (it.hasNext()) {
                    ((a.InterfaceC0011a) it.next()).c(this);
                }
            }
            dv();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dv() {
        nk.get().remove(this);
        nl.get().remove(this);
        nm.get().remove(this);
        this.nu = 0;
        if (this.mRunning && this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((a.InterfaceC0011a) arrayList.get(i)).b(this);
            }
        }
        this.mRunning = false;
        this.mStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimation() {
        initAnimation();
        nk.get().add(this);
        if (this.mStartDelay > 0 && this.mListeners != null) {
            ArrayList arrayList = (ArrayList) this.mListeners.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((a.InterfaceC0011a) arrayList.get(i)).a(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(long j) {
        if (!this.ns) {
            this.ns = true;
            this.nt = j;
            return false;
        }
        long j2 = j - this.nt;
        if (j2 > this.mStartDelay) {
            this.mStartTime = j - (j2 - this.mStartDelay);
            this.nu = 1;
            return true;
        }
        return false;
    }

    boolean j(long j) {
        if (this.nu == 0) {
            this.nu = 1;
            if (this.ni < 0) {
                this.mStartTime = j;
            } else {
                this.mStartTime = j - this.ni;
                this.ni = -1L;
            }
        }
        boolean z = false;
        switch (this.nu) {
            case 1:
            case 2:
                float f = this.mDuration > 0 ? ((float) (j - this.mStartTime)) / ((float) this.mDuration) : 1.0f;
                if (f >= 1.0f) {
                    if (this.nr < this.mRepeatCount || this.mRepeatCount == -1) {
                        if (this.mListeners != null) {
                            int size = this.mListeners.size();
                            for (int i = 0; i < size; i++) {
                                this.mListeners.get(i).d(this);
                            }
                        }
                        if (this.mRepeatMode == 2) {
                            this.nq = !this.nq;
                        }
                        this.nr += (int) f;
                        f %= 1.0f;
                        this.mStartTime += this.mDuration;
                    } else {
                        f = Math.min(f, 1.0f);
                        z = true;
                    }
                }
                if (this.nq) {
                    f = 1.0f - f;
                }
                animateValue(f);
                break;
        }
        return z;
    }

    void animateValue(float f) {
        float interpolation = this.mInterpolator.getInterpolation(f);
        this.mCurrentFraction = interpolation;
        int length = this.nw.length;
        for (int i = 0; i < length; i++) {
            this.nw[i].calculateValue(interpolation);
        }
        if (this.mUpdateListeners != null) {
            int size = this.mUpdateListeners.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mUpdateListeners.get(i2).d(this);
            }
        }
    }

    @Override // com.a.a.a
    /* renamed from: dw */
    public i mo10do() {
        i iVar = (i) super.clone();
        if (this.mUpdateListeners != null) {
            ArrayList<b> arrayList = this.mUpdateListeners;
            iVar.mUpdateListeners = new ArrayList<>();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                iVar.mUpdateListeners.add(arrayList.get(i));
            }
        }
        iVar.ni = -1L;
        iVar.nq = false;
        iVar.nr = 0;
        iVar.mInitialized = false;
        iVar.nu = 0;
        iVar.ns = false;
        g[] gVarArr = this.nw;
        if (gVarArr != null) {
            int length = gVarArr.length;
            iVar.nw = new g[length];
            iVar.mValuesMap = new HashMap<>(length);
            for (int i2 = 0; i2 < length; i2++) {
                g clone = gVarArr[i2].clone();
                iVar.nw[i2] = clone;
                iVar.mValuesMap.put(clone.getPropertyName(), clone);
            }
        }
        return iVar;
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.nw != null) {
            for (int i = 0; i < this.nw.length; i++) {
                str = str + "\n    " + this.nw[i].toString();
            }
        }
        return str;
    }
}
