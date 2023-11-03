package com.xiaopeng.libtheme;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.xiaopeng.libtheme.ThemeManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class ThemeViewModel {
    private HashMap<String, Integer> mAttributeSet;
    private OnCallback mCallback;
    private List<OnCallback> mCallbacks = new ArrayList();
    private int mUiMode;

    /* loaded from: classes12.dex */
    public interface OnCallback {
        void onThemeChanged();
    }

    private ThemeViewModel(Context context, HashMap<String, Integer> hashMap) {
        this.mUiMode = context.getResources().getConfiguration().uiMode;
        this.mAttributeSet = hashMap == null ? new HashMap<>() : hashMap;
    }

    public static ThemeViewModel create(Context context) {
        return new ThemeViewModel(context, new HashMap());
    }

    public static ThemeViewModel create(Context context, AttributeSet attributeSet) {
        return new ThemeViewModel(context, ThemeManager.resolveAttribute(context, attributeSet, 0, 0, null));
    }

    public static ThemeViewModel create(Context context, AttributeSet attributeSet, int i, int i2) {
        return new ThemeViewModel(context, ThemeManager.resolveAttribute(context, attributeSet, i, i2, null));
    }

    public static ThemeViewModel create(Context context, AttributeSet attributeSet, int i, int i2, Object obj) {
        return new ThemeViewModel(context, ThemeManager.resolveAttribute(context, attributeSet, i, i2, getExtra(obj)));
    }

    public static List<String> asList(String... strArr) {
        if (strArr != null) {
            return Arrays.asList(strArr);
        }
        return null;
    }

    public static HashMap<String, List<String>> asMaps(String str, String... strArr) {
        if (!TextUtils.isEmpty(str) && strArr != null) {
            HashMap<String, List<String>> hashMap = new HashMap<>();
            hashMap.put(str, Arrays.asList(strArr));
            return hashMap;
        }
        return null;
    }

    public static HashMap<String, List<String>> asMaps(String str, List<String> list) {
        if (!TextUtils.isEmpty(str) && list != null && !list.isEmpty()) {
            HashMap<String, List<String>> hashMap = new HashMap<>();
            hashMap.put(str, list);
            return hashMap;
        }
        return null;
    }

    public static HashMap<String, List<String>> getExtra(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof List) {
                    return asMaps(ThemeManager.KEY_APPEND, (List) obj);
                }
                if (obj instanceof HashMap) {
                    return (HashMap) obj;
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public void setCallback(OnCallback onCallback) {
        this.mCallback = onCallback;
    }

    public void addCallback(OnCallback onCallback) {
        if (onCallback != null && this.mCallbacks != null) {
            this.mCallbacks.add(onCallback);
        }
    }

    public void removeCallback(OnCallback onCallback) {
        if (onCallback != null && this.mCallbacks != null && this.mCallbacks.contains(onCallback)) {
            this.mCallbacks.remove(onCallback);
        }
    }

    public void onAttachedToWindow(View view) {
        int uiMode = ThemeManager.getUiMode(view.getContext());
        ThemeManager.Logger.log("ThemeViewModel onAttachedToWindow newMode=" + uiMode + " oldMode=" + this.mUiMode + " view=" + getViewInfo(view));
        if (isUiModeChanged(uiMode)) {
            updateThemeResource(view);
        }
        this.mUiMode = uiMode;
    }

    public void onDetachedFromWindow(View view) {
    }

    public void onWindowFocusChanged(View view, boolean z) {
        if (z) {
            int uiMode = ThemeManager.getUiMode(view.getContext());
            ThemeManager.Logger.log("ThemeViewModel onWindowFocusChanged newMode=" + uiMode + " oldMode=" + this.mUiMode + " view=" + getViewInfo(view));
            if (isUiModeChanged(uiMode)) {
                updateThemeResource(view);
            }
            this.mUiMode = uiMode;
        }
    }

    public void onWindowVisibilityChanged(View view, int i) {
        if (i == 0) {
            int uiMode = ThemeManager.getUiMode(view.getContext());
            ThemeManager.Logger.log("ThemeViewModel onWindowVisibilityChanged newMode=" + uiMode + " oldMode=" + this.mUiMode + " view=" + getViewInfo(view));
            if (isUiModeChanged(uiMode)) {
                updateThemeResource(view);
            }
            this.mUiMode = uiMode;
        }
    }

    public void onConfigurationChanged(View view, Configuration configuration) {
        int i = configuration != null ? configuration.uiMode : 0;
        boolean isThemeChanged = ThemeManager.isThemeChanged(configuration);
        ThemeManager.Logger.log("ThemeViewModel onConfigurationChanged isThemeChanged=" + isThemeChanged + " uiMode=" + i + " view=" + getViewInfo(view));
        if (isThemeChanged) {
            updateThemeResource(view);
            this.mUiMode = configuration != null ? configuration.uiMode : this.mUiMode;
        }
    }

    public void setTextColor(int i) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.TEXT_COLOR, Integer.valueOf(i));
    }

    public void setBackground(Drawable drawable) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.BACKGROUND, 0);
    }

    public void setBackgroundColor(int i) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.BACKGROUND, 0);
    }

    public void setBackgroundResource(int i) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.BACKGROUND, Integer.valueOf(i));
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.BACKGROUND, 0);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.SRC, 0);
    }

    public void setImageResource(int i) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.SRC, Integer.valueOf(i));
    }

    public void setImageDrawable(Drawable drawable) {
        this.mAttributeSet.put(ThemeManager.AttributeSet.SRC, 0);
    }

    public void setThemeAttribute(String str, int i) {
        if (!TextUtils.isEmpty(str) && this.mAttributeSet.containsKey(str)) {
            this.mAttributeSet.put(str, Integer.valueOf(i));
        }
    }

    private boolean isUiModeChanged(int i) {
        return i != this.mUiMode;
    }

    private void updateThemeResource(View view) {
        ThemeManager.Logger.log("ThemeViewModel updateThemeResource view=" + getViewInfo(view) + " attr=" + getAttrInfo());
        ThemeManager.updateAttribute(view, this.mAttributeSet);
        if (this.mCallback != null) {
            this.mCallback.onThemeChanged();
        }
        if (this.mCallbacks != null && this.mCallbacks.size() > 0) {
            for (OnCallback onCallback : this.mCallbacks) {
                if (onCallback != null) {
                    onCallback.onThemeChanged();
                }
            }
        }
    }

    private String getAttrInfo() {
        try {
            if (this.mAttributeSet != null && this.mAttributeSet.keySet() != null) {
                return Arrays.toString(this.mAttributeSet.keySet().toArray());
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    private String getViewInfo(View view) {
        return view != null ? view.toString() : "view null";
    }
}
