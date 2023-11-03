package com.xiaopeng.libtheme;

import android.app.IUiModeManager;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class ThemeManager {
    public static final boolean DEBUG;
    public static final String KEY_APPEND = "extra_append";
    public static final String KEY_GLOBAL = "extra_global";
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_YES = 2;
    private static final String TAG = "ThemeManager";
    public static final boolean THEME;
    public static final long THEME_ANIMATION_INTERVAL;
    public static final long THEME_TIMEOUT_DELAY;
    public static final int UI_MODE_NIGHT_MASK = 48;
    public static final int UI_MODE_NIGHT_NO = 16;
    public static final int UI_MODE_NIGHT_UNDEFINED = 0;
    public static final int UI_MODE_NIGHT_YES = 32;
    public static final int UI_MODE_THEME_CLEAR = 63;
    public static final int UI_MODE_THEME_MASK = 192;
    public static final int UI_MODE_THEME_UNDEFINED = 0;
    public static final int VERSION = 3;
    private Context mContext;
    private ThemeData mThemeData = new ThemeData();

    static {
        THEME = SystemProperties.getInt("persist.sys.theme.logger", 0) == 1;
        DEBUG = THEME;
        THEME_ANIMATION_INTERVAL = UiModeManager.THEME_ANIMATION_INTERVAL;
        THEME_TIMEOUT_DELAY = UiModeManager.THEME_TIMEOUT_DELAY;
    }

    private ThemeManager(Context context, View view, String str, List<ThemeView> list) {
        this.mContext = context.getApplicationContext();
        this.mThemeData.xml = str;
        this.mThemeData.root = view;
        this.mThemeData.list = list;
    }

    public static ThemeManager create(Context context, View view, String str, List<ThemeView> list) {
        return new ThemeManager(context, view, str, list);
    }

    public void onConfigurationChanged(Configuration configuration) {
        ThemeWrapper.getInstance().onConfigurationChanged(this.mContext, this.mThemeData, configuration);
    }

    public static boolean isThemeChanged(Configuration configuration) {
        return (configuration == null || (configuration.uiMode & UI_MODE_THEME_MASK) == 0) ? false : true;
    }

    public static int getThemeMode(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getThemeMode();
    }

    public static int getDayNightMode(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getDayNightMode();
    }

    public static int getDayNightAutoMode(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getDayNightAutoMode();
    }

    public static int getUiMode(Context context) {
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Configuration configuration = applicationContext != null ? applicationContext.getResources().getConfiguration() : null;
        if (configuration != null) {
            return configuration.uiMode;
        }
        return 0;
    }

    public static boolean isNightMode(Context context) {
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Configuration configuration = applicationContext != null ? applicationContext.getResources().getConfiguration() : null;
        return configuration != null && (configuration.uiMode & 48) == 32;
    }

    public static boolean isThemeWorking(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).isThemeWorking();
    }

    public static void applyThemeMode(Context context, int i) {
        ((UiModeManager) context.getSystemService("uimode")).applyThemeMode(i);
    }

    public static void applyDayNightMode(Context context, int i) {
        ((UiModeManager) context.getSystemService("uimode")).applyDayNightMode(i);
    }

    public static IUiModeManager getUiModeManager() {
        return IUiModeManager.Stub.asInterface(ServiceManager.getService("uimode"));
    }

    public static void setWindowBackgroundResource(Configuration configuration, Window window, int i) {
        if (isThemeChanged(configuration) && window != null) {
            try {
                window.setBackgroundDrawableResource(i);
            } catch (Exception e) {
            }
        }
    }

    public static void setWindowBackgroundDrawable(Configuration configuration, Window window, Drawable drawable) {
        if (isThemeChanged(configuration) && window != null) {
            try {
                window.setBackgroundDrawable(drawable);
            } catch (Exception e) {
            }
        }
    }

    public static HashMap<String, Integer> resolveAttribute(Context context, android.util.AttributeSet attributeSet) {
        return ThemeWrapper.resolveAttribute(context, attributeSet, 0, 0, null);
    }

    public static HashMap<String, Integer> resolveAttribute(Context context, android.util.AttributeSet attributeSet, int i, int i2, HashMap<String, List<String>> hashMap) {
        return ThemeWrapper.resolveAttribute(context, attributeSet, i, i2, hashMap);
    }

    public static void setAttributeValue(HashMap<String, Integer> hashMap, String str, int i) {
        ThemeWrapper.setAttributeValue(hashMap, str, i);
    }

    public static void updateAttribute(View view, HashMap<String, Integer> hashMap) {
        ThemeWrapper.updateAttribute(view, hashMap);
    }

    /* loaded from: classes12.dex */
    public static class Logger {
        public static void log(String str) {
            if (ThemeManager.DEBUG) {
                Log.i(ThemeManager.TAG, str);
            }
        }

        public static void log(String str, String str2) {
            Log.i(str, str2);
        }
    }

    /* loaded from: classes12.dex */
    public static class ViewBuilder {
        private List<ThemeView> list = new ArrayList();

        private ViewBuilder() {
            this.list.clear();
        }

        public static ViewBuilder create() {
            return new ViewBuilder();
        }

        public ViewBuilder add(int i, String str, int i2) {
            return add(i, str, -1, i2);
        }

        public ViewBuilder add(int i, String str, int i2, int i3) {
            if (i > 0) {
                try {
                    if (!TextUtils.isEmpty(str) && i3 >= 0) {
                        ThemeView themeView = new ThemeView();
                        themeView.resId = i;
                        themeView.resAttr = str;
                        themeView.resRoot = i2;
                        themeView.resValue = Integer.valueOf(i3);
                        this.list.add(themeView);
                    }
                } catch (Exception e) {
                }
            }
            return this;
        }

        public List<ThemeView> get() {
            return this.list;
        }
    }

    /* loaded from: classes12.dex */
    public static class ResourceType {
        public static final int ANIM = 0;
        public static final int ARRAY = 1;
        public static final int ATTR = 2;
        public static final int BOOL = 3;
        public static final int COLOR = 4;
        public static final int DIMEN = 5;
        public static final int DRAWABLE = 6;
        public static final int ID = 7;
        public static final int INTEGER = 8;
        public static final int LAYOUT = 9;
        public static final int MIPMAP = 10;
        public static final int STRING = 11;
        public static final int STYLE = 12;
        public static final int STYLEABLE = 13;
        public static final HashMap<String, Integer> sResourceType = new HashMap<>();

        static {
            sResourceType.clear();
            sResourceType.put("anim", 0);
            sResourceType.put("array", 1);
            sResourceType.put("attr", 2);
            sResourceType.put("bool", 3);
            sResourceType.put("color", 4);
            sResourceType.put("dimen", 5);
            sResourceType.put("drawable", 6);
            sResourceType.put("id", 7);
            sResourceType.put("integer", 8);
            sResourceType.put("layout", 9);
            sResourceType.put("mipmap", 10);
            sResourceType.put("string", 11);
            sResourceType.put(AttributeSet.STYLE, 12);
            sResourceType.put("styleable", 13);
        }

        public static int getType(String str) {
            if (!TextUtils.isEmpty(str)) {
                return sResourceType.get(str).intValue();
            }
            return -1;
        }
    }

    /* loaded from: classes12.dex */
    public static class AttributeSet {
        public static final String ALPHA = "alpha";
        public static final String BACKGROUND = "background";
        public static final String BUTTON = "button";
        public static final String DIVIDER = "divider";
        public static final String DRAWABLE_BOTTOM = "drawableBottom";
        public static final String DRAWABLE_END = "drawableEnd";
        public static final String DRAWABLE_LEFT = "drawableLeft";
        public static final String DRAWABLE_RIGHT = "drawableRight";
        public static final String DRAWABLE_START = "drawableStart";
        public static final String DRAWABLE_TOP = "drawableTop";
        public static final String FOREGROUND = "foreground";
        public static final String PROGRESS_DRAWABLE = "progressDrawable";
        public static final String SCROLLBAR_THUMB_VERTICAL = "scrollbarThumbVertical";
        public static final String SRC = "src";
        public static final String STYLE = "style";
        public static final String TEXT_COLOR = "textColor";
        public static final String TEXT_COLOR_HINT = "textColorHint";
        public static final String THEME = "theme";
        public static final String THUMB = "thumb";
        public static final HashMap<String, Integer> sAttributeMap = new HashMap<>();

        static {
            sAttributeMap.clear();
            sAttributeMap.put(STYLE, 0);
            sAttributeMap.put(THEME, 16842752);
            sAttributeMap.put(ALPHA, 16843551);
            sAttributeMap.put(FOREGROUND, 16843017);
            sAttributeMap.put(BACKGROUND, 16842964);
            sAttributeMap.put(SCROLLBAR_THUMB_VERTICAL, 16842853);
            sAttributeMap.put(SRC, 16843033);
            sAttributeMap.put(TEXT_COLOR, 16842904);
            sAttributeMap.put(TEXT_COLOR_HINT, 16842906);
            sAttributeMap.put(DRAWABLE_LEFT, 16843119);
            sAttributeMap.put(DRAWABLE_TOP, 16843117);
            sAttributeMap.put(DRAWABLE_RIGHT, 16843120);
            sAttributeMap.put(DRAWABLE_BOTTOM, 16843118);
            sAttributeMap.put(DRAWABLE_START, 16843666);
            sAttributeMap.put(DRAWABLE_END, 16843667);
            sAttributeMap.put(PROGRESS_DRAWABLE, 16843068);
            sAttributeMap.put(THUMB, 16843074);
            sAttributeMap.put(BUTTON, 16843015);
            sAttributeMap.put(DIVIDER, 16843049);
        }

        public static boolean hasAttribute(String str) {
            if (!TextUtils.isEmpty(str)) {
                return sAttributeMap.containsKey(str);
            }
            return false;
        }

        public static boolean isThemeAttribute(String str) {
            if (!TextUtils.isEmpty(str)) {
                return THEME.equals(str);
            }
            return false;
        }

        public static boolean isStyleAttribute(String str) {
            if (!TextUtils.isEmpty(str)) {
                return STYLE.equals(str);
            }
            return false;
        }

        public static boolean supportTransition(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return BACKGROUND.equals(str) || SRC.equals(str) || TEXT_COLOR.equals(str);
        }
    }
}
