package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes8.dex */
public @interface RestrictTo {

    /* loaded from: classes8.dex */
    public enum Scope {
        LIBRARY,
        LIBRARY_GROUP,
        GROUP_ID,
        TESTS,
        SUBCLASSES
    }

    Scope[] value();
}
