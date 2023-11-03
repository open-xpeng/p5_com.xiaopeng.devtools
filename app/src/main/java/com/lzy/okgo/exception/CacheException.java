package com.lzy.okgo.exception;

/* loaded from: classes11.dex */
public class CacheException extends Exception {
    private static final long serialVersionUID = 845628123701073013L;

    public static CacheException aG(String str) {
        return new CacheException("the http response code is 304, but the cache with cacheKey = " + str + " is null or expired!");
    }

    public CacheException(String str) {
        super(str);
    }
}
