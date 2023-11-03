package com.lzy.okgo.model;

import android.os.SystemClock;
import com.lzy.okgo.request.base.Request;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class Progress implements Serializable {
    private static final long serialVersionUID = 6353658567594109891L;
    public long currentSize;
    public Throwable exception;
    public Serializable extra1;
    public Serializable extra2;
    public Serializable extra3;
    public String fileName;
    public String filePath;
    public String folder;
    public float fraction;
    public transient long mr;
    private transient long ms;
    public Request<?, ? extends Request> request;
    public int status;
    public String tag;
    public String url;
    private transient long mt = SystemClock.elapsedRealtime();
    public long totalSize = -1;
    public int priority = 0;
    public long date = System.currentTimeMillis();
    private transient List<Long> mu = new ArrayList();

    /* loaded from: classes11.dex */
    public interface a {
        void a(Progress progress);
    }

    public static Progress a(Progress progress, long j, a aVar) {
        return a(progress, j, progress.totalSize, aVar);
    }

    public static Progress a(Progress progress, long j, long j2, a aVar) {
        progress.totalSize = j2;
        progress.currentSize += j;
        progress.ms += j;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if ((elapsedRealtime - progress.mt >= com.lzy.okgo.a.lj) || progress.currentSize == j2) {
            long j3 = elapsedRealtime - progress.mt;
            if (j3 == 0) {
                j3 = 1;
            }
            progress.fraction = (((float) progress.currentSize) * 1.0f) / ((float) j2);
            progress.mr = progress.g((progress.ms * 1000) / j3);
            progress.mt = elapsedRealtime;
            progress.ms = 0L;
            if (aVar != null) {
                aVar.a(progress);
            }
        }
        return progress;
    }

    private long g(long j) {
        this.mu.add(Long.valueOf(j));
        if (this.mu.size() > 10) {
            this.mu.remove(0);
        }
        long j2 = 0;
        for (Long l : this.mu) {
            j2 = ((float) j2) + ((float) l.longValue());
        }
        return j2 / this.mu.size();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Progress progress = (Progress) obj;
        if (this.tag != null) {
            return this.tag.equals(progress.tag);
        }
        if (progress.tag == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.tag != null) {
            return this.tag.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Progress{fraction=" + this.fraction + ", totalSize=" + this.totalSize + ", currentSize=" + this.currentSize + ", speed=" + this.mr + ", status=" + this.status + ", priority=" + this.priority + ", folder=" + this.folder + ", filePath=" + this.filePath + ", fileName=" + this.fileName + ", tag=" + this.tag + ", url=" + this.url + '}';
    }
}
