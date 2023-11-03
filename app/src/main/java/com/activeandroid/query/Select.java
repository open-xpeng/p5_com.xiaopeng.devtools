package com.activeandroid.query;

import android.text.TextUtils;
import com.activeandroid.Model;

/* loaded from: classes11.dex */
public final class Select implements Sqlable {
    private String[] mColumns;
    private boolean mDistinct = false;
    private boolean mAll = false;

    public Select() {
    }

    public Select(String... strArr) {
        this.mColumns = strArr;
    }

    public Select(Column... columnArr) {
        int length = columnArr.length;
        this.mColumns = new String[length];
        for (int i = 0; i < length; i++) {
            String[] strArr = this.mColumns;
            strArr[i] = columnArr[i].name + " AS " + columnArr[i].alias;
        }
    }

    public Select distinct() {
        this.mDistinct = true;
        this.mAll = false;
        return this;
    }

    public Select all() {
        this.mDistinct = false;
        this.mAll = true;
        return this;
    }

    public From from(Class<? extends Model> cls) {
        return new From(cls, this);
    }

    /* loaded from: classes11.dex */
    public static class Column {
        String alias;
        String name;

        public Column(String str, String str2) {
            this.name = str;
            this.alias = str2;
        }
    }

    @Override // com.activeandroid.query.Sqlable
    public String toSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        if (this.mDistinct) {
            sb.append("DISTINCT ");
        } else if (this.mAll) {
            sb.append("ALL ");
        }
        if (this.mColumns != null && this.mColumns.length > 0) {
            sb.append(TextUtils.join(", ", this.mColumns) + " ");
        } else {
            sb.append("* ");
        }
        return sb.toString();
    }
}
