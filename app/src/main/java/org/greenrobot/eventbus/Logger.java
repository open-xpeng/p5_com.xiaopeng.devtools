package org.greenrobot.eventbus;

import android.util.Log;
import java.io.PrintStream;
import java.util.logging.Level;

/* loaded from: classes13.dex */
public interface Logger {
    void log(Level level, String str);

    void log(Level level, String str, Throwable th);

    /* loaded from: classes13.dex */
    public static class AndroidLogger implements Logger {
        static final boolean ANDROID_LOG_AVAILABLE;
        private final String tag;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException e) {
            }
            ANDROID_LOG_AVAILABLE = z;
        }

        public static boolean isAndroidLogAvailable() {
            return ANDROID_LOG_AVAILABLE;
        }

        public AndroidLogger(String str) {
            this.tag = str;
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(mapLevel(level), this.tag, str);
            }
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                int mapLevel = mapLevel(level);
                String str2 = this.tag;
                Log.println(mapLevel, str2, str + "\n" + Log.getStackTraceString(th));
            }
        }

        protected int mapLevel(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                if (intValue < 500) {
                    return 2;
                }
                return 3;
            } else if (intValue < 900) {
                return 4;
            } else {
                if (intValue < 1000) {
                    return 5;
                }
                return 6;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class JavaLogger implements Logger {
        protected final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    /* loaded from: classes13.dex */
    public static class SystemOutLogger implements Logger {
        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            printStream.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }
}
