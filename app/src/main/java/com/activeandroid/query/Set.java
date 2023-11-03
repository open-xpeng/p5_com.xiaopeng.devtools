package com.activeandroid.query;

import com.activeandroid.util.SQLiteUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public final class Set implements Sqlable {
    private String mSet;
    private Update mUpdate;
    private String mWhere;
    private List<Object> mSetArguments = new ArrayList();
    private List<Object> mWhereArguments = new ArrayList();

    public Set(Update update, String str) {
        this.mUpdate = update;
        this.mSet = str;
    }

    public Set(Update update, String str, Object... objArr) {
        this.mUpdate = update;
        this.mSet = str;
        this.mSetArguments.addAll(Arrays.asList(objArr));
    }

    public Set where(String str) {
        this.mWhere = str;
        this.mWhereArguments.clear();
        return this;
    }

    public Set where(String str, Object... objArr) {
        this.mWhere = str;
        this.mWhereArguments.clear();
        this.mWhereArguments.addAll(Arrays.asList(objArr));
        return this;
    }

    @Override // com.activeandroid.query.Sqlable
    public String toSql() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mUpdate.toSql());
        sb.append("SET ");
        sb.append(this.mSet);
        sb.append(" ");
        if (this.mWhere != null) {
            sb.append("WHERE ");
            sb.append(this.mWhere);
            sb.append(" ");
        }
        return sb.toString();
    }

    public void execute() {
        SQLiteUtils.execSql(toSql(), getArguments());
    }

    public String[] getArguments() {
        int size = this.mSetArguments.size();
        int size2 = this.mWhereArguments.size();
        String[] strArr = new String[size + size2];
        for (int i = 0; i < size; i++) {
            strArr[i] = this.mSetArguments.get(i).toString();
        }
        for (int i2 = 0; i2 < size2; i2++) {
            strArr[i2 + size] = this.mWhereArguments.get(i2).toString();
        }
        return strArr;
    }
}
