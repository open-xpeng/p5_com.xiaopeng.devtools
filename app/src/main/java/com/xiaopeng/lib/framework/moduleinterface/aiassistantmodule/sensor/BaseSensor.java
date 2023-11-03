package com.xiaopeng.lib.framework.moduleinterface.aiassistantmodule.sensor;

import java.lang.reflect.Field;

/* loaded from: classes12.dex */
public abstract class BaseSensor {
    private ISensorCallback mSensorCallback;
    private ISensorListener mSensorListener;

    public abstract void initField();

    public abstract String sensorName();

    public abstract void startSensing();

    public abstract void stopSensing();

    protected BaseSensor() {
        initField();
    }

    public void setSensorListener(ISensorListener iSensorListener) {
        this.mSensorListener = iSensorListener;
    }

    public void setSensorCallback(ISensorCallback iSensorCallback) {
        this.mSensorCallback = iSensorCallback;
    }

    public ISensorCallback getSensorCallback() {
        return this.mSensorCallback;
    }

    public ISensorListener getSensorListener() {
        return this.mSensorListener;
    }

    public void doExtra(String str) {
    }

    public void refreshField(String str) {
    }

    protected void sensing(String str, String str2) {
        if (getSensorListener() != null) {
            getSensorListener().onSensorChange(sensorName(), str, str2);
        }
    }

    protected void sensing(String str, Object obj) {
        sensing(str, String.valueOf(obj));
    }

    public String onRequestSensorValue(String str) {
        String str2;
        try {
            Field fieldByClasss = getFieldByClasss(str, this);
            refreshField(str);
            fieldByClasss.setAccessible(true);
            str2 = String.valueOf(fieldByClasss.get(this));
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        if (getSensorCallback() != null) {
            getSensorCallback().onSensorReponse(sensorName(), str, str2);
        }
        return str2;
    }

    private Field getFieldByClasss(String str, Object obj) {
        Field field = null;
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                field = cls.getDeclaredField(str);
            } catch (Exception e) {
            }
        }
        return field;
    }
}
