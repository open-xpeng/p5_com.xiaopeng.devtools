package com.activeandroid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes11.dex */
public @interface Table {
    public static final String DEFAULT_ID_NAME = "Id";

    String id() default "Id";

    String name();
}
