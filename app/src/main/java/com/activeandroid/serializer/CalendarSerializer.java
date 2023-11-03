package com.activeandroid.serializer;

import java.util.Calendar;

/* loaded from: classes11.dex */
public final class CalendarSerializer extends TypeSerializer {
    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getDeserializedType() {
        return Calendar.class;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getSerializedType() {
        return Long.TYPE;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Long serialize(Object obj) {
        return Long.valueOf(((Calendar) obj).getTimeInMillis());
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Calendar deserialize(Object obj) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(((Long) obj).longValue());
        return calendar;
    }
}
