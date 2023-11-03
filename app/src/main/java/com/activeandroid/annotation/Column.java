package com.activeandroid.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes11.dex */
public @interface Column {

    /* loaded from: classes11.dex */
    public enum ConflictAction {
        ROLLBACK,
        ABORT,
        FAIL,
        IGNORE,
        REPLACE
    }

    /* loaded from: classes11.dex */
    public enum ForeignKeyAction {
        SET_NULL,
        SET_DEFAULT,
        CASCADE,
        RESTRICT,
        NO_ACTION
    }

    boolean index() default false;

    String[] indexGroups() default {};

    int length() default -1;

    String name() default "";

    boolean notNull() default false;

    ForeignKeyAction onDelete() default ForeignKeyAction.NO_ACTION;

    ConflictAction onNullConflict() default ConflictAction.FAIL;

    ConflictAction onUniqueConflict() default ConflictAction.FAIL;

    ConflictAction[] onUniqueConflicts() default {};

    ForeignKeyAction onUpdate() default ForeignKeyAction.NO_ACTION;

    boolean unique() default false;

    String[] uniqueGroups() default {};
}
