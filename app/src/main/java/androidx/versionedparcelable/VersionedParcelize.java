package androidx.versionedparcelable;

import androidx.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes11.dex */
public @interface VersionedParcelize {
    boolean allowSerialization() default false;

    int[] deprecatedIds() default {};

    boolean ignoreParcelables() default false;

    boolean isCustom() default false;

    String jetifyAs() default "";
}
