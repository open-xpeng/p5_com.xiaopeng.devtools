package com.activeandroid.query;

import android.text.TextUtils;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Join;
import com.activeandroid.util.Log;
import com.activeandroid.util.SQLiteUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public final class From implements Sqlable {
    private String mAlias;
    private String mGroupBy;
    private String mHaving;
    private List<Join> mJoins;
    private String mLimit;
    private String mOffset;
    private String mOrderBy;
    private Sqlable mQueryBase;
    private Class<? extends Model> mType;
    private final StringBuilder mWhere = new StringBuilder();
    private List<Object> mArguments = new ArrayList();

    public From(Class<? extends Model> cls, Sqlable sqlable) {
        this.mType = cls;
        this.mJoins = new ArrayList();
        this.mQueryBase = sqlable;
        this.mJoins = new ArrayList();
    }

    public From as(String str) {
        this.mAlias = str;
        return this;
    }

    public Join join(Class<? extends Model> cls) {
        Join join = new Join(this, cls, null);
        this.mJoins.add(join);
        return join;
    }

    public Join leftJoin(Class<? extends Model> cls) {
        Join join = new Join(this, cls, Join.JoinType.LEFT);
        this.mJoins.add(join);
        return join;
    }

    public Join outerJoin(Class<? extends Model> cls) {
        Join join = new Join(this, cls, Join.JoinType.OUTER);
        this.mJoins.add(join);
        return join;
    }

    public Join innerJoin(Class<? extends Model> cls) {
        Join join = new Join(this, cls, Join.JoinType.INNER);
        this.mJoins.add(join);
        return join;
    }

    public Join crossJoin(Class<? extends Model> cls) {
        Join join = new Join(this, cls, Join.JoinType.CROSS);
        this.mJoins.add(join);
        return join;
    }

    public From where(String str) {
        if (this.mWhere.length() > 0) {
            this.mWhere.append(" AND ");
        }
        this.mWhere.append(str);
        return this;
    }

    public From where(String str, Object... objArr) {
        where(str).addArguments(objArr);
        return this;
    }

    public From and(String str) {
        return where(str);
    }

    public From and(String str, Object... objArr) {
        return where(str, objArr);
    }

    public From or(String str) {
        if (this.mWhere.length() > 0) {
            this.mWhere.append(" OR ");
        }
        this.mWhere.append(str);
        return this;
    }

    public From or(String str, Object... objArr) {
        or(str).addArguments(objArr);
        return this;
    }

    public From groupBy(String str) {
        this.mGroupBy = str;
        return this;
    }

    public From having(String str) {
        this.mHaving = str;
        return this;
    }

    public From orderBy(String str) {
        this.mOrderBy = str;
        return this;
    }

    public From limit(int i) {
        return limit(String.valueOf(i));
    }

    public From limit(String str) {
        this.mLimit = str;
        return this;
    }

    public From offset(int i) {
        return offset(String.valueOf(i));
    }

    public From offset(String str) {
        this.mOffset = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addArguments(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj.getClass() == Boolean.TYPE || obj.getClass() == Boolean.class) {
                obj = Integer.valueOf(obj.equals(true) ? 1 : 0);
            }
            this.mArguments.add(obj);
        }
    }

    private void addFrom(StringBuilder sb) {
        sb.append("FROM ");
        sb.append(Cache.getTableName(this.mType));
        sb.append(" ");
        if (this.mAlias != null) {
            sb.append("AS ");
            sb.append(this.mAlias);
            sb.append(" ");
        }
    }

    private void addJoins(StringBuilder sb) {
        for (Join join : this.mJoins) {
            sb.append(join.toSql());
        }
    }

    private void addWhere(StringBuilder sb) {
        if (this.mWhere.length() > 0) {
            sb.append("WHERE ");
            sb.append((CharSequence) this.mWhere);
            sb.append(" ");
        }
    }

    private void addGroupBy(StringBuilder sb) {
        if (this.mGroupBy != null) {
            sb.append("GROUP BY ");
            sb.append(this.mGroupBy);
            sb.append(" ");
        }
    }

    private void addHaving(StringBuilder sb) {
        if (this.mHaving != null) {
            sb.append("HAVING ");
            sb.append(this.mHaving);
            sb.append(" ");
        }
    }

    private void addOrderBy(StringBuilder sb) {
        if (this.mOrderBy != null) {
            sb.append("ORDER BY ");
            sb.append(this.mOrderBy);
            sb.append(" ");
        }
    }

    private void addLimit(StringBuilder sb) {
        if (this.mLimit != null) {
            sb.append("LIMIT ");
            sb.append(this.mLimit);
            sb.append(" ");
        }
    }

    private void addOffset(StringBuilder sb) {
        if (this.mOffset != null) {
            sb.append("OFFSET ");
            sb.append(this.mOffset);
            sb.append(" ");
        }
    }

    private String sqlString(StringBuilder sb) {
        String trim = sb.toString().trim();
        if (Log.isEnabled()) {
            Log.v(trim + " " + TextUtils.join(",", getArguments()));
        }
        return trim;
    }

    @Override // com.activeandroid.query.Sqlable
    public String toSql() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mQueryBase.toSql());
        addFrom(sb);
        addJoins(sb);
        addWhere(sb);
        addGroupBy(sb);
        addHaving(sb);
        addOrderBy(sb);
        addLimit(sb);
        addOffset(sb);
        return sqlString(sb);
    }

    public String toExistsSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT EXISTS(SELECT 1 ");
        addFrom(sb);
        addJoins(sb);
        addWhere(sb);
        addGroupBy(sb);
        addHaving(sb);
        addLimit(sb);
        addOffset(sb);
        sb.append(")");
        return sqlString(sb);
    }

    public String toCountSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) ");
        addFrom(sb);
        addJoins(sb);
        addWhere(sb);
        addGroupBy(sb);
        addHaving(sb);
        addLimit(sb);
        addOffset(sb);
        return sqlString(sb);
    }

    public <T extends Model> List<T> execute() {
        if (this.mQueryBase instanceof Select) {
            return SQLiteUtils.rawQuery(this.mType, toSql(), getArguments());
        }
        SQLiteUtils.execSql(toSql(), getArguments());
        Cache.getContext().getContentResolver().notifyChange(ContentProvider.createUri(this.mType, null), null);
        return null;
    }

    public <T extends Model> T executeSingle() {
        if (this.mQueryBase instanceof Select) {
            limit(1);
            return (T) SQLiteUtils.rawQuerySingle(this.mType, toSql(), getArguments());
        }
        limit(1);
        SQLiteUtils.rawQuerySingle(this.mType, toSql(), getArguments()).delete();
        return null;
    }

    public boolean exists() {
        return SQLiteUtils.intQuery(toExistsSql(), getArguments()) != 0;
    }

    public int count() {
        return SQLiteUtils.intQuery(toCountSql(), getArguments());
    }

    public String[] getArguments() {
        int size = this.mArguments.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = this.mArguments.get(i).toString();
        }
        return strArr;
    }
}
