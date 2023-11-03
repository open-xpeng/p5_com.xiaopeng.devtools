package com.xiaopeng.lib.apirouter.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes12.dex */
public @interface Parameter {
    String value();
}
