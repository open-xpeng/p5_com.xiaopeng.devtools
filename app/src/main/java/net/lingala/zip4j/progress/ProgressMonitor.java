package net.lingala.zip4j.progress;

/* loaded from: classes13.dex */
public class ProgressMonitor {
    private State apg;
    private long aph;
    private long api;
    private int apj;
    private Task apk;
    private Result apl;
    private boolean apm;
    private boolean apn;
    private Exception exception;
    private String fileName;

    /* loaded from: classes13.dex */
    public enum Result {
        SUCCESS,
        WORK_IN_PROGRESS,
        ERROR,
        CANCELLED
    }

    /* loaded from: classes13.dex */
    public enum State {
        READY,
        BUSY
    }

    /* loaded from: classes13.dex */
    public enum Task {
        NONE,
        ADD_ENTRY,
        REMOVE_ENTRY,
        CALCULATE_CRC,
        EXTRACT_ENTRY,
        MERGE_ZIP_FILES,
        SET_COMMENT,
        RENAME_FILE
    }

    public ProgressMonitor() {
        reset();
    }

    public void J(long j) {
        this.api += j;
        if (this.aph > 0) {
            this.apj = (int) ((this.api * 100) / this.aph);
            if (this.apj > 100) {
                this.apj = 100;
            }
        }
        while (this.apn) {
            try {
                Thread.sleep(150L);
            } catch (InterruptedException e) {
            }
        }
    }

    public void ua() {
        this.apl = Result.SUCCESS;
        this.apj = 100;
        reset();
    }

    public void e(Exception exc) {
        this.apl = Result.ERROR;
        this.exception = exc;
        reset();
    }

    public void ub() {
        reset();
        this.fileName = null;
        this.aph = 0L;
        this.api = 0L;
        this.apj = 0;
    }

    private void reset() {
        this.apk = Task.NONE;
        this.apg = State.READY;
    }

    public State uc() {
        return this.apg;
    }

    public void a(State state) {
        this.apg = state;
    }

    public void K(long j) {
        this.aph = j;
    }

    public void a(Task task) {
        this.apk = task;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void a(Result result) {
        this.apl = result;
    }

    public boolean ud() {
        return this.apm;
    }
}
