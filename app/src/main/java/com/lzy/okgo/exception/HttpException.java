package com.lzy.okgo.exception;

/* loaded from: classes11.dex */
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 8773734741709178425L;
    private int code;
    private String message;

    public HttpException(String str) {
        super(str);
    }

    public static HttpException cZ() {
        return new HttpException("network error! http response code is 404 or 5xx!");
    }

    public static HttpException aH(String str) {
        return new HttpException(str);
    }
}
