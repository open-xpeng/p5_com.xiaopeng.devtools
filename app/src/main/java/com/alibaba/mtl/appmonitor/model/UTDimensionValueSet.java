package com.alibaba.mtl.appmonitor.model;

import com.alibaba.mtl.log.model.LogField;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public class UTDimensionValueSet extends DimensionValueSet {
    private static final Set<LogField> bF = new HashSet<LogField>() { // from class: com.alibaba.mtl.appmonitor.model.UTDimensionValueSet.1
        {
            add(LogField.PAGE);
            add(LogField.ARG1);
            add(LogField.ARG2);
            add(LogField.ARG3);
            add(LogField.ARGS);
        }
    };

    public Integer Z() {
        int i;
        String str;
        if (this.map != null && (str = this.map.get(LogField.EVENTID.toString())) != null) {
            try {
                i = com.alibaba.mtl.appmonitor.f.a.a(str);
            } catch (NumberFormatException e) {
            }
            return Integer.valueOf(i);
        }
        i = 0;
        return Integer.valueOf(i);
    }

    @Override // com.alibaba.mtl.appmonitor.model.DimensionValueSet, com.alibaba.mtl.appmonitor.c.b
    public void clean() {
        super.clean();
    }

    @Override // com.alibaba.mtl.appmonitor.model.DimensionValueSet, com.alibaba.mtl.appmonitor.c.b
    public void a(Object... objArr) {
        super.a(objArr);
    }
}
