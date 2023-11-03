package com.lzy.okgo.d;

import java.util.ArrayList;
import java.util.List;

/* compiled from: TableEntity.java */
/* loaded from: classes11.dex */
public class f {
    private List<c> list = new ArrayList();
    public String mb;

    public f(String str) {
        this.mb = str;
    }

    public f a(c cVar) {
        this.list.add(cVar);
        return this;
    }

    public String cY() {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(this.mb);
        sb.append('(');
        for (c cVar : this.list) {
            if (cVar.lT != null) {
                sb.append("PRIMARY KEY (");
                for (String str : cVar.lT) {
                    sb.append(str);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                sb.append(")");
            } else {
                sb.append(cVar.lR);
                sb.append(" ");
                sb.append(cVar.lS);
                if (cVar.lV) {
                    sb.append(" NOT NULL");
                }
                if (cVar.lU) {
                    sb.append(" PRIMARY KEY");
                }
                if (cVar.lW) {
                    sb.append(" AUTOINCREMENT");
                }
                sb.append(",");
            }
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(')');
        return sb.toString();
    }

    public int getColumnCount() {
        return this.list.size();
    }

    public int getColumnIndex(String str) {
        int columnCount = getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            if (this.list.get(i).lR.equals(str)) {
                return i;
            }
        }
        return -1;
    }
}
