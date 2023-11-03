package com.activeandroid.query;

import com.activeandroid.Cache;
import com.activeandroid.Model;

/* loaded from: classes11.dex */
public final class Update implements Sqlable {
    private Class<? extends Model> mType;

    public Update(Class<? extends Model> cls) {
        this.mType = cls;
    }

    public Set set(String str) {
        return new Set(this, str);
    }

    public Set set(String str, Object... objArr) {
        return new Set(this, str, objArr);
    }

    Class<? extends Model> getType() {
        return this.mType;
    }

    @Override // com.activeandroid.query.Sqlable
    public String toSql() {
        return "UPDATE " + Cache.getTableName(this.mType) + " ";
    }
}
