package com.alibaba.sdk.android.man.crashreporter.a.b;

import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import java.lang.Thread;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes11.dex */
public class b {
    private static Lock a = new ReentrantLock();

    public String a(Map<Thread, StackTraceElement[]> map) {
        int i;
        String stringBuffer;
        Thread key;
        a.lock();
        if (map != null) {
            try {
                try {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
                    int i2 = 15;
                    if (configure != null) {
                        i2 = configure.enableMaxThreadNumber;
                        i = configure.enableMaxThreadStackTraceNumber;
                        if (i2 == 0) {
                            stringBuffer = "";
                            return stringBuffer;
                        }
                    } else {
                        i = 15;
                    }
                    int i3 = 0;
                    for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
                        if (entry != null && (key = entry.getKey()) != null) {
                            String name = key.getName();
                            if (name == null || !name.equals("ANR-WatchDog")) {
                                int priority = key.getPriority();
                                long id = key.getId();
                                String str = "";
                                Thread.State state = key.getState();
                                if (state != null) {
                                    str = state.name();
                                }
                                String str2 = "";
                                ThreadGroup threadGroup = key.getThreadGroup();
                                if (threadGroup != null) {
                                    str2 = threadGroup.getName();
                                    if (str2.equals("system")) {
                                    }
                                }
                                String name2 = key.getClass().getName();
                                String str3 = "";
                                ClassLoader contextClassLoader = key.getContextClassLoader();
                                if (contextClassLoader != null) {
                                    str3 = contextClassLoader.toString();
                                }
                                stringBuffer2.append(String.format("name:%s prio:%d tid:%d \n|state:%s \n|group:%s \n|class:%s \n|classLoader:%s\n", name, Integer.valueOf(priority), Long.valueOf(id), str, str2, name2, str3));
                                StackTraceElement[] value = entry.getValue();
                                if (value != null && i != 0) {
                                    stringBuffer2.append("|stackTrace:\n ");
                                    int i4 = 0;
                                    for (StackTraceElement stackTraceElement : value) {
                                        if (stackTraceElement != null) {
                                            stringBuffer2.append(String.format("%s\n", stackTraceElement.toString()));
                                        }
                                        i4++;
                                        if (i4 >= i) {
                                            break;
                                        }
                                    }
                                }
                                stringBuffer2.append("\n");
                            }
                        }
                        i3++;
                        if (i3 >= i2) {
                            break;
                        }
                    }
                    stringBuffer = stringBuffer2.toString();
                    return stringBuffer;
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("serialization failed.", e);
                }
            } finally {
                a.unlock();
            }
        }
        a.unlock();
        return "";
    }
}
