package com.activeandroid.util;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes11.dex */
public class Tokenizer {
    private int mCurrent;
    private boolean mIsNext;
    private final InputStream mStream;

    public Tokenizer(InputStream inputStream) {
        this.mStream = inputStream;
    }

    public boolean hasNext() throws IOException {
        if (!this.mIsNext) {
            this.mIsNext = true;
            this.mCurrent = this.mStream.read();
        }
        return this.mCurrent != -1;
    }

    public int next() throws IOException {
        if (!this.mIsNext) {
            this.mCurrent = this.mStream.read();
        }
        this.mIsNext = false;
        return this.mCurrent;
    }

    public boolean skip(String str) throws IOException {
        if (str == null || str.length() == 0 || str.charAt(0) != this.mCurrent) {
            return false;
        }
        int length = str.length();
        this.mStream.mark(length - 1);
        for (int i = 1; i < length; i++) {
            if (this.mStream.read() != str.charAt(i)) {
                this.mStream.reset();
                return false;
            }
        }
        return true;
    }
}
