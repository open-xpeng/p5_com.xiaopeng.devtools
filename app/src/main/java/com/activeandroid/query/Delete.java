package com.activeandroid.query;

import com.activeandroid.Model;

/* loaded from: classes11.dex */
public final class Delete implements Sqlable {
    public From from(Class<? extends Model> cls) {
        return new From(cls, this);
    }

    @Override // com.activeandroid.query.Sqlable
    public String toSql() {
        return "DELETE ";
    }
}
